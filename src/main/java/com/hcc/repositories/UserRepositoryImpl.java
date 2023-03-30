package com.hcc.repositories;

import com.hcc.entities.User;
import com.hcc.exceptions.EtAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository{

    private static final String SQL_CREATE = "INSERT INTO users(id,cohort_start_date, username, password)" +
            " VALUES(NEXTVAL('ET_USERS_SEQ'), ?, ?, ?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM users WHERE username = ?";
    private static final String SQL_FIND_BY_ID = "SELECT id, cohort_start_date, username, password" +
            "FROM users WHERE id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Date date, String username, String password) throws EtAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setDate(1,   new java.sql.Date(date.getTime()));
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                return preparedStatement;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("id");
        }catch (Exception e){
            throw new EtAuthException("Invalid details. Failed to create account");
        }

    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) throws EtAuthException {
        return null;
    }

    @Override
    public Integer getCountByUsername(String username) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, Integer.class, username);
    }

    @Override
    public Optional<User> findByUserId(Long userId) {
        List<User> users = jdbcTemplate.query(SQL_FIND_BY_ID, userRowMapper, userId);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        Array authoritiesArray = rs.getArray("authorities");
        List<String> authoritiesList = new ArrayList<>();
        if (authoritiesArray != null) {
            authoritiesList = Arrays.asList((String[]) authoritiesArray.getArray());
        }

        return new User(rs.getInt("id "),
                        rs.getDate("cohortStartDate"),
                        rs.getString("username"),
                        rs.getString("password"),
                        authoritiesList);
    });

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    public Optional<User> findById(int aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public User getById(Long aLong) {
        return null;
    }

    @Override
    public User getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


}

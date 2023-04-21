package com.hcc.services;

public interface SecurityService {
    String findLoggedInUsername();
    void authenticate(String username, String password);
    void autoLogin(String username, String password);
}
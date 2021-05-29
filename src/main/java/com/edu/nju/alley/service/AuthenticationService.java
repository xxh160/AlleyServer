package com.edu.nju.alley.service;

public interface AuthenticationService {

    Integer isExist(String code);

    void addUser(Integer userId, Integer codeId);

}

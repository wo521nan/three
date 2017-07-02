package com.gong.service;

import com.gong.model.User;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface UserService {
//    public boolean add(User user);
//    public void delete(List<String> keys);
//    public boolean update(User user);
//    public User get(String keyId);
    public void saveUser(User user);
    public User getUser(final String username);
}

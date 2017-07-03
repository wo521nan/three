package com.gong.dao.impl;

import com.gong.dao.UserDao;
import com.gong.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/29.
 */
public class UserDaoImpl implements UserDao {
    private RedisTemplate<Serializable,Serializable> redisTemplate;
    public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
        return redisTemplate;
    }
    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    public User getUserByName(final  String username) {
//        return redisTemplate.execute(new RedisCallback<User>() {
//            public User doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                byte[] key = redisTemplate.getStringSerializer().serialize("user.username" +username );
//                if (redisConnection.exists(key)) {
//                    byte[] value = redisConnection.get(key);
//                    String password = redisTemplate.getStringSerializer().deserialize(value);
//                    User user = new User();
//                    user.setPassword(value);
//                    user.setId(username);
//                    return user;
//                }
//                return null;
//            }
//        });
//    }

    public void saveUser(final User user) {
        redisTemplate.execute(new RedisCallback<Object>(){

            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(redisTemplate.getStringSerializer().serialize(user.getUsername()),
                        redisTemplate.getStringSerializer().serialize(user.getPassword()));
                return null;
            }
        });
    }


    public User getUser(final  String username){
        return redisTemplate.execute(new RedisCallback<User>() {
            public User doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] key = redisTemplate.getStringSerializer().serialize(username);
                if (redisConnection.exists(key)){
                    byte[] value = redisConnection.get(key);
                    String password = redisTemplate.getStringSerializer().deserialize(value);
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    return user;
                }
                return null;
            }
        });

    }
//    public boolean add(final User user) {
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<String> serializer = getRedisSerializer();
//                byte[] key  = serializer.serialize(user.getId());
//                byte[] name = serializer.serialize(user.getUsername());
//                return connection.setNX(key, name);
//            }
//        });
//        return result;
//    }
//
//
//    public void delete(List<String> keys) {
//        redisTemplate.delete(keys);
//    }  ;
//
//    public boolean update(final User user) {
//        String key = user.getId();
//        if (get(key) == null) {
//            throw new NullPointerException("数据行不存在, key = " + key);
//        }
//        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<String> serializer = getRedisSerializer();
//                byte[] key  = serializer.serialize(user.getId());
//                byte[] name = serializer.serialize(user.getUsername());
//                connection.set(key, name);
//                return true;
//            }
//        });
//        return result;
//    }
//
//    public User get(final String keyId) {
//        User result = redisTemplate.execute(new RedisCallback<User>() {
//            public User doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                RedisSerializer<String> serializer = getRedisSerializer();
//                byte[] key = serializer.serialize(keyId);
//                byte[] value = connection.get(key);
//                if (value == null) {
//                    return null;
//                }
//                String name = serializer.deserialize(value);
//                return new User(keyId, name, null);
//            }
//        });
//        return result;
//    }
}


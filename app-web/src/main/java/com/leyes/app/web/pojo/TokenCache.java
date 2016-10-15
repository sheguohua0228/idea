package com.leyes.app.web.pojo;

import com.leyes.app.redis.RedisObject;

public class TokenCache extends RedisObject{

    private String token;
    
    public TokenCache(){}
    public TokenCache(String token){
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
}

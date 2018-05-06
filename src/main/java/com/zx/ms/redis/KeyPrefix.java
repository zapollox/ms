package com.zx.ms.redis;

/**
 * Created by DELL on 2018/5/5.
 */
public interface KeyPrefix {
    public int expireSeconds();//过期时间

    public String getPrefix();//前缀
}

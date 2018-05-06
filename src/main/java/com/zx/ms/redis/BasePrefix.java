package com.zx.ms.redis;

/**
 * Created by DELL on 2018/5/5.
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireSeconds;//过期时间

    private String prefix;//redis前缀

    public BasePrefix(String prefix) {//0永不过期
        this(0, prefix);
    }
    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        return simpleName+":"+prefix;
    }
}

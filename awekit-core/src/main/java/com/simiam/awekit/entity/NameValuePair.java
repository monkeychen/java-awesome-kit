package com.simiam.awekit.entity;

/**
 * <p>Title: NameValuePair</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/6/5 19:41</p>
 */
public class NameValuePair<V> {
    private String name;
    private V value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NameValuePair() {

    }

    public NameValuePair(String name, V value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + " = " + value;
    }
}

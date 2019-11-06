package com.dfbz.entity;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 4977910058169348703L;

    private Long id;
    private String name;
    private Integer age;
    private String sex;
    private String createTime;
    private String delFlag;

    public User() {
    }

    public User(Long id, String name, Integer age, String sex, String createTime, String delFlag) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.createTime = createTime;
        this.delFlag = delFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", createTime='" + createTime + '\'' +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}

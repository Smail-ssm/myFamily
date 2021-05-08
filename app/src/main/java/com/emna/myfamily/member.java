package com.emna.myfamily;

public class member {
    String name;
    String lastname;
    String age;
    String relation;
    String email;
    String phone;
    String latit;
    String longtit, Id;

    public member() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatit() {
        return latit;
    }

    public void setLatit(String latit) {
        this.latit = latit;
    }

    public String getLongtit() {
        return longtit;
    }

    public void setLongtit(String longtit) {
        this.longtit = longtit;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public member(String name, String lastname, String age, String relation, String email, String phone, String latit, String longtit, String id) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.relation = relation;
        this.email = email;
        this.phone = phone;
        this.latit = latit;
        this.longtit = longtit;
        Id = id;
    }
}

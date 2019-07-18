package com.lco.lcoproject.Model;

public class User {

        private String user_id;
        private String name;
        private String phone;
        private String email;
        private String addr;
        private String city;
        private  String other;
    public  String url;

    public User(String user_id, String name,String phone,String email,String addr,String city,String  other,String url) {
        this.user_id = user_id;
        this.name = name;
        this.phone=phone;
        this.email=email;
        this.addr=addr;
        this.city=city;
        this.other=other;
    }

    public User() {

    }

    public String getOther(String other) {
        return this.other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser_id() {
        return user_id;
    }

        public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

        public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

        @Override
        public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", addr='" + addr + '\'' +
                ", city='" + city + '\'' +
                ", other='" + other + '\'' +
                ", url='" + url + '\'' +



                '}';
    }
}

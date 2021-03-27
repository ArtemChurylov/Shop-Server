package com.example.shop.application.models;

public class Address {

    private String country;
    private String region;
    private String city;
    private Integer post_office_number;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPost_office_number() {
        return post_office_number;
    }

    public void setPost_office_number(Integer post_office_number) {
        this.post_office_number = post_office_number;
    }
}

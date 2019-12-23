package com.ma7moud.newsapp.models.CountryModel;

public class Country {

    private String name ;
    private String code ;

    @Override
    public String toString() {
        return getName();
    }

    public Country() {
    }

    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

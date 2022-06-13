package com.example.pokedesx;

public class Pokemon {

    private Integer number;
    private String name;
    private String url;

    public Pokemon() {

    }

    public Pokemon(Integer number, String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Integer getNumber() {
        String [] fragmentosUrl = url.split("/");
        return Integer.parseInt(fragmentosUrl[fragmentosUrl.length-1]);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

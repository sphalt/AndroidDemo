package com.saiph.androiddemo;

public class Country {
    private String countryName;
    private String capital;
    private String population;
    private String area;
    private String region;
    private String subregion;

    public Country(String countryName, String capital, String population, String area, String region, String subregion) {
        this.countryName = countryName;
        this.capital = capital;
        this.population = population;
        this.area = area;
        this.region = region;
        this.subregion = subregion;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }
}

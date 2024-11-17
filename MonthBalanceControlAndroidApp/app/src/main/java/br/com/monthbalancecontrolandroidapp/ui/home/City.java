package br.com.monthbalancecontrolandroidapp.ui.home;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.PropertyName;

import java.util.List;

public class City {
//    @PropertyName("name")
    private String name;
//    @PropertyName("state")
    private String state;
//    @PropertyName("country")
    private String country;
//    @PropertyName("capital")
    private boolean capital;
//    @PropertyName("population")
    private long population;
//    @PropertyName("regions")
    private List<String> regions;

    public City(String name, String state, String country, boolean capital, long population, List<String> regions) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.capital = capital;
        this.population = population;
        this.regions = regions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s", name, state);
    }
}

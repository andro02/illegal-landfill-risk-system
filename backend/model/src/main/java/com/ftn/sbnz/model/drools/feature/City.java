package com.ftn.sbnz.model.drools.feature;

public class City extends NearbyFeature {
    private int population;

    public City(String name, double distanceM, int population) {
        super(name, distanceM);
        this.population = population;
    }

    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
}
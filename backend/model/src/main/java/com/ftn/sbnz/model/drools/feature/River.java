package com.ftn.sbnz.model.drools.feature;

public class River extends NearbyFeature {
    private int importance; // 1-10

    public River(String name, double distanceM, int importance) {
        super(name, distanceM);
        this.importance = importance;
    }

    public int getImportance() { return importance; }
    public void setImportance(int importance) { this.importance = importance; }
}
package com.ftn.sbnz.model.drools.feature;

public class IndustrialZone extends NearbyFeature {
    private String zoneType;
    private int hazardLevel;

    public IndustrialZone(String name, double distanceM, String zoneType, int hazardLevel) {
        super(name, distanceM);
        this.zoneType = zoneType;
        this.hazardLevel = hazardLevel;
    }

    public String getZoneType() { return zoneType; }
    public void setZoneType(String zoneType) { this.zoneType = zoneType; }
    public int getHazardLevel() { return hazardLevel; }
    public void setHazardLevel(int hazardLevel) { this.hazardLevel = hazardLevel; }
}
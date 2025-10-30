// ...existing code...
package br.univ.drone.sensors;

public class SimpleAltitudeSensor implements AltitudeSensor {
    private double altitude = 0.0;

    @Override public double getAltitude() { return altitude; }
    @Override public void setAltitude(double altitude) { this.altitude = altitude; }
}
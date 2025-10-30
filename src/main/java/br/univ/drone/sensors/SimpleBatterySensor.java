// ...existing code...
package br.univ.drone.sensors;

public class SimpleBatterySensor implements BatterySensor {
    private double level = 100.0;

    @Override
    public double getLevel() { return level; }

    @Override
    public void setLevel(double level) { this.level = Math.max(0.0, Math.min(100.0, level)); }

    public void drain(double amount) { setLevel(level - amount); }
}
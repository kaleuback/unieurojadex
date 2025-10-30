// ...existing code...
package br.univ.drone.sensors;

public class SimpleGpsSensor implements GpsSensor {
    private double x, y, z;

    @Override
    public double[] getPosition() { return new double[] { x, y, z }; }

    public void setPosition(double x, double y, double z) { this.x = x; this.y = y; this.z = z; }
}
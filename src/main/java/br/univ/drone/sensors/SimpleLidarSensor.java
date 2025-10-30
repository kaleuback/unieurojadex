// ...existing code...
package br.univ.drone.sensors;

public class SimpleLidarSensor implements LidarSensor {
    private double distanceFront = -1.0;

    @Override
    public double getDistance(double angleRadians) {
        return distanceFront; // versão simples: ignora ângulo
    }

    public void setDistanceFront(double d) { this.distanceFront = d; }
}
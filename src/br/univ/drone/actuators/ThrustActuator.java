// ...existing code...
package br.univ.drone.actuators;

public interface ThrustActuator {
    // throttle, roll, pitch, yaw (-1..1)
    void setControl(double throttle, double roll, double pitch, double yaw);
}
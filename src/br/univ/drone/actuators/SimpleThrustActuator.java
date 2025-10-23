// ...existing code...
package br.univ.drone.actuators;

public class SimpleThrustActuator implements ThrustActuator {
    private double throttle, roll, pitch, yaw;

    @Override
    public void setControl(double throttle, double roll, double pitch, double yaw) {
        this.throttle = throttle;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        System.out.printf("[Thrust] throttle=%.2f roll=%.2f pitch=%.2f yaw=%.2f%n",
                throttle, roll, pitch, yaw);
    }

    public double[] getLastControl() {
        return new double[] { throttle, roll, pitch, yaw };
    }
}

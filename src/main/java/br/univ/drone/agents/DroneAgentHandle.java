package br.univ.drone.agents;

/**
 * Handle simples para registrar drones no World.
 */
public class DroneAgentHandle {
    private DroneAgent drone;

    public DroneAgentHandle(DroneAgent drone) {
        this.drone = drone;
    }

    public DroneAgent getDrone() {
        return drone;
    }
}

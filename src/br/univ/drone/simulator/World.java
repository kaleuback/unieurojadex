package br.univ.drone.simulator;

import br.univ.drone.agents.DroneAgentHandle;
import java.util.*;

/**
 * Mundo simples que registra drones. Pode ser expandido.
 */
public class World {
    private List<DroneAgentHandle> drones = new ArrayList<>();

    public void stepSimulation(double dt) {
        System.out.println("[WORLD] stepSimulation dt=" + dt + "s | drones registrados=" + drones.size());
        // Aqui poderíamos atualizar posições globais, detectar colisões, etc.
    }

    public void registerDrone(DroneAgentHandle drone) {
        drones.add(drone);
        System.out.println("[WORLD] Drone registrado.");
    }
}

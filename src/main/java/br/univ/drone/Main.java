package br.univ.drone;

import br.univ.drone.agents.DroneAgent;
import br.univ.drone.agents.DroneAgentHandle;
import br.univ.drone.sensors.SimpleGpsSensor;
import br.univ.drone.sensors.SimpleLidarSensor;
import br.univ.drone.sensors.SimpleAltitudeSensor;
import br.univ.drone.sensors.SimpleBatterySensor;
import br.univ.drone.actuators.SimpleThrustActuator;
import br.univ.drone.actuators.ThrustActuator;
import br.univ.drone.simulator.World;


/**
 * Main de demonstração: cria sensores/atuadores, registra drone e executa 3 cenários.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Simulador de Drones (versão mínima) ===");

        // Criar sensores/atuadores concretos
        SimpleGpsSensor gps = new SimpleGpsSensor();
        SimpleLidarSensor lidar = new SimpleLidarSensor();
        SimpleAltitudeSensor alt = new SimpleAltitudeSensor();
        SimpleThrustActuator thrust = new SimpleThrustActuator();
        SimpleBatterySensor battery = new SimpleBatterySensor();

        // Criar agente e handle
        DroneAgent drone = new DroneAgent(gps, lidar, alt, thrust, battery);
        DroneAgentHandle handle = new DroneAgentHandle(drone);

        // Registrar no mundo
        World world = new World();
        world.registerDrone(handle);

        // ----- CENÁRIO 1: Missão normal -----
        System.out.println("\n--- CENÁRIO 1: Missão normal (bateria 80%) ---");
        battery.setLevel(80.0);
        // garante sem obstáculo
        lidar.setDistanceFront(-1.0);
        drone.run();

        // ----- CENÁRIO 2: Bateria crítica -----
        System.out.println("\n--- CENÁRIO 2: Bateria crítica (5%) ---");
        battery.setLevel(5.0);
        lidar.setDistanceFront(-1.0);
        // reiniciar e executar (novo run faz o pouso)
        DroneAgent drone2 = new DroneAgent(gps, lidar, alt, thrust, battery);
        drone2.run();

        // ----- CENÁRIO 3: Obstáculo à frente -----
        System.out.println("\n--- CENÁRIO 3: Obstáculo (2.5m) ---");
        battery.setLevel(80.0);
        lidar.setDistanceFront(2.5); // força obstáculo
        DroneAgent drone3 = new DroneAgent(gps, lidar, alt, thrust, battery);
        drone3.run();

        System.out.println("\n=== Simulação finalizada ===");
    }
}

package br.univ.drone.agents;

import br.univ.drone.sensors.*;
import br.univ.drone.actuators.*;

public class DroneAgent {
    // referências aos sensores e atuadores (injeção ou criação)
    private GpsSensor gps;
    private LidarSensor lidar;
    private AltitudeSensor alt;
    private ThrustActuator thrust;
    private BatterySensor battery;

    // crenças internas
    private volatile double[] position;
    private volatile double batteryLevel;
    private volatile boolean obstacleClose;

    public DroneAgent(GpsSensor gps, LidarSensor lidar, AltitudeSensor alt, ThrustActuator thrust, BatterySensor battery) {
        this.gps = gps; this.lidar = lidar; this.alt = alt; this.thrust = thrust; this.battery = battery;
        // inicializa crenças
    }

    // loop principal (no Jadex você modela como planos; aqui pseudo)
    public void run() {
        while(true) {
            sense(); // atualizar crenças
            if(battery.getLevel() < 10.0) {
                planEmergencyLand();
            } else if(obstacleClose) {
                planAvoidCollision();
            } else {
                planFollowMission();
            }
            sleep(100); // 100ms
        }
    }

    private void sense() {
        position = gps.getPosition();
        batteryLevel = battery.getLevel();
        double dFront = lidar.getDistance(0.0);
        obstacleClose = dFront > 0 && dFront < 5.0;
    }

    private void planFollowMission() {
        // calcular vetor até waypoint e executar thrust.setControl(...)
    }

    private void planAvoidCollision() {
        // manobra evasiva
    }

    private void planEmergencyLand() {
        // pouso seguro
    }
}
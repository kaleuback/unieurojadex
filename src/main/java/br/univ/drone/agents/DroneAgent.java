// ...existing code...
package br.univ.drone.agents;

import br.univ.drone.sensors.*;
import br.univ.drone.actuators.*;

/**
 * DroneAgent: lê sensores, atualiza crenças e executa planos simples.
 */
public class DroneAgent {

    private GpsSensor gps;
    private LidarSensor lidar;
    private AltitudeSensor alt;
    private ThrustActuator thrust;
    private BatterySensor battery;

    private volatile double[] position = {0.0, 0.0, 0.0};
    private volatile double batteryLevel = 100.0;
    private volatile boolean obstacleClose = false;

    private boolean missionActive = true;

    public DroneAgent(GpsSensor gps, LidarSensor lidar, AltitudeSensor alt,
                      ThrustActuator thrust, BatterySensor battery) {
        this.gps = gps;
        this.lidar = lidar;
        this.alt = alt;
        this.thrust = thrust;
        this.battery = battery;
    }

    public void run() {
        System.out.println("[DRONE] Levantando voo...");
        int step = 0;
        while (missionActive && step < 20) { // limite para não travar
            sense();

            if (batteryLevel < 10.0) {
                planEmergencyLand();
            } else if (obstacleClose) {
                planAvoidCollision();
            } else {
                planFollowMission();
            }

            // simula consumo de bateria leve por passo
            if (battery instanceof SimpleBatterySensor) {
                ((SimpleBatterySensor) battery).drain(2.0);
            }

            sleep(400);
            step++;
        }
        System.out.println("[DRONE] Missao finalizada.");
    }

    private void sense() {
        try {
            double[] p = gps.getPosition();
            if (p != null && p.length >= 3) position = p;
        } catch (Exception e) {
            System.out.println("[SENSE] Erro ao ler GPS: " + e.getMessage());
        }

        try {
            batteryLevel = battery.getLevel();
        } catch (Exception e) {
            System.out.println("[SENSE] Erro ao ler bateria: " + e.getMessage());
        }

        try {
            double dFront = lidar.getDistance(0.0);
            obstacleClose = dFront > 0 && dFront < 5.0;
        } catch (Exception e) {
            System.out.println("[SENSE] Erro ao ler Lidar: " + e.getMessage());
        }

        System.out.printf("[SENSE] Pos=(%.2f, %.2f, %.2f) | Bateria=%.1f%% | Obstáculo=%s%n",
                position[0], position[1], position[2],
                batteryLevel, obstacleClose ? "SIM" : "NÃO");
    }

    private void planFollowMission() {
        System.out.println("[PLAN] Seguindo missão: avançando e escaneando area.");
        thrust.setControl(0.6, 0.0, 0.1, 0.0);
    }

    private void planAvoidCollision() {
        System.out.println("[PLAN] Evitando colisão: manobra evasiva.");
        // exemplo: reduz throttle, gira à direita
        thrust.setControl(0.2, 0.6, 0.0, 0.0);
        sleep(700);
    }

    private void planEmergencyLand() {
        System.out.println("[PLAN] Pouso de emergência iniciado!");
        // descer lentamente por algumas iterações
        for (int i = 0; i < 4; i++) {
            thrust.setControl(0.1, 0.0, -0.4, 0.0);
            sleep(500);
        }
        missionActive = false;
        System.out.println("[PLAN] Pouso concluído.");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

package br.univ.drone.sensors;

public interface GpsSensor {
    // retorna posição [x, y, z]
    double[] getPosition();
}

// src/br/univ/drone/sensors/AltitudeSensor.java
package br.univ.drone.sensors;

public interface AltitudeSensor {
    double getAltitude(); // em metros
}

// src/br/univ/drone/sensors/LidarSensor.java
package br.univ.drone.sensors;

public interface LidarSensor {
    // distance at angle (degrees) or -1 if none
    double getDistance(double angleDegrees);
}

// src/br/univ/drone/actuators/ThrustActuator.java
package br.univ.drone.actuators;

public interface ThrustActuator {
    // throttle: -1.0 .. 1.0, yaw, pitch, roll control values -1..1
    void setControl(double throttle, double yaw, double pitch, double roll);
}

// src/br/univ/drone/simulator/World.java
package br.univ.drone.simulator;

import java.util.List;
import br.univ.drone.agents.DroneAgentHandle;

public class World {
    // estado global do mundo; atualiza posição dos veículos, obstáculos, etc.
    public void stepSimulation(double dt) {
        // aplicar física simples a cada drone via suas actuações
    }
    // registradores
    public void registerDrone(DroneAgentHandle drone) {}
}
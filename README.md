# Unieuro Jadex - Simulador de Drones

Este projeto é um simulador de drones em Java, utilizando uma arquitetura inspirada em agentes (Jadex-style), com sensores, atuadores e um mundo simulado.

## Estrutura do Projeto

### 1. Main.java
**Local:** src/br/univ/drone/Main.java  
- Ponto de entrada do programa.  
- Cria o mundo simulado (World) e inicializa os agentes (drones).  
- Executa o loop de simulação chamando world.stepSimulation(dt).

### 2. Sensores

#### GpsSensor
**Local:** src/br/univ/drone/sensors/GpsSensor.java  
- Interface que fornece a posição [x, y, z] do drone.

#### AltitudeSensor
**Local:** src/br/univ/drone/sensors/AltitudeSensor.java  
- Retorna a altitude do drone em metros.

#### LidarSensor
**Local:** src/br/univ/drone/sensors/LidarSensor.java  
- Retorna a distância de obstáculos em determinado ângulo (graus).  
- Retorna -1 se não houver obstáculo detectado.

### 3. Atuadores

#### ThrustActuator
**Local:** src/br/univ/drone/actuators/ThrustActuator.java  
- Controla o drone via throttle, yaw, pitch e roll, valores de -1.0 a 1.0.  

### 4. Mundo Simulado

#### World
**Local:** src/br/univ/drone/simulator/World.java  
- Mantém o estado global do mundo, incluindo posição dos drones e obstáculos.  
- stepSimulation(dt) aplica física simples e atualiza todos os drones.  
- registerDrone(drone) registra drones no mundo.

### 5. Agente de Drone

#### DroneAgent
**Local:** src/br/univ/drone/agents/DroneAgent.java  
- Representa um drone com sensores e atuadores.  
- Mantém crenças internas: posição, nível de bateria e proximidade de obstáculos.  
- Loop principal (run()):
  1. Atualiza crenças com sense().  
  2. Decide ação baseada nas crenças:
     - planEmergencyLand() para pouso seguro se a bateria estiver baixa.  
     - planAvoidCollision() para manobras de evasão de obstáculos.  
     - planFollowMission() para seguir missão ou pontos de interesse.  
  3. Executa controle dos atuadores usando thrust.setControl(...).  

## Observações
- O código atual é um esqueleto inicial, focado em arquitetura e interfaces.  
- Planeja-se integração com Jadex para modelar agentes e planos reais.  
- Futuramente, é possível adicionar múltiplos drones, missões complexas e física mais detalhada.

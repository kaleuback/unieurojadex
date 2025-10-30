# 🛰️ Unieuro Jadex - Simulador de Drones

Este projeto é um **simulador de drones em Java**, utilizando uma arquitetura inspirada em **agentes inteligentes (Jadex-style)**, com sensores, atuadores e um mundo simulado.

## 🚀 Execução da Simulação

Para executar o projeto com Maven:

```bash
apt update
apt install maven -y
mvn -v
mvn exec:java -Dexec.mainClass="br.univ.drone.Main"
Durante a execução, três cenários automáticos são simulados:

Missão Normal (bateria cheia)
O drone levanta voo e segue uma missão de reconhecimento.

Bateria Crítica (5%)
O drone detecta bateria baixa e inicia um pouso de emergência.

Obstáculo Detectado
O drone executa uma manobra evasiva para evitar colisão.

Exemplo de saída:



=== Iniciando Simulador de Drones ===
[WORLD] Drone registrado.

--- CENÁRIO 1: Missão normal ---
[PLAN] Seguindo missão: avançando e escaneando área.

--- CENÁRIO 2: Bateria crítica ---
[PLAN] Pouso de emergência iniciado!

--- CENÁRIO 3: Obstáculo ---
[PLAN] Evitando colisão: manobra evasiva.

=== Simulação finalizada ===

Cenário 1: Drone em missão com bateria 80%.

Cenário 2: Pouso automático por bateria fraca.

Cenário 3: Evasão de obstáculo detectado.

🧩 Estrutura do Projeto
1. Main.java
Local: src/br/main/java/br

Ponto de entrada do programa.

Cria o mundo simulado (World) e inicializa os agentes (DroneAgent).

Executa os cenários de teste da simulação.

2. Sensores
GpsSensor
Local: src/br/univ/drone/sensors/GpsSensor.java

Fornece a posição [x, y, z] do drone.

AltitudeSensor
Local: src/br/univ/drone/sensors/AltitudeSensor.java

Retorna a altitude atual do drone.

LidarSensor
Local: src/br/univ/drone/sensors/LidarSensor.java

Detecta obstáculos em diferentes ângulos.

Retorna -1 se não houver obstáculo.

3. Atuadores
ThrustActuator
Local: src/br/univ/drone/actuators/ThrustActuator.java

Controla potência e movimento do drone (throttle, yaw, pitch e roll).

4. Mundo Simulado
World
Local: src/br/univ/drone/simulator/World.java

Gerencia o ambiente e a posição dos drones.

Aplica física simplificada e atualiza sensores a cada iteração.

registerDrone(drone) registra um novo agente no mundo.

5. Agente de Drone
DroneAgent
Local: src/br/univ/drone/agents/DroneAgent.java

Representa o agente principal (o drone).

Mantém crenças internas:

Posição, bateria e proximidade de obstáculos.

Executa o ciclo de percepção, decisão e ação:

sense() → lê sensores.

Planeja ação:

planFollowMission() – segue missão normal.

planAvoidCollision() – evita obstáculos.

planEmergencyLand() – pousa quando a bateria está baixa.

thrust.setControl(...) → aplica comandos de voo.

💡 Observações
O código agora simula três cenários automáticos com base nas crenças do agente.

Estrutura inspirada em Sistemas Multiagentes (Jadex).

Próximos passos:

Adicionar múltiplos drones.

Integrar com Jadex real para planos e crenças modeladas.

Visualização gráfica da simulação.

execute o comando:

bash

mvn exec:java -Dexec.mainClass="br.univ.drone.Main"


Cenário 1: Missão normal (bateria alta, sem obstáculos).

Cenário 2: Bateria crítica → drone faz pouso de emergência.

Cenário 3: Obstáculo detectado → manobra evasiva.


“Esse simulador demonstra a arquitetura de agentes, onde o drone percebe o ambiente, atualiza suas crenças e age de forma autônoma — conceito central em sistemas multiagentes e IA distribuída.”


Esta sección describe como ejecutar YCSB en "KeyValueStore".

## 1.- Instalar Java y Maven

## 2.- Clonar el proyecto "KeyValueStore"
* git clone https://github.com/Sixto-Castro93/Proyecto-Parcial-Operativos

* Para poder ejecutar el servidor de ese proyecto, seguir las instrucciones del readme del mismo en la sección "EJECUCIÓN EN LINUX"


## 3.- YCSB

Git clone YCSB y compilar:

* git clone https://github.com/JordyVasquez/YCSB

* cd YCSB

* mvn -pl com.yahoo.ycsb:KeyValueStore-binding -am clean package


## 4.- Fase de carga y ejecutar test

Fase de carga:

./bin/ycsb load KeyValueStore -s -P workloads/workloada -p "KeyValueStore.host=127.0.0.1" -p "KeyValueStore.port=1000" > outputLoad.txt

Ejecutar el test (Workload):

./bin/ycsb run KeyValueStore -s -P workloads/workloada > outputRun.txt


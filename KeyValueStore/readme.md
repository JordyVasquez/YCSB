Quick Start

This section describes how to run YCSB on KeyValueStore.

1. Install Java and Maven

2. Set Up YCSB

Git clone YCSB and compile:

git clone https://github.com/JordyVasquez/YCSB
cd YCSB
mvn -pl com.yahoo.ycsb:redis-binding -am clean package

3. Clone project "KeyValueStore"

git clone https://github.com/Sixto-Castro93/Proyecto-Parcial-Operativos

and for running the server follow pass from "readme", section "EJECUCIÓN EN LINUX"

4. Load data and run test

Load the data:

./bin/ycsb load KeyValueStore -s -P workloads/workloada -p "KeyValueStore.host=127.0.0.1" -p "KeyValueStore.port=1000" > outputLoad.txt

Run the workload test:

./bin/ycsb run KeyValueStore -s -P workloads/workloada > outputRun.txt


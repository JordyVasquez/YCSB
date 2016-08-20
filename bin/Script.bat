@echo off
setlocal enabledelayedexpansion
cd ..
set pat=%cd%
cd bin
echo Mediciones;2016> estadisticas.txt
echo Mediciones:> estadisticas_result.txt
set/a numeroHilo=1
set/a Base=100000
set/a BaseOperationa=100000
set/a AumentoOperaciones=100000
set/a Hilos=1
set /a repeticiones=5
 for /L %%z in (1, 1, 5) do (
 for /L %%y in (1, 1, 5) do (
 for /L %%x in (1, 1, %repeticiones%) do ( 
START /B/WAIT CMD /C ycsb run redis -s -P %pat%\workloads\workloada -p redis.host=127.0.0.1 -p redis.port=6379 -p threadcount=!Hilos! -p operationcount=!BaseOperationa! > Salida_Ejecucion.txt
rem echo Salida de redis con # de operaciones !BaseOperationa! -- la vez %%x con hilos !Hilos!>> resultado.txt
FIND /i "RunTime" < Salida_Ejecucion.txt > resultado.txt
 FIND /i "Operations" < Salida_Ejecucion.txt >> resultado.txt
 FIND /i "AverageLatency" < Salida_Ejecucion.txt >> resultado.txt
 for /F "tokens=1,2,3 delims=, " %%a in (resultado.txt) do (
	rem set /a numero%%a=!numero%%a!+%%c
	echo redis_!Hilos!_!BaseOperationa!_%%a_%%b;%%c >> estadisticas.txt
)
rem echo Salida de KeyValuesStore # de operaciones !BaseOperationa! -- la vez %%x con hilos !Hilos!>> resultado.txt
START /B/WAIT CMD /C ycsb run KeyValueStore -s -P %pat%\workloads\workloada -p KeyValueStore.host=127.0.0.1 -p KeyValueStore.port=1000 -p threadcount=!Hilos! -p operationcount=!BaseOperationa! > Salida_Ejecucion.txt
FIND /i "RunTime" < Salida_Ejecucion.txt > resultado.txt
FIND /i "Operations" < Salida_Ejecucion.txt >> resultado.txt
FIND /i "AverageLatency" < Salida_Ejecucion.txt >> resultado.txt
 for /F "tokens=1,2,3 delims=, " %%a in (resultado.txt) do (
	rem set /a numero%%a=!numero%%a!+%%c
	echo KeyValueStore_!Hilos!_!BaseOperationa!_%%a_%%b;%%c >> estadisticas.txt
)
)
set /a BaseOperationa= !BaseOperationa! + %AumentoOperaciones%
)
set /a BaseOperationa=%Base%
set/a Hilos= !Hilos! + 1
)
sort <estadisticas.txt > estadisticas2.txt
set var=0
set  name=vacio
set con=0
FOR /F "tokens=1,2 delims=;" %%a IN (estadisticas2.txt) DO (
if !var! ==0 (
echo !var! %%a %%b
echo !var!
set con= %%b
set name=%%a
) 
if !var! ==1 ( 
	if !name!==%%a (
		set tempo=%%b
		set /a "con+= !tempo!" 2> errores.txt
	) 
	if not !name!==%%a ( 
set /a con= !con!/%repeticiones%
echo !name!=!con! >> estadisticas_result.txt
set name=%%a
set con=%%b
	) 
)
set /a var=1
)
set /a con= !con!/%repeticiones%
echo !name!=!con! >> estadisticas_result.txt
rem DEL estadisticas2.txt
DEL errores.txt
DEL Salida_Ejecucion.txt
DEL resultado.txt
DEL estadisticas.txt
pause >nul
exit 
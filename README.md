###Boxever Project:
On Unix based operating system run:
```
chmod +x gradlew
```
On Windows operating system use ```gradlew.bat``` instead of ```./gradlew``` command.  
* To build the project run on Unix based systems:
```
./gradlew jar
```
* To run the project execute:
```
java -jar ./build/libs/airline-challenge-1.0-SNAPSHOT.jar AIRPORT_ORIGIN AIRPORT_DESTINY
```
Where ```AIRPORT_ORIGIN``` and ```AIRPORT_DESTINY``` are airport codes for the departure from origin and arrival to destiny respectively. 
* Example:
```
java -jar ./build/libs/airline-challenge-1.0-SNAPSHOT.jar DUB SYD
DUB -- LHR (1)
LHR -- BKK (9)
BKK -- SYD (11)
time: 21
```
It is also possible to run executing the gradle command without build a .jar file:
```
./gradlew run --args "AIRPORT_ORIGIN AIRPORT_DESTINY"
```
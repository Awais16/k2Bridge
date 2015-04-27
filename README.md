# k^2 Bridge

Bridge between Kinect API's (C#) and Java to be used in KUKA's robotic API.

BodyBasic WPF: Body Stream + RPC
	thrift for RPC
	HandsService [provinding hands location and state]

### still have to look into

CoordinateMapper of kinect: MapCameraPointToDepthSpace

send all body joints;

server side invocation for gestures (leave thrift move to grpc)

### Limitation for kuka
can't mount on robot head because you need complete body/sitting posture for sensor to recognize hands.

### Dependencies

* kinect v2 for windows
* Kinect V2 sdk
* thrift libs
* jdk ;)



## Installation

* Run BodyBasicWPF (with rpc server as well)
* Use Jar file in your project.
	1. include K2BLib.jar from K2BLib/dist/
	2. include libthrift.jar (gan be build from apace thrift source)
	3. and 
```java
k2bClient client = new k2bClient(); //client object;
client.connect("localhost", 9090); //connect(string host,int port)
Hands hands= client.getHands();
```


### generating stubs

$> thrift.exe --gen java hands.thrift

$> thrift.exe --gen csharp hands.thrift



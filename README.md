# k^2 Bridge


###still have to look into

CoordinateMapper of kinect: MapCameraPointToDepthSpace

send all body joints;

server side invocation for gestures (leave thrift move grpc)

###Limitation for kuka
can't mount on robot head because you need complete body/sitting posture for sensor to recognize hands.

###Dependencies

* kinect v2 for windows
* Kinect V2 sdk
* thrift libs
* v2
* java ;)



##Installation

* Run BodyBasicWPF (will rpc server as well)
* Use Jar file in your project.
	1. Create Class object
	2. bla bla bla

###generating stubs

$> thrift.exe --gen java hands.thrift

$> thrift.exe --gen csharp hands.thrift



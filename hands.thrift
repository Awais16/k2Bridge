#GPL2
#K2Bridge
#Awais (creative technologies FH)


namespace java k2Bridge
namespace csharp k2Bridge

enum HandState {
	CLOSED=0,
	OPEN=1,
	LASSO=2
}

enum TrackingState{
	Inferred=0,
	Tracked=1
}

struct Hand{
	1: double x,
	2: double y,
	3: HandState handState,
	4:TrackingState trackingState
}

struct Hands {
	1:Hand left,
	2:Hand right
}

service HandService{
	Hands getHands()
}
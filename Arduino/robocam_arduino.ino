#include <AccelStepper.h>
#include <AFMotor.h>

AF_Stepper motor1(200, 1);
AF_Stepper motor2(200, 2);

String inputString = "";
boolean stringComplete = false;


void forwardstep1() {  
  motor1.onestep(FORWARD, DOUBLE);
}

void backwardstep1() {  
  motor1.onestep(BACKWARD, DOUBLE);
}
// wrappers for the second motor!
void forwardstep2() {  
  motor2.onestep(FORWARD, DOUBLE);
}
void backwardstep2() {  
  motor2.onestep(BACKWARD, DOUBLE);
}

AccelStepper stepper1(forwardstep1, backwardstep1);
AccelStepper stepper2(forwardstep2, backwardstep2);



void setup() {
  // initialize serial:
  Serial.begin(9600);
  // reserve 200 bytes for the inputString:
  inputString.reserve(200);
  
  
  stepper1.setMaxSpeed(300.0);
    stepper1.setAcceleration(100.0);
    
    stepper2.setMaxSpeed(300.0);
    stepper2.setAcceleration(100.0);
}


void loop() {
   if (stringComplete) {
    Serial.println(inputString); 
    // clear the string:
    inputString = "";
    stringComplete = false;
  } 

stepper1.run();
stepper2.run();

}


void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read(); 
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      stringComplete = true;
    }
   
   if (inChar == 'w') {
  stepper1.moveTo(stepper1.currentPosition() + 3);
  
 
} 
if (inChar == 's') {
    stepper1.moveTo(stepper1.currentPosition() - 3);
  
    
  }
  
  if (inChar == 'a') {
  stepper2.moveTo(stepper2.currentPosition() + 3);
  
  
 }
 
 if (inChar == 'd') {
    stepper2.moveTo(stepper2.currentPosition() - 3);
  
    
   }
   
   
  }
}





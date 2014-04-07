import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class robocam extends PApplet {




Capture cam;

Serial myPort;
int val;

float counter;
PImage img;

boolean rotate = false;


public void setup() {

  size(640, 480, P3D);

  String[] cameras = Capture.list();
  if (cameras.length == 0) {
    println("There are no cameras available for capture.");
    exit();
  } 

  else {
    println("Available cameras:");
    for (int i = 0; i < cameras.length; i++) {
      println(cameras[i]);
    }
    // usb webcam on my hp was ~20 
    cam = new Capture(this, cameras[20]);
    // Or, the settings can be defined based on the text in the list
    //cam = new Capture(this, 640, 480, "Built-in iSight", 30);

    // Start capturing the images from the camera
    cam.start();

    String portName = Serial.list()[0];

    myPort = new Serial(this, portName, 9600);


    counter=0.0f;
    img=cam;
  }
}



public void draw() {


  background(0);

  // counter++;

  if (cam.available() == true) {
    cam.read();
  }

// Rotate the image if needed
  if (rotate == true) {
    translate(width-img.width/2, height-img.height/2);

    rotateX(180*TWO_PI/360);
    translate(-img.width/2, -img.height/2);
    image(img, 0, 0);
  }


  else {

    image(cam, 0, 0);
  }
}


public void keyPressed() {

  if (key == 'w') {
    myPort.write('w');
    myPort.write("/n");
  }
  if ( key == 's') {

    myPort.write('s');
    myPort.write("/n");
  }

  if ( key == 'a') {

    myPort.write('a');
    myPort.write("/n");
  }

  if ( key == 'd') {

    myPort.write('d');
    myPort.write("/n");
  }

  if (key == 'r') {

    if (rotate == false) {

      rotate = true;
    }
    else {
      rotate = false;
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "robocam" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

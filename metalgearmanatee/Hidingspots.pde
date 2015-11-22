class Hidingspots {                                                              //Class definition for Hidingspots class
  int _x;                                                                        //Declare variable _x
  int _y;                                                                        //Declare variable _y
  int _t;                                                                        //Declare variable _t
  PImage [] hidespotAnimation = new PImage[2];                                   //Create a new animation array

  Hidingspots() {                                                                //Constructor for the Hidingspots class
    _x = 500;                                                                    //Set the initial x position of the cardboard box
    _y = 250;                                                                    //Set the initial y position of the cardboard box
    _t=0;                                                                        //Set the timer to 0
    hidespotAnimation[0] = loadImage("cardboardbox.png");                        //Load the first picture in the hide animation (The animation is the cardboard box blinking to show that it is about to move to a new location)
    hidespotAnimation[1] = loadImage("cardboardbox1.png");                       //Load the second picture in the hide animation
    cardboardBox = loadImage("cardboardbox.png");                                //Load the image of the cardboard box - used for displaying and also colision testing
  }                                                                              //Close the constructor

  void display() {                                                               //Function that displays the hiding place
    if (_t < 420) {                                                              //If the timer is under 420 (420/60 = 7 seconds)
      image(cardboardBox, _x, _y);                                               //Display the cardboard box
    } else {                                                                     //When the timer is over 420
      twoPictureAnimation(hidespotAnimation, _x, _y);                            //Display the blinking animation
    }                                                                            //Close the else statement
  }                                                                              //Close the function

  void randomizer() {                                                            //Function that randomizes the position of the cardboardbox
    _t += 1;                                                                     //Add one to the timer
    if (_t > 600) {                                                              //When the timer hits 600
      _x = int(random(cardboardBox.width/2, width - cardboardBox.width/2));      //Change the x position to a random position
      _y = int(random(cardboardBox.height/2, height - cardboardBox.height/2));   //Change the y position to a random position
      _t = 0;                                                                    //Reset the timer
    }                                                                            //Close the if statement
  }                                                                              //Close the function

  int returnHideX() {                                                            //Function that returns integer
    return _x;                                                                   //Return the current x position
  }                                                                              //Close the function

  int returnHideY() {                                                            //Function that returns an integer
    return _y;                                                                   //Return the y position
  }                                                                              //Close the function

  void resetValues() {                                                           //Function that resets the values
    _x = 500;                                                                    //Reset the x position
    _y = 250;                                                                    //Reset the y position
    _t=0;                                                                        //Reset the timer
  }                                                                              //Close the function
}                                                                                //Close the class definition


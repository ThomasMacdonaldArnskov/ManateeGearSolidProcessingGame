class Health {                                                                              //Class definition for the Health class

  int _health;                                                                              //Declare health variable
  PImage _fullhealth;                                                                       //Declare the image shown with full health
  PImage _2health;                                                                          //Declare the image shown with 2 health
  PImage _1health;                                                                          //Declare the image shown with 1 health
  PImage _0health;                                                                          //Declare the image shown with 0 health

  Health () {                                                                              //Health class constructor
    _health = 3;                                                                           //Sets starting health to 3
    _fullhealth = loadImage("fullhealth.png");                                             //Load image for full health
    _2health = loadImage("2health.png");                                                   //Load image for 2 health
    _1health = loadImage("1health.png");                                                   //Load image for 1 health
    _0health = loadImage("0health.png");                                                   //Load image for 0 health
  }                                                                                        //Close the health constructor

  void display() {                                                                         //Display function for the health class
    if (_health == 3) {                                                                    //If health equals 3
      image(_fullhealth, width - _fullhealth.width/2 -10, _fullhealth.height/2 + 10);      //Display full health picture - located in the top right corner
    } else if (_health == 2) {                                                             //Else if the health equals 2
      image(_2health, width - _2health.width/2 -10, _fullhealth.height/2 + 10);            //Show image for 2 health
    } else if (_health == 1) {                                                             //Else if the health equals 1
      image(_1health, width - _1health.width/2 -10, _fullhealth.height/2 + 10);            //Display Image for health 1
    } else {                                                                               //Else (if health is 0)
      image(_0health, width - _0health.width/2 -10, _fullhealth.height/2 + 10);            //Display 0 health image
      current = gameOver;                                                                  //And set the current state of the game to game over
    }                                                                                      //Close the else statement
  }                                                                                        //Close the display function

  void healthCalculator() {                                                                //Function that calculates health when hit
    _health -= 1;                                                                          //If the function is called 1 health is subtracted
  }                                                                                        //Close the function

  int returnHealth() {                                                                     //Function that returns the current health so that it can be used everywhere in the game
    return _health;                                                                        //Returns an integer
  }                                                                                        //Close the function

  void resetValues() {                                                                     //Function that resets the value of the health variable
    _health = 3;                                                                           //Health is set to 3
  }                                                                                        //Close the function
}                                                                                          //Close the class definition


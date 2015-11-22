class Mantaray {                                                                //Class definitoin of the Mantaray class

  float _x;                                                                     //Declare float for the x position
  float _y;                                                                     //Declare float for the y position
  int _speed;                                                                   //Declare integer for the speed
  int _t;                                                                       //Declare integer for the movement timer
  int _deathTimer;                                                              //Declare integer for the death timer
  PImage [] mantaAnimation = new PImage[2];                                     //Create new PImage array - movement animation
  PImage [] mantaAnimationR = new PImage[2];                                    //Create new PImage array - flipped movement animation
  PImage [] mantaDeathAnimation = new PImage[6];                                //Create new PImage array - death animation
  PImage [] mantaDeathAnimationR = new PImage[6];                               //Create new PImage array - flipped death animation
  PImage mantaScreen;                                                           //Declare PImage for the death screen of the mantaray


  Mantaray() {                                                                  //Mantaray class constructor
    enemyManta = loadImage("mantaray.png");                                     //Load image for the mantaray
    mantaAnimation[0] = loadImage("mantaray.png");                              //Load image for mantaray movement and set it to first point in animation array
    mantaAnimation[1] = loadImage("mantaray1.png");                             //Load image for mantaray movement and set it to second point in animation array
    mantaAnimationR[0] = loadImage("mantarayR.png");                            //Load image for mantaray movement and set it to first point in flipped animation array
    mantaAnimationR[1] = loadImage("mantaray1R.png");                           //Load image for mantaray movement and set it to second point in flipped animation array
    mantaScreen = loadImage("mantadeathscreen.jpg");                            //Load image for the mantaray death screen
    for (int i = 0; i < mantaDeathAnimation.length; i++) {                      //For loop that initialises the deathanimation
      mantaDeathAnimation[i] = loadImage("mantadeathanimation"+i+".png");       //Conviently named so that a for loop can be used
    }                                                                           //Close for loop
    for (int i = 0; i < mantaDeathAnimationR.length; i++) {                     //For loop that initialises the flipped deathanimation
      mantaDeathAnimationR[i] = loadImage("mantadeathanimation"+i+"R.png");     //Conviently named so that a for loop can be used
    }                                                                           //Close for loop
    _x = width - enemyManta.width/2;                                            //Set the x starting position
    _y = height - enemyManta.height/2;                                          //Set the y starting position
    _speed = 1;                                                                 //Set the starting speed
    _t = 0;                                                                     //Set speed timer to 0
    _deathTimer = 0;                                                            //Set the death timer to 0
  }                                                                             //Close the constructor

  void display() {                                                              //Function that displays the mantaray
    if (_speed < 0) {                                                           //When the mantaray is moving to the left
      twoPictureAnimation(mantaAnimationR, _x, _y);                             //Display the flipped movement animation
    } else {                                                                    //When the mantaray is moving to the right
      twoPictureAnimation(mantaAnimation, _x, _y);                              //Display the movement animation
    }                                                                           //Close the else statement
  }                                                                             //Close the function

  void movement() {                                                             //Function that moves the mantaray
    if (_x >= width - enemyManta.width/2) {                                     //If the mantaray hits the right edge
      _speed *= -1.00;                                                          //Reverse the speed
    } else if (_x <= enemyManta.width/2) {                                      //If the mantaray hits the left edge
      _speed = abs(_speed);                                                     //Reverse the speed
    }                                                                           //Close the else if statement
    _x += _speed;                                                               //Speed is added to the x position
    _y = (768.0/1366.0)*_x;                                                     //Mathematical formular for points in a line - y=mx+b
    _t +=1;                                                                     //Add one to the timer
    if (_t > 900 && _speed > 0) {                                               //When the timer hits 900 and the mantaray is moving right
      _speed += 1;                                                              //Add one to the speed
      _t = 0;                                                                   //Reset the timer
    } else if (_t > 900 && _speed < 0) {                                        //When the timer hits 900 and the mantaray is moving left
      _speed -= 1;                                                              //Subtract one from the speed
      _t = 0;                                                                   //Reset the timer
    }                                                                           //Close else if statement
  }                                                                             //Close the function

  void mantaDeath() {                                                           //Function that displays death by mantaray
    _deathTimer += 1;                                                           //Timer adds one
    if (_deathTimer < 90) {                                                     //When the timer is under 90
      if (mantaSpeed < 0 && mantaX > pointX ) {                                 //If the mantaray is moving left and it is positioned to the right of the hero
        deathAnimation(mantaDeathAnimation, mantaX-100, mantaY-90);             //Display death animation - adjusted for size differences in pictures
      } else if (mantaSpeed > 0 && mantaX < pointX) {                           //If the mantaray is moving right and it is positioned to the left of the hero
        deathAnimation(mantaDeathAnimationR, mantaX+40, mantaY-100);            //Display flipped death animation - adjusted for size differences in pictures
      } else if (mantaSpeed < 0 && mantaX < pointX) {                           //If the mantaray is moving left and it is positioned to the left of the hero
        deathAnimation(mantaDeathAnimationR, mantaX+40, mantaY-100);            //Display flipped death animation - adjusted for size differences in pictures
      } else {                                                                  //If the mantaray is moving right and it is positioned to the right of the hero
        deathAnimation(mantaDeathAnimation, mantaX-100, mantaY-90);             //Display death animation - adjusted for size differences in pictures
      }                                                                         //Close the else statement
    } else if (_deathTimer > 90 && _deathTimer < 130) {                         //When the timer is between 90 and 130
      if (mantaSpeed < 0 && mantaX > pointX ) {                                 //If the mantaray is moving left and it is positioned to the right of the hero
        image(mantaDeathAnimation[5], mantaX-100, mantaY-90);                   //Display last picture in array - so that death lingers
      } else if (mantaSpeed > 0 && mantaX < pointX) {                           //If the mantaray is moving right and it is positioned to the left of the hero
        image(mantaDeathAnimationR[5], mantaX+40, mantaY-100);                  //Display last picture in array - so that death lingers
      } else if (mantaSpeed < 0 && mantaX < pointX) {                           //If the mantaray is moving left and it is positioned to the left of the hero
        image(mantaDeathAnimationR[5], mantaX+40, mantaY-100);                  //Display last picture in array - so that death lingers
      } else {                                                                  //If the mantaray is moving right and it is positioned to the right of the hero
        image(mantaDeathAnimation[5], mantaX-100, mantaY-90);                   //Display last picture in array - so that death lingers
      }                                                                         //Close the else statement
    } else if (_deathTimer > 130 && _deathTimer < 300) {                        //When the timer is between 130 and 300
image (mantaScreen, width/2, height/2);                                         //Show the mantaray death screen
    } else if (_deathTimer >= 300) {                                            //When the timer hits 300
      shark.resetValues();                                                      //Reset shark values
      octo.resetValues();                                                       //Reset octopus values
      manta.resetValues();                                                      //Reset mantaray values
      killer.resetValues();                                                     //Reset orca values
      health.healthCalculator();                                                //Calculate health
      heroSafe = true;                                                          //Set hero state to safe
      manatee.isHeroSafe(heroSafe);                                             //And parse it through the isHeroSafe function
      _deathTimer = 0;                                                          //Reset the death timer
      manatee.movement();                                                       //And allow the hero to move again
    }                                                                           //Close the else if statement
  }                                                                             //Close the function

  float returnMantaX() {                                                        //Function that returns float
    return _x;                                                                  //Return the x position
  }                                                                             //Close the function

  float returnMantaY() {                                                        //Function that returns float
    return _y;                                                                  //Return the y position
  }                                                                             //Close the function

  int returnMantaSpeed() {                                                      //Function that returns integer
    return _speed;                                                              //Returns the speed of the mantaray
  }                                                                             //Close the function

  void resetValues() {                                                          //Function that resets the values
    _x = width - enemyManta.width/2;                                            //Set the x position
    _y = height - enemyManta.height/2;                                          //Set the y position
    _speed = 1;                                                                 //Set the speed
  }                                                                             //Close the function
}                                                                               //Close the class definition


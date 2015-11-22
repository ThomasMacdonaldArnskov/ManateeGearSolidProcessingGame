class Whale {                                                                          //Class description for the Whale class

  float _x;                                                                            //Declaring variable for the position on the x axis
  float _y;                                                                            //Declaring variable for the position on the y axis
  int _speed;                                                                          //Declaring varibale for the speed
  int _t;                                                                              //Declaring variable for the speed timer
  int _deathTimer;                                                                     //Declaring variable for the death timer
  PImage whaleScreen;                                                                  //Declaring variable for the whale death screen
  PImage [] whaleAnimation = new PImage[2];                                            //Creating new PImage array for the movement animation
  PImage [] whaleAnimationR = new PImage[2];                                           //Creating new PImage array for the flipped movement animation
  PImage [] orcaDeathAnimation = new PImage[6];                                        //Creating new PImage array for the death animation
  PImage [] orcaDeathAnimationR = new PImage[6];                                       //Creating new PImage array for the flipped death animation
  
  Whale() {                                                                            //Constructor for the whale class
    enemyWhale = loadImage("orcasmall.png");                                           //initialise image for the orca
    whaleAnimation[0] = loadImage("orcasmall.png");                                    //initialise image for the first point in the animation array
    whaleAnimation[1] = loadImage("orcasmall1.png");                                   //initialise image for the second point in the animation array
    whaleAnimationR[0] = loadImage("orcasmallR.png");                                  //initialise image for the first point in the flipped animation array
    whaleAnimationR[1] = loadImage("orcasmall1R.png");                                 //initialise image for the second point in the flipped animation array
    whaleScreen = loadImage("whaledeathscreen.jpg");                                   //initialise image for the whale death screen
    for (int i = 0; i < orcaDeathAnimation.length; i++) {                              //for loop that initialises every image in the death animation
      orcaDeathAnimation[i] = loadImage("orcadeathanimation"+i+".png");                //conviently named to allow the use of a for-loop
    }                                                                                  //closing the for loop

    for (int i = 0; i < orcaDeathAnimationR.length; i++) {                             //for loop that initialises every image in the flipped death animation
      orcaDeathAnimationR[i] = loadImage("orcadeathanimation"+i+"R.png");              //conviently named to allow the use of a for-loop
    }                                                                                  //closing for loop
    _x = width - enemyWhale.width/2;                                                   //set the starting point on the x axis
    _y = enemyWhale.height/2;                                                          //set the starting point on the y axis
    _speed = 1;                                                                        //set the starting speed
    _deathTimer = 0;                                                                   //set the death timer to 0
    _t = 0;                                                                            //set the speed timer to 0
  }                                                                                    //closing the constructor

  void display() {                                                                     //function that displays the orca
    if (_speed < 0) {                                                                  //If the orca is moving to the left
      twoPictureAnimation(whaleAnimationR, _x, _y);                                    //display the flipped movement animation
    } else {                                                                           //if the orca is moving to the right
      twoPictureAnimation(whaleAnimation, _x, _y);                                     //display the movement animation
    }                                                                                  //close the else statement
  }                                                                                    //close the function

  void movement() {                                                                    //function that moves the orca
    if (_x >= width - enemyWhale.width/2) {                                            //if the orca hits the right edge of the screen
      _speed *= -1;                                                                    //reverse the speed
    } else if (_x <= enemyWhale.width/2) {                                             //if the orca hits the left edge of the screen
      _speed = abs(_speed);                                                            //reverse the speed
    }                                                                                  //close the else if statement
    _x += _speed;                                                                      //add the speed to the x coordinate
    _y = (-768.0/1366.0)*_x + 768.0;                                                   //Mathematical formula for coordinates on a line - y=mx+b
    _t += 1;                                                                           //add one to the timer
    if (_t > 900 && _speed > 0) {                                                      //if the speed timer hits 900 and the orca is moving to the right
      _speed += 1;                                                                     //add one to the speed
      _t = 0;                                                                          //reset the timer
    } else if (_t > 900 && _speed < 0) {                                               //if the speed timer hits 900 and the orca is moving to the left
      _speed -= 1;                                                                     //subtract one from the speed
      _t = 0;                                                                          //reset the timer
    }                                                                                  //close the else if statement
  }                                                                                    //close the function

  void whaleDeath() {                                                                  //function that displays the death animation for death by orca
    _deathTimer += 1;                                                                  //add one to the death timer
    if (_deathTimer < 90) {                                                            //if the death timer is under 90
      if (whaleSpeed < 0 && whaleX > pointX ) {                                        //if the orca is moving to the left and the orca is to the right of the hero
        deathAnimation(orcaDeathAnimation, whaleX - 40, whaleY);                       //display the death animation
      } else if (whaleSpeed > 0 && whaleX < pointX) {                                  //if the orca is moving to the right and the orca is to the left of the hero
        deathAnimation(orcaDeathAnimationR, whaleX + 40, whaleY);                      //display the flipped death animation
      } else if (whaleSpeed < 0 && whaleX < pointX) {                                  //if the orca is moving to the left and the orca is to the left of the hero
        deathAnimation(orcaDeathAnimationR, whaleX + 40, whaleY);                      //display the flipped death animation
      } else {                                                                         //if the orca is moving to the right and the orca is to the right of the hero
        deathAnimation(orcaDeathAnimation, whaleX - 40, whaleY);                       //display the death animation
      }                                                                                //close the else statement
    } else if (_deathTimer > 90 && _deathTimer < 130) {                                //if the death timer is between 90 and 130
      if (whaleSpeed < 0 && whaleX > pointX ) {                                        //if the orca is moving to the left and the orca is to the right of the hero
        image(orcaDeathAnimation[5], whaleX - 40, whaleY);                             //display the last picture in the array
      } else if (whaleSpeed > 0 && whaleX < pointX) {                                  //if the orca is moving to the right and the orca is to the left of the hero
        image(orcaDeathAnimationR[5], whaleX + 40, whaleY);                            //display the last picture in the array
      } else if (whaleSpeed < 0 && whaleX < pointX) {                                  //if the orca is moving to the left and the orca is to the left of the hero
        image(orcaDeathAnimationR[5], whaleX + 40, whaleY);                            //display the last picture in the array
      } else {                                                                         //if the orca is moving to the right and the orca is to the right of the hero
        image(orcaDeathAnimation[5], whaleX - 40, whaleY);                             //display the last picture in the array
      }                                                                                //close the else statement
    } else if (_deathTimer > 130 && _deathTimer < 300) {                               //if the death timer is between 130 and 300
    image(whaleScreen,width/2,height/2);                                               //display the death screen for death by orca
    } else if (_deathTimer >= 300) {                                                   //if the death timer hits 300
      shark.resetValues();                                                             //reset the values
      octo.resetValues();                                                              //reset the values
      manta.resetValues();                                                             //reset the values
      killer.resetValues();                                                            //reset the values
      health.healthCalculator();                                                       //calculate the health
      heroSafe = true;                                                                 //set hero state to safe
      manatee.isHeroSafe(heroSafe);                                                    //pass it to the isHeroSafe function
      _deathTimer = 0;                                                                 //reset the death timer
      manatee.movement();                                                              //allow the hero to move again
    }                                                                                  //close the else if statement
  }                                                                                    //close the function

  float returnWhaleX() {                                                               //function that returns a floating value
    return _x;                                                                         //return the x position
  }                                                                                    //close the function
  
  float returnWhaleY() {                                                               //function that returns a floating value
    return _y;                                                                         //return the y position
  }                                                                                    //close the function

  int returnWhaleSpeed() {                                                             //function that returns an integer
    return _speed;                                                                     //return the current speed
  }                                                                                    //close the function

  void resetValues() {                                                                 //function that resets the values
    _x = width - enemyWhale.width/2;                                                   //set the x position
    _y = enemyWhale.height/2;                                                          //set the y position
    _speed = 1;                                                                        //set the speed back to 1
  }                                                                                    //close the function
}                                                                                      //close the class description


class Enemy {                                                                   //Class definition of the shark type enemy.

  int _x;                                                                       //Declaring variable for the position on the x axis.
  int _y;                                                                       //Declaring variable for the positioin on the y axis
  int _t;                                                                       //Declaring variable for the timer that increases the speed of the enemies
  int _deathTimer;                                                              //Declaring variable for the timer that allows the deathanimation to play out
  int _speed;                                                                   //Declaring variable for the speed of the shark
  PImage [] sharkAnimation = new PImage[2];                                     //Creating new array that displays the movement animation when the mouse is moved to the right
  PImage [] sharkAnimationR = new PImage[2];                                    //Creating new array that displays the movement animation when the mouse is moved to the left
  PImage [] sharkDeathAnimation = new PImage[6];                                //Creating new array that displays the death animation when the hero hits the shark
  PImage [] sharkDeathAnimationR = new PImage[6];                               //Creating new array that displays the flipped death animation
  PImage sharkScreen;                                                           //Declaring the variable that displays the deathscreen for the shark

  Enemy () {                                                                    //The constructor for the class
    _x = width/2;                                                               //Initialising the x starting position of the shark
    _y = height/2;                                                              //Initialising the y starting position of the shark
    _speed = 1;                                                                 //Initialising the starting speed
    _t = 0;                                                                     //Initialising the timer that increases the speed of the shark
    _deathTimer = 0;                                                            //Initialising the timer that allows the death animation to play out
    sharkAnimation[0] = loadImage("shark.png");                                 //Initialising the first point in the array that displays the movement
    sharkAnimation[1] = loadImage("shark1.png");                                //Initialising the second point in the array that displays the movement
    sharkAnimationR[0] = loadImage("sharkR.png");                               //Initialising the first point in the array that displays the reversed movement
    sharkAnimationR[1] = loadImage("shark1R.png");                              //Initialising the second point in the array that displays the reversed movement
    sharkpic = loadImage("shark.png");                                          //Initialising the image of the shark - Used for colission testing
    sharkScreen = loadImage("sharkdeathscreen.jpg");                            //Initialising the picture that is displayed when you die by shark
    for (int i = 0; i < sharkDeathAnimation.length; i++) {                      //For-loop that initialises the death animation
      sharkDeathAnimation[i] = loadImage("sharkdeathanimation"+i+".png");       //The names of the pictures are conviently named to allow for the use of a loop
    }                                                                           //Closing the for-loop
    for (int i = 0; i < sharkDeathAnimationR.length; i++) {                     //For-loop that initialises the flipped death animation
      sharkDeathAnimationR[i] = loadImage("sharkdeathanimation"+i+"R.png");     //The names of the pictures are conviently named to allow for the use of a loop
    }                                                                           //Closing the for-loop
  }                                                                             //Closing the constructor

  void display() {                                                              //The function that displays the shark
    if (_speed < 0) {                                                           //If the speed is under 0 the shark is moving to the left
      twoPictureAnimation(sharkAnimation, _x, _y);                              //Using the 2 picture animation function to display the shark movement animation
    } else {                                                                    //Else if the speed is over 0 the shark is moving to the right
      twoPictureAnimation(sharkAnimationR, _x, _y);                             //Using the 2 picture animation function to display the reversed shark movement animation
    }                                                                           //Closing the else statement
  }                                                                             //Closing the display function

  void movement() {                                                             //The function that makes the shark move - The shark enemy moves horisontally
    if (_x >= 1200) {                                                           //If the shark position on the x axis is more than or equal to 1200
      _speed *= -1;                                                             //Reverse the speed of the shark so that it moves left
    } else if (_x <= 150) {                                                     //If the shark position on the x axis is less than or equal to 150
      _speed = abs(_speed);                                                     //Reverse the speed of the shark so that it moves right
    }                                                                           //Closing the else if statement
    _x+=_speed;                                                        //The speed of the shark is added to the x axis position, (if the shark is moving left it is subtracted (+-speed)
    _t += 1;                                                           //The timer that increases the speed of the shark goes up
    if (_t > 900 && _speed > 0) {                                      //When the timer hits 900 (at framerate 60 that equals 15 seconds) and the speed is over 0
      _speed += 1;                                                     //1 is added to the speed
      _t = 0;                                                          //The timer is reset to 0
    } else if (_t > 900 && _speed < 0) {                               //Else if the timer hits 900 and the speed is under 0
      _speed -= 1;                                                     //1 is subtracted from the speed. Since the speed at first would be minus one it effectively would double the speed 
      _t = 0;                                                          //The timer is reset to 0
    }                                                                  //Closing the else if statement
  }                                                                    //Closing the movement function

  void sharkDeath() {                                                  //The function that displays death by shark
    _deathTimer += 1;                                                  //When the function is called the death timer starts to go up
    if (_deathTimer < 90) {                                            //If the deathtimer is under 90 (1,5 seconds at framerate 60)
      if (sharkSpeed < 0 && sharkX > pointX ) {                        //And if the shark is moving to the left and the shark is to the right of the hero
        deathAnimation(sharkDeathAnimation, sharkX-100, sharkY+40);    //Play the death animation - Position adjusted so that the animation is displayed in the right place
      } else if (sharkSpeed > 0 && sharkX < pointX) {                  //Else if shark is moving to the right and the shark is to the left of the hero
        deathAnimation(sharkDeathAnimationR, sharkX+100, sharkY+40);   //Play the flipped death animation - Poisiton adjusted so the animation is displayed in the right place
      } else if (sharkSpeed < 0 && sharkX < pointX) {                  //Else if the shark is moving to the left and the shark is to the left of the hero
        deathAnimation(sharkDeathAnimationR, sharkX+100, sharkY+40);   //Play the flipped death animation - Position adjusted so the animation is displayed in the right place
      } else {                                                         //Else (if the shark is moving right and the shark is to the right of the hero)
        deathAnimation(sharkDeathAnimation, sharkX-100, sharkY+40);    //Play the death animation - Position adjusted so the animation is displayed in the right place
      }                                                                //Close the else statement
    } else if (_deathTimer > 90 && _deathTimer < 130) {                //When the death timer is between 90 and 130
      if (sharkSpeed < 0 && sharkX > pointX ) {                        //And if the shark is moving to the left and the shark is to the right of the hero
        image(sharkDeathAnimation[5], sharkX-100, sharkY+40);          //Show the last picture in the array, so that you have time to registre that you died
      } else if (sharkSpeed > 0 && sharkX < pointX) {                  //Else if shark is moving to the right and the shark is to the left of the hero
        image(sharkDeathAnimationR[5], sharkX+100, sharkY+40);         //Show the last picture in the reversed array, so that death lingers
      } else if (sharkSpeed < 0 && sharkX < pointX) {                  //Else if the shark is moving to the left and the shark is to the left of the hero
        image(sharkDeathAnimationR[5], sharkX+100, sharkY+40);         //Show the last picture in the reversed array, so that death lingers
      } else {                                                         //Else (if the shark is moving right and the shark is to the right of the hero)
        image(sharkDeathAnimation[5], sharkX-100, sharkY+40);          //Show the last picture in the array so that death lingers
      }                                                                //Close the else statement
    } else if (_deathTimer > 130 && _deathTimer < 300) {               //When the death timer is between 130 and 300
      image(sharkScreen, width/2, height/2);                           //Show the death screen for the shark
    } else if (_deathTimer >= 300) {                                   //When the death timer reaches 300
      shark.resetValues();                                             //Reset the shark starting values - so that you don't start out with having a shark with super speed
      octo.resetValues();                                              //Reset the octopus starting values
      manta.resetValues();                                             //Reset mantaray starting values
      killer.resetValues();                                            //Reset the killer whale starting values
      health.healthCalculator();                                       //Calling the health calculator function, that subtracts 1 from your total health
      heroSafe = true;                                                 //Sets the heroSafe value to true
      manatee.isHeroSafe(heroSafe);                                    //Pass the new state of the heroSafe variable to the isHeroSafe function
      _deathTimer = 0;                                                 //Reset the death timer to 0
      manatee.movement();                                              //Allow the hero to move again
    }                                                                  //Close the else if statement
  }                                                                    //Close the sharkDeath function                                        

  int returnSharkX() {                                                 //Function that returns the sharks position on the x axis
    return _x;                                                         //Return of the type integer
  }                                                                    //Close the returnSharkX function
  
  int returnSharkSpeed() {                                            //Returns the current speed of the shark
    return _speed;                                                    //Returns the speed as an integer, used for defining which way the shark is currently heading
  }                                                                   //Close the function
  
  int returnSharkY() {                                                //Function that returns the position on the Y axis 
    return _y;                                                        //Used for collision testing
  }                                                                   //Close the function

  void resetValues() {                                                //Function that resets the values to the orignal values - So that when you die you are not left with impossible speeds
    _x = width/2;                                                     //X is in the middle of the screen
    _y = height/2;                                                    //Y is in the middle of the screen
    _speed = 1;                                                       //The speed is reset to 1
  }                                                                   //Close the function
}                                                                     //End of class definition


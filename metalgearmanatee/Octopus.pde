class Octopus {                                                              //Class definition of the Octopus class

  int _x;                                                                    //Declaring variable for x position
  int _y;                                                                    //Declaring variable for y position
  int _speed;                                                                //Declaring variable for speed
  int _t;                                                                    //Declaring variable for speed timer
  int _deathTimer;                                                           //Declaring variable for death timer
  PImage octoScreen;                                                         //Declaring variable for the octopus death screen
  PImage [] octoAnimation = new PImage[2];                                   //Creating new PImage array that holds movement animation
  PImage [] octoAnimationR = new PImage[2];                                  //Creating new PImage array that holds flipped movement animation
  PImage [] octoDeathAnimation = new PImage[6];                              //Creating new PImage array that holds death animation
  PImage [] octoDeathAnimationR = new PImage[6];                             //Creating new PImage array that holds flipped death animation

  Octopus() {                                                                //Constructor for the octopus class
    enemyOctopus = loadImage("squid.png");                                   //Load image of the octopus
    octoAnimation[0] = loadImage("squid.png");                               //Set first picture in animation array
    octoAnimation[1] = loadImage("squid1.png");                              //Set second picture in the animation array
    octoAnimationR[0] = loadImage("squidR.png");                             //Set first picture in the flipped animation array
    octoAnimationR[1] = loadImage("squid1R.png");                            //Set second picture in the flipped animation array
    octoScreen = loadImage("octodeathscreen.jpg");                           //Load image of the octopus death screen
    for (int i = 0; i < octoDeathAnimationR.length; i++) {                   //For loop to initialise death animation array
      octoDeathAnimationR[i] = loadImage("octodeathanimation"+i+"R.png");    //Conviently named pictures to allow for the use of a for loop
    }                                                                        //Close the for loop
    for (int i = 0; i < octoDeathAnimation.length; i++) {                    //For loop to initialise flipped death animation array
      octoDeathAnimation[i] = loadImage("octodeathanimation"+i+".png");      //Conviently named pictures to allow for the use of a for loop
    }                                                                        //Close the for loop

    _speed = 1;                                                              //Set starting speed to 1
    _x = width/2;                                                            //Set starting x position
    _y = 100;                                                                //Set starting y position
    _t = 0;                                                                  //Set speed timer to 0
    _deathTimer = 0;                                                         //Set death timer to 0
  }                                                                          //Close constructor
  
  void display() {                                                           //Function that displays the octopus
    if (_speed < 0) {                                                        //If the octopus moves up
      twoPictureAnimation(octoAnimation, _x, _y);                            //Play the movement animation
    } else {                                                                 //If the octopus moves down
      twoPictureAnimation(octoAnimationR, _x, _y);                           //Play the flipped animation
    }                                                                        //Close the else statement
  }                                                                          //Close the function

  void movement() {                                                          //Function that moves the octopus
    if (_y >= height - enemyOctopus.height/2) {                              //If the octopus hits the bottom edge 
      _speed *= -1;                                                          //Reverse the speed
    } else if (_y <= enemyOctopus.height/2) {                                //If the octopus hits to top edge
      _speed = abs(_speed);                                                  //Reverse the speed
    }                                                                        //Close the else if statement
    _y+=_speed;                                                              //Speed is added to the y position
    _t += 1;                                                                 //1 is added to the timer
    if (_t > 900 && _speed > 0) {                                            //When the timer hits 900 and the octopus is moving down
      _speed += 1;                                                           //Add one to the speed
      _t = 0;                                                                //Reset the timer
    } else if (_t > 900 && _speed < 0) {                                     //When the timer hits 900 and the octopus is moving up
      _speed -= 1;                                                           //Subtract one from the speed
      _t = 0;                                                                //Reset the timer
    }                                                                        //Close the else if statement
  }                                                                          //Close the function

  void octoDeath() {                                                         //Function that displays death by octopus
    _deathTimer += 1;                                                        //Add one to the death timer
    if (_deathTimer < 90) {                                                  //If the death timer is under 90
      if (octoSpeed < 0 && octoY > pointY ) {                                //If the octopus is moving up and is located over the hero
        deathAnimation(octoDeathAnimation, octoX+20, octoY-100);             //Play the death animation - adjusted position
      } else if (octoSpeed > 0 && octoY < pointY) {                          //If the octopus is moving down and is located under the hero
        deathAnimation(octoDeathAnimationR, octoX+25, octoY-55);             //Play the flipped death animation - adjusted position 
      } else if (octoSpeed < 0 && octoY < pointY) {                          //If the octopus is moving up and is located under the hero
        deathAnimation(octoDeathAnimationR, octoX+25, octoY-55);             //Play the flipped death animation - adjusted position
      } else {                                                               //If the octopus is moving down and is located over the hero
        deathAnimation(octoDeathAnimation, octoX+20, octoY-100);             //Play the death animation - adjusted position
      }                                                                      //Close else statement
    } else if (_deathTimer > 90 && _deathTimer < 130) {                      //If the death timer is between 90 and 130
      if (octoSpeed < 0 && octoY > pointY ) {                                //If the octopus is moving up and is located over the hero
        image(octoDeathAnimation[5], octoX+20, octoY-100);                   //Display last frame in the array
      } else if (octoSpeed > 0 && octoY < pointY) {                          //If the octopus is moving down and is located under the hero
        image(octoDeathAnimationR[5], octoX+25, octoY-55);                   //Display last frame in the array
      } else if (octoSpeed < 0 && octoY < pointY) {                          //If the octopus is moving up and is located under the hero
        image(octoDeathAnimationR[5], octoX+25, octoY-55);                   //Display last frame in the array
      } else {                                                               //If the octopus is moving down and is located over the hero
        image(octoDeathAnimation[5], octoX+20, octoY-100);                   //Display last frame in the array
      }                                                                      //Close else statement
    } else if (_deathTimer > 130 && _deathTimer < 300) {                     //If the death timer is between 130 and 300
      image(octoScreen, width/2, height/2);                                  //Display the octopus death screen
    } else if (_deathTimer >= 300) {                                         //When the timer hits 300
      shark.resetValues();                                                   //Reset values
      octo.resetValues();                                                    //Reset values
      manta.resetValues();                                                   //Reset values
      killer.resetValues();                                                  //Reset values
      health.healthCalculator();                                             //Calculate health
      heroSafe = true;                                                       //Set safe status to true
      manatee.isHeroSafe(heroSafe);                                          //Pass it through the isHeroSafe function
      _deathTimer = 0;                                                       //Reset the death timer
      manatee.movement();                                                    //Allow the hero to move again
    }                                                                        //Close else if statement
  }                                                                          //Close function

  int returnOctoY () {                                                       //Function that returns integer
    return _y;                                                               //Return the y position
  }                                                                          //Close the function

  int returnOctoX() {                                                        //Function that returns integer
    return _x;                                                               //Return x position
  }                                                                          //Close function

  int returnOctoSpeed() {                                                    //Function that returns integer
    return _speed;                                                           //Return the current speed
  }                                                                          //Close function

  void resetValues() {                                                       //Function that resets values
    _speed = 1;                                                              //Set speed to 1
    _x = width/2;                                                            //Set x position
    _y = 100;                                                                //Set y position
  }                                                                          //Close function
}                                                                            //Close class definition


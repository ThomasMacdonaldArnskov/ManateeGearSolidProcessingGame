class Hero {                                                              //Class definition for the class Hero
  int[][] movement = new int[25][2];                                      //Creating a new 2D array used for movement
  PImage [] heroAnimation = new PImage[2];                                //New animation array for moving to the right
  PImage [] heroAnimationR = new PImage[2];                               //New animation array for moving to the left
  PImage [] safeAnimation = new PImage[2];                                //New animation array for displaying that the hero is safe going right - To avoid dying multiple times in a row you are invisible right after you die for a couple of seconds. To show that to the player the animation shows a blinking hero
  PImage [] safeAnimationR = new PImage[2];                               //New animation array for displaying that the hero is safe going left
  int pointX;                                                             //The point on the x axis the hero is - used because its easier to write than the specific point in the array
  int pointY;                                                             //The point on the y axis the hero is - used because its easier to write than the specific point in the array
  boolean lastleft;                                                       //Declare a boolean called lastleft - used to test if the hero was last turned right or left if standing still
  int _t;                                                                 //Timer for the hero safe state
  boolean _safe;                                                          //Declare a boolean called _safe that is used to see if the hero is in the safe state

  Hero() {                                                                //The Hero constructor
    hero = loadImage("manateehero.png");                                  //Load the image for the hero - used for collision testing
    heroHidden = loadImage("manateeherohidden.png");                      //Load the image for the hidden hero
    heroAnimation [0] = loadImage("manateehero.png");                     //load the image for the first point in the animation array
    heroAnimation [1] = loadImage("manateehero2.png");                    //load the image for the second point in the animation array
    heroAnimationR [0] = loadImage("manateeheroR.png");                   //load the image for the first point in the flipped animation array
    heroAnimationR [1] = loadImage("manateehero2R.png");                  //load the image for the second point in the flipped animation array
    safeAnimation[0] = loadImage("manateeherohidden.png");                //load the image for the first point in the safe animation array
    safeAnimation[1] = loadImage("manateehero2.png");                     //load the image for the second point in the safe animation array
    safeAnimationR[0] = loadImage("manateeherohiddenR.png");              //load the image for the first point in the flipped safe animation array
    safeAnimationR[1] = loadImage("manateehero2R.png");                   //load the image for the second point in the flipped safe animation array
    lastleft = false;                                                     //Set the lastleft variable to false
    _safe = true;                                                         //Set the _safe variable to true
    _t = 0;                                                               //Set the _t timer to 0
  }                                                                       //Close the constructor

  void movement() {                                                       //Function that moves the hero
    pointX = movement[movement.length - 1][0];                            //Store the x position of the hero into a variable
    pointY = movement[movement.length - 1][1];                            //Store the y position of the hero into a variable
    for (int i = movement.length - 1; i > 0; i--) {                       //For loop to shift position in the array
      movement[i][0] = movement[i-1][0];                                  //Shift where the x location is stored down one spot in the index
      movement[i][1] = movement[i-1][1];                                  //Shift where the y location is stored down one spot in the index
    }                                                                     //Close the for loop
    movement[0][0] = mouseX;                                              //Store the x position of the mouse into the array
    movement[0][1] = mouseY;                                              //Store the y position of the mouse into the array
    for (int i = 1; i < movement.length; i++) {                           //For loop that draws the movement line
      stroke(255, 161, 8);                                                //Set the stroke color
      strokeWeight(3);                                                    //Set the thickness of the stroke
      line(movement[i-1][0], movement[i-1][1], movement[i][0], movement[i][1]);        //draw a line connecting the points stored in the array
    }                                                                                  //close the for loop
  }                                                                                    //close the function

  void display() {                                                                     //Function that displays the hero
    if (mouseX > pmouseX) {                                                            //If the mouse is moving to the right
      twoPictureAnimation(heroAnimation, pointX, pointY);                              //Display the movement animation
      lastleft = false;                                                                //And set the lastleft boolean to false
    } else if (mouseX < pmouseX) {                                                     //If the mouse is moving left
      twoPictureAnimation(heroAnimationR, pointX, pointY);                             //Display the flipped animation
      lastleft = true;                                                                 //And set the lastleft boolean to true
    } else if (lastleft == true) {                                                     //Else if the mouse is in the same spot but it last was moving left
      twoPictureAnimation(heroAnimationR, pointX, pointY);                             //Display the flipped animation
    } else {                                                                           //And if the mouse is still but last movement was to the right
      twoPictureAnimation(heroAnimation, pointX, pointY);                              //Display the movement animation
    }                                                                                  //Close the else statement
  }                                                                                    //Close the function

  void isHeroSafe(boolean safe) {                                                      //Function that set the heros status to safe or not
    if (safe && _t<300) {                                                              //If the boolean that is passed through the function is true and the timer is under 300
      _t+=1;                                                                           //Plus one to the timer
      _safe = true;                                                                    //Set _safe to true
      if (mouseX > pmouseX) {                                                          //If the mouse is moving to the right
        twoPictureAnimation(safeAnimation, pointX, pointY);                            //Display the safe animation
        lastleft = false;                                                              //And set the lastleft boolean to false
      } else if (mouseX < pmouseX) {                                                   //If the mouse is moving left
        twoPictureAnimation(safeAnimationR, pointX, pointY);                           //Display the flipped animation
        lastleft = true;                                                               //And set the lastleft boolean to true
      } else if (lastleft == true) {                                                   //Else if the mouse is in the same spot but it last was moving left
        twoPictureAnimation(safeAnimationR, pointX, pointY);                           //Display the flipped animation
      } else {                                                                         //And if the mouse is still but last movement was to the right
        twoPictureAnimation(safeAnimation, pointX, pointY);                            //Display the movement animation
      }                                                                                //Close the else statement
    } else if (_t >= 300) {                                                            //When the timer hits 300
      _t=0;                                                                            //Reset the timer
      alert.rewind();                                                                  //Rewind the alert sound
      _safe = false;                                                                   //And set the _safe to false
    }                                                                                  //Close the else if statement
  }                                                                                    //Close the the function

  boolean returnHeroSafe() {                                                           //Function that returns boolean
    return _safe;                                                                      //Return the _safe variable
  }                                                                                    //Close the function

  int returnManateeX() {                                                               //Function that returns integer
    return pointX;                                                                     //Returns heros x position
  }                                                                                    //Close function

  int returnManateeY() {                                                               //Function that returns integer
    return pointY;                                                                     //Returns heros y position
  }                                                                                    //Close function
}                                                                                      //Close class definition


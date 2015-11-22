class Food {                                                                //Class definition for the Food class - The rations that displays randomly and gives you points when collected

    int _x;                                                                 //Declaring the x position
  int _y;                                                                   //Declaring the y position
  int _t;                                                                   //Declaring the variable for the timer that randomizes the ration position
  int _score;                                                               //Declaring the variable for the highscore

    Food() {                                                                //Food constructor
    fooditem = loadImage("ration.png");                                     //load the ration image
    _x = 900;                                                               //Set the initial position of the ration on the x axis
    _y = 570;                                                               //Set the initial position of the ration on the y axis
    _t = 0;                                                                 //Set the timer to 0
    _score = 0;                                                             //Set the score to 0
    showFood = true;                                                        //Set the boolean showFood to true
    foodEaten = false;                                                      //And the foodEaten to false
  }

  void display() {                                                          //Function that displays the ration
    if (showFood) {                                                         //If showFood is true
      image(fooditem, _x, _y);                                              //The image of the ration will be shown
    }                                                                       //Closing the if statement
  }                                                                         //Closing the display function

  void display(boolean showFood) {                                          //Overloaded function that takes a boolean as parameter                                   
    if (showFood) {                                                         //If showFood is true
      image(fooditem, _x, _y);                                              //Display the image of the ration
    }                                                                       //Close the if statement
  }                                                                         //Close the function

  void randomizer() {                                                       //Function that randomizes the position of the food item
    _t += 1;                                                                //The t timer goes up when the function is called
    if (_t > 300) {                                                         //If the timer hits 300
      item.rewind();                                                        //The sound for the collection of an item is rewinded
      _x = int(random(fooditem.width/2, width - fooditem.width/2));         //The x position is set from half the width of the image to the width minus half the width of the image - This is because the images is shown in center mode- Converted to int
      _y = int(random(fooditem.height/2, height - fooditem.height/2));      //The y position is set from half the height of the image to the height minus half the height of the image - This is because the images is shown in center mode - Converted to int
      _t = 0;                                                               //The timer is reset to 0
      showFood = true;                                                      //And the boolean showFood is set to true so that it is displayed
    }                                                                       //Closing the if statement
  }                                                                         //Closing the random function
  
  void scoreCounter(boolean foodEaten) {                                    //Function that calculates your highscore
    if (foodEaten) {                                                        //If foodEaten is true
      _score += 10;                                                         //+10 to the score
      foodEaten = false;                                                    //Set food eaten to false
      _x = width + 500;                                                     //And move the position on the x axis way out of the screen so that the points only count up once. Maybe not the most elegant solution but it works
    }                                                                       //Closing the if statement
  }                                                                         //Closing the function

  void highScore() {                                                        //Function that displays the highschore
    textFont(font, 48);                                                     //The font is defined by the variable font and the size is set to 48
    fill(255, 205, 0);                                                      //The text is colored to a delighting orange
    text("Highscore: " +_score+"", 10, 50);                                 //The text higscore + whatever your current score is, is displayed in the left hand corner
  }                                                                         //Closing the function

  int returnFoodX() {                                                       //Function that returns the position on the x axis
    return _x;                                                              //Aaaaand returns x which is an integer
  }

  int returnFoodY() {                                                       //Function that returns the position on the y axis
    return _y;                                                              //Aaaaand returns y which is an integer
  }                                                                         //Close the function

  boolean returnShowFood() {                                                //Function that returns what the current state of the variable showFood is
    return showFood;                                                        //Returns showFood which is a boolean datatype
  }                                                                         //Close function

  boolean returnFoodEaten() {                                               //Function that returns the current state of the foodEaten variable
    return foodEaten;                                                       //Returns foodEaten which is a boolean datatype
  }                                                                         //Close the function

  int returnHighScore() {                                                   //Returns the current value of _score
    return _score;                                                          //which is an integer
  }                                                                         //Close the function

  void resetValues() {                                                      //Function that resets the value - used upon game over
    _x = 900;                                                               //the position on the x axis
    _y = 570;                                                               //the position on the y axis
    _t = 0;                                                                 //reset the timer to 0
    _score = 0;                                                             //Reset the score to 0
  }                                                                         //Close the function
}                                                                           //Close the class definition


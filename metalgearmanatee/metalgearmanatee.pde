/*

Welcome to the source code for manatee gear solid.
Programmed by Thomas Laurits Macdonald Arnskov,
for the project hand-in in Introduction to programming

*/

import ddf.minim.*;                                                      //Import the Minim library

Menu menu;                                                               //Declare a variable called menu of the Menu class
Game game;                                                               //Declare a variable called game of the Game class
Hero manatee;                                                            //Declare a variable called manatee of the Hero class
Enemy shark;                                                             //Declare a variable called shark of the Enemy class
Octopus octo;                                                            //Declare a variable called octo of the Enemy class
Mantaray manta;                                                          //Declare a variable called manta of the Mantaray class
Whale killer;                                                            //Declare a variable called killer of the Whale class
Hidingspots hide;                                                        //Declare a variable called hide of the Hidingspots class
Food food;                                                               //Deckare a variable called health of the Health class
Health health;

Minim minim;                                                             //Declaring a variable called minim of the Minim class
AudioPlayer titleSong;                                                   //Declaring an auduiplayer variable called titlesong
AudioPlayer levelSong;                                                   //Declaring an audioplayer variable called levelSong
AudioPlayer codec;                                                       //Declaring an audioplayer variable called codec
AudioPlayer alert;                                                       //Declaring an audioplayer variable called alert
AudioPlayer death;                                                       //Declaring an audioplayer variable called death
AudioPlayer item;                                                        //Declaring an audioplayer variable called item

int pointX;                                                              //Declaring a int called pointX - the heroes x position
int pointY;                                                              //Declaring a int called pointY - the heroes y position
int sharkX;                                                              //Declaring a int called sharkX - the sharks x position
int sharkY;                                                              //Declaring a int called sharkY - the sharks y position
int sharkSpeed;                                                          //Declaring a int called sharkSpeed - the sharks current speed
int hideX;                                                               //Declaring a int called hideX - the cardboard box's x position
int hideY;                                                               //Declaring a int called hideY - the cardboard box's y position
int octoY;                                                               //Declaring a int called octoY - the octopus y position
int octoX;                                                               //Declaring a int called octoX - the octopus x position
int octoSpeed;                                                           //Declaring a int called octoSpeed - the octopus's current speed
float mantaX;                                                            //Declaring a float called mantaX - the mantarays x position
float mantaY;                                                            //Declaring a float called mantaY - the mantarays y position
int mantaSpeed;                                                          //Declaring a int called mantaSpeed - the current speed of the mantaray
float whaleX;                                                            //Declaring a float called whaleX - the orcas x position
float whaleY;                                                            //Declaring a float called whaleY - the orcas y position
int whaleSpeed;                                                          //Declaring a int called whaleSpeed - the orcas current speed
int foodX;                                                               //Declaring a int called foodX - the x position of the ration
int foodY;                                                               //Declaring a int called foodY - the y position of the ration
int finalScore;                                                          //Declaring a int called finalScore - the accumulated score
int healthReturn;                                                        //Declaring a int called healthReturn - the returned health from the Health class function called returnHealth();

boolean showFood;                                        //Declaring a boolean called showFood - when true shows the ration, when not it doesn't
boolean foodEaten;                                       //Declaring a boolean called foodEaten - used for calculating score when the ration is colided with
boolean heroSafe;                                        //Declaring a boolean called heroSafe - used for the invinsibility timer that starts after death so you don't die twice in a row

PImage titleScreen;                                      //Declaring a PImage that displays the titlescreen picture
PImage current;                                          //Declaring a PImage that is called current - changes based on which menubutton you press
PImage creditsPage;                                      //Declaring a PImage that displays the credits page picture
PImage levelPage;                                        //Declaring a Pimage that displays the level page picture
PImage howToPage;                                        //Declaring a PImage that displays the how to page picture
PImage gameOver;                                         //Declaring a PImage that displays the game over picture
PImage loadingPage;                                      //Declaring a PImage that displays the loading page picture

PImage hero;                                             //Declaring a PImage for the hero image
PImage cardboardBox;                                     //Declaring a PImage for the cardboard box
PImage heroHidden;                                       //Declaring a PImage for the hero when he is hiding behind the cardboard box
PImage sharkpic;                                         //Declaring a PImage for the image of the shark
PImage enemyOctopus;                                     //Declaring a PImage for the image of the octopus
PImage enemyManta;                                       //Declaring a PImage for the image of the Mantaray
PImage enemyWhale;                                       //Declaring a PImage for the orca
PImage fooditem;                                         //Declaring a Pimage for the ration

PFont font;                                              //Declaring a custom font

PImage[] loading = new PImage[6];                        //Creating a PImage array that contains the pictures of the loading animation

int t = 0;                                               //Declaring the variable for the timer that controls the movement animation
int t2 = 0;                                              //Declaring the variable for the integer that controls the indexpoint in the movement array
int sda = 0;                                             //Declaring the variable for the timer that controls the death animation
int sda2 = 0;                                            //Declaring the variable for the integer that controls the index point in the death animation array
int loadingtimer = 0;                                    //Declaring the variable for the timer that controls the loading screen animation

void setup() {                                           //The setup function of the game
  size(1366, 768);                                       //Setting the size of the screen to 1366 by 768 - the game is meant to be played in fullscreen
  frameRate(60);                                         //Setting the framerate to 60, so that it runs smoothly
  smooth();                                              //Turn on anti aliasing
  font = loadFont("Metal_Gear_Solid_2-48.vlw");          //Load the metal gear solid font type

  for (int i = 0; i<loading.length; i++) {               //For-loop to initialise the loading animation array
    loading[i] = loadImage("loading"+i+".jpg");          //Every picture is conviently named to allow for the for-loop to be utilized
  }                                                      //Closing the for-loop
  
  menu = new Menu();                                     //create new object from the menu class
  manatee = new Hero();                                  //create new object from the hero class
  hide = new Hidingspots();                              //create new object from the Hidingspots class
  shark = new Enemy();                                   //create new object from the Enemy class
  octo = new Octopus();                                  //create new object from the Octopus class
  manta = new Mantaray();                                //create new object from the Mantaray class
  killer = new Whale();                                  //create new object from the mantaray class
  game = new Game();                                     //create new object from the Game class
  food = new Food();                                     //create new object from the Food class
  health = new Health();                                 //create new object from the Health class

  titleScreen = loadImage("MANATEE_TITLE_MENU.jpg");     //Initialise titleScreen variable and load the corresponding picture
  creditsPage = loadImage("creditspage.jpg");            //Initialise creditsPage variable and load the corresponding picture
  levelPage = loadImage("templevel.jpg");                //Initialise levelPage variable and load the corresponding picture
  loadingPage = loadImage("loading0.jpg");               //Initialise loadingPage variable and load the corresponding picture
  howToPage = loadImage("howtopage.jpg");                //Initialise howToPage variable and load the corresponding picture
  gameOver = loadImage("deathscreen.jpg");               //Initialise gameOver variable and load the corresponding picture

  current = titleScreen;                                 //Sets the current variable to equal the titlescreen 

  minim = new Minim(this);                               //Create a new instance of minim
  titleSong = minim.loadFile("10.mp3");                  //load the title song
  levelSong = minim.loadFile("1.mp3");                   //load the level song
  codec = minim.loadFile("Codec.mp3");                   //load the codec sound effect
  alert = minim.loadFile("Alert.mp3");                   //load the alert sound effect
  death = minim.loadFile("deathshout.mp3");              //load the sound effect for the game over screen
  item = minim.loadFile("itemcollect.mp3");              //load the sound that plays when you pick up a ration
  imageMode(CENTER);                                     //Set the image mode to center
}                                                        //Close the setup

void draw() {                                            //The draw function of the game
  background(current);                                   //Set the background to equal the current variable
  
  pointX = manatee.returnManateeX();                     //Call the function returnManateeX and set it to the variable pointX
  pointY = manatee.returnManateeY();                     //Call the function returnManateeY and set it to the variable pointY
  heroSafe = manatee.returnHeroSafe();                   //Return whether or not the hero is safe
  sharkX = shark.returnSharkX();                         //Call the function that returns the x position of the shark
  sharkY = shark.returnSharkY();                         //Call the function that returns the y position of the shark
  sharkSpeed = shark.returnSharkSpeed();                 //Return the speed of the shark
  octoY = octo.returnOctoY();                            //Return the y position of the Octopus
  octoX = octo.returnOctoX();                            //Return the x position of the Octopus
  octoSpeed = octo.returnOctoSpeed();                    //Return the speed of the Octopus
  mantaX = manta.returnMantaX();                         //Return the x position of the Mantaray
  mantaY = manta.returnMantaY();                         //Return the y position of the Mantaray
  mantaSpeed = manta.returnMantaSpeed();                 //Return the speed of the Mantaray
  whaleX = killer.returnWhaleX();                        //Return the x position of the Orca
  whaleY = killer.returnWhaleY();                        //Return the y position of the Orca
  whaleSpeed = killer.returnWhaleSpeed();                //Return the speed of the Orca
  hideX = hide.returnHideX();                            //Return the x position of the cardboard box
  hideY = hide.returnHideY();                            //Return the y position of the cardboard box
  foodX = food.returnFoodX();                            //Return the x position of the ration
  foodY = food.returnFoodY();                            //Return the y position of the ration
  showFood = food.returnShowFood();                      //Return whether or not the returnShowFood is true or false
  foodEaten = food.returnFoodEaten();                    //Return whether or not the returnFoodEaten is true or false
  healthReturn = health.returnHealth();                  //Return the current health state 
  finalScore = food.returnHighScore();                   //Return the current score

  if (current == titleScreen) {                          //If the current variable is equal to the titleScreen
    titleSong.play();                                    //Play the titlescreen song
    menu.display();                                      //Display the menu
  } else if (current == loadingPage) {                   //Else if the current variable is equal to the loading page
    codec.play();                                        //Play the codec sound
    food.resetValues();                                  //Reset the food values - basically resets the current score to 0
    loadingtimer +=1;                                    //Starts the loading timer
    if (loadingtimer < 180) {                            //If the loading timer is under 180
      deathAnimation(loading, width/2, height/2);        //Play the 6 picture animation contained in the loading array at the center of the screen 
    } else if (loadingtimer >= 180) {                    //Else if the loading timer is larger or equal to 180
      loadingtimer = 0;                                  //Reset the loading timer
      current = levelPage;                               //Change the current variable to the levePage
    }                                                    //Close the else if statement
  } else if (current == levelPage) {                     //Else if the current is equal to the levelPage
    cursor(CROSS);                                       //Change the cursor to be a cross
    codec.pause();                                       //Pause the codec sound
    codec.rewind();                                      //Rewind the codec sound
    death.rewind();                                      //Rewind the game over sound
    titleSong.pause();                                   //Pause the title song
    titleSong.rewind();                                  //And rewind it
    levelSong.play();                                    //Play the level song
    game.logic();                                        //Call the function logic inside the Game class
    food.randomizer();                                   //Randomise the location of the ration
    food.highScore();                                    //Display the highscore
    health.display();                                    //Display the health
  } else if (current == gameOver) {                                                    //Else if the current variable is equal to gameOver
    levelSong.pause();                                                                 //Pause the levelSong
    levelSong.rewind();                                                                //And rewind it
    death.play();                                                                      //Play the game over sound
    menu.display();                                                                    //Display the menu. So you can play again!
    textFont(font, 72);                                                                //Set the text font to the font variable and to font size 72
    fill(255, 205, 0);                                                                 //Fill in the orange color of the text
    text("Highscore: " +finalScore+"", width/2 - 130, height/2 + 130);                 //Write highscore and the current state of the finalScore variable on the screen, adjust for corner mode
    shark.resetValues();                                                               //Reset the values of the shark  
    octo.resetValues();                                                                //Reset the values of the octopus
    manta.resetValues();                                                               //Reset the values of the mantaray
    killer.resetValues();                                                              //Reset the values of the orca
    health.resetValues();                                                              //Reset the health value
    hide.resetValues();                                                                //Reset the values of the cardboardbox
  } else if (current == creditsPage) {                                                 //Else if the current variable is equal to the creditsPage
    menu.display();                                                                    //Display the menu
  } else if (current == howToPage) {                                                   //Else if the current variable is equal to the howToPage
    menu.display();                                                                    //Display the menu
  }                                                                                    //Close the else if statement
}                                                                                      //Close the draw function            


/* Universal functions */

boolean collisionTest(PImage image, int x, int y, PImage pic, int xx, int yy) {        //The function that tests if two pictures collide
  //Defining bounding box of image
  float xStartOfImage = x - image.width/2*0.75;                                        //25 percent from the left side of picture one. 75 percent to tighten the hit box
  float xEndOfImage = x + image.width/2*0.75;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yStartOfImage = y - image.height/2*0.75;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yEndOfImage = y + image.height/2*0.75;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes
  //Defining bounding box of pic  
  float xxStartOfImage = xx - pic.width/2*0.75;                                        //25 percent from the left side of picture two. 75 percent to tighten the hit box
  float xxEndOfImage = xx + pic.width/2*0.75;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yyStartOfImage = yy - pic.height/2*0.75;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yyEndOfImage = yy + pic.height/2*0.75;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes

  if (xxStartOfImage >= xStartOfImage && xxStartOfImage <= xEndOfImage && yyStartOfImage >= yStartOfImage && yyStartOfImage <= yEndOfImage) {         //If the first picture colides with the second picture from the top and the left
    return true;                                                                                                                                      //Boolean return is true
  } else if (xStartOfImage >= xxStartOfImage && xStartOfImage <= xxEndOfImage && yyStartOfImage >= yStartOfImage && yyStartOfImage <= yEndOfImage) {  //Else if the first picture colide with the second picture from the top and the right
    return true;                                                                                                                                      //Boolean return is true
  } else if (xxStartOfImage >= xStartOfImage && xxStartOfImage <= xEndOfImage && yStartOfImage >= yyStartOfImage && yStartOfImage <= yyEndOfImage) {  //Else if the first picture colides with the second picture from the bottom and the left
    return true;                                                                                                                                      //Boolean return is true
  } else if (xStartOfImage >= xxStartOfImage && xStartOfImage <= xxEndOfImage && yStartOfImage >= yyStartOfImage && yStartOfImage <= yyEndOfImage) {  //Else if the first picture colides with the second picture from the bottom and the right
    return true;                                                                                                                                      //Boolean return is true
  } else {                                                                                                                                            //If none of the above is true
    return false;                                                                                                                                     //Boolean return is false
  }                                                                                                                                                   //Close the else statement
}                                                                                                                                                     //Close the function

boolean collisionTest(PImage image, int x, int y, PImage pic, float xx, float yy) {    //Overloaded collision test function
  //Defining bounding box of image
  float xStartOfImage = x - image.width/2*0.75;                                        //25 percent from the left side of picture one. 75 percent to tighten the hit box
  float xEndOfImage = x + image.width/2*0.75;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yStartOfImage = y - image.height/2*0.75;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yEndOfImage = y + image.height/2*0.75;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes
  //Defining bounding box of pic
  float xxStartOfImage = xx - pic.width/2*0.75;                                        //25 percent from the left side of picture two. 75 percent to tighten the hit box
  float xxEndOfImage = xx + pic.width/2*0.75;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yyStartOfImage = yy - pic.height/2*0.75;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yyEndOfImage = yy + pic.height/2;                                              //25 percent from the bottom of the picture. Adjusted for hitbox purposes

  if (xxStartOfImage >= xStartOfImage && xxStartOfImage <= xEndOfImage && yyStartOfImage >= yStartOfImage && yyStartOfImage <= yEndOfImage) {         //If the first picture colides with the second picture from the top and the left
      return true;                                                                                                                                    //Boolean return is true
  } else if (xStartOfImage >= xxStartOfImage && xStartOfImage <= xxEndOfImage && yyStartOfImage >= yStartOfImage && yyStartOfImage <= yEndOfImage) {  //Else if the first picture colide with the second picture from the top and the right
    return true;                                                                                                                                      //Boolean return is true
  } else if (xxStartOfImage >= xStartOfImage && xxStartOfImage <= xEndOfImage && yStartOfImage >= yyStartOfImage && yStartOfImage <= yyEndOfImage) {  //Else if the first picture colides with the second picture from the bottom and the left
    return true;                                                                                                                                      //Boolean return is true
  } else if (xStartOfImage >= xxStartOfImage && xStartOfImage <= xxEndOfImage && yStartOfImage >= yyStartOfImage && yStartOfImage <= yyEndOfImage) {  //Else if the first picture colides with the second picture from the bottom and the right
    return true;                                                                                                                                      //Boolean return is true
  } else {                                                                                                                                            //If none of the above is true
    return false;                                                                                                                                     //Boolean return is false
  }                                                                                                                                                   //Close the else statement
}                                                                                                                                                     //Close the function

void deathAnimation(PImage [] arr, int x, int y) {        //The function that displays the death animation, takes a PImage array and x,y coordinates as integers
  if (sda < 15) {                                         //If the timer is less than 15
    sda += 1;                                             //Plus one to the timer
    sda2=0;                                               //And have the index point at 0
  } else if (sda < 30) {                                  //When the timer hits 15 and is less than 30
    sda += 1;                                             //Plus one to the timer
    sda2 = 1;                                             //At index point 1
  } else if (sda < 45) {                                  //When the timer hits 30 and is less than 45
    sda += 1;                                             //Plus one to the timer
    sda2 = 2;                                             //At index point 2
  } else if (sda < 60) {                                  //When the timer hits 45 and is less than 60
    sda += 1;                                             //Plus one to the timer
    sda2 = 3;                                             //At index point 3
  } else if (sda < 75) {                                  //When the timer hits 60 and is less than 75
    sda += 1;                                             //Plus one to the timer
    sda2 = 4;                                             //At index point 4
  } else if (sda < 90) {                                  //When the timer hits 75 and is less than 90
    sda += 1;                                             //Plus one to the timer
    sda2 = 5;                                             //At index point 5
  } else {                                                //When the timer hits 90 
    sda = 0;                                              //Reset the timer to 0
  }                                                       //Close the else statement
  image(arr[sda2], x, y);                                 //Display the array at the index point sda2 (By the way sda stands for shark death animation - at first the game only had sharks)
}                                                         //Close the function

void deathAnimation(PImage [] arr, float x, float y) {    //Overloaded 6 picture animation that takes floating points instead of integers
  if (sda < 15) {                                         //If the timer is less than 15
    sda += 1;                                             //Plus one to the timer
    sda2=0;                                               //And have the index point at 0
  } else if (sda < 30) {                                  //When the timer hits 15 and is less than 30
    sda += 1;                                             //Plus one to the timer
    sda2 = 1;                                             //At index point 1
  } else if (sda < 45) {                                  //When the timer hits 30 and is less than 45
    sda += 1;                                             //Plus one to the timer
    sda2 = 2;                                             //At index point 2
  } else if (sda < 60) {                                  //When the timer hits 45 and is less than 60
    sda += 1;                                             //Plus one to the timer
    sda2 = 3;                                             //At index point 3
  } else if (sda < 75) {                                  //When the timer hits 60 and is less than 75
    sda += 1;                                             //Plus one to the timer
    sda2 = 4;                                             //At index point 4
  } else if (sda < 90) {                                  //When the timer hits 75 and is less than 90
    sda += 1;                                             //Plus one to the timer
    sda2 = 5;                                             //At index point 5
  } else {                                                //When the timer hits 90
    sda = 0;                                              //Reset the timer to 0
  }                                                       //Close the else statement
  image(arr[sda2], x, y);                                 //Display the array at the index point sda2 (By the way sda stands for shark death animation - at first the game only had sharks)
}                                                         //Close the function

void twoPictureAnimation(PImage [] arr, int x, int y) {  //The function that produces the two picture animation (The movement animation)
  if (t < 60) {                                          //If the timer is less than 60
    t += 1;                                              //Plus one to the timer
    t2=0;                                                //At index point 0
  } else if (t < 120) {                                  //If the timer is over 60 but less than 120
    t += 1;                                              //Plus one to the timer
    t2 = 1;                                              //At index point 1
  } else {                                               //When the timer hits 120
    t = 0;                                               //Reset the timer to 0
  }                                                      //Close the else statement
  image(arr[t2], x, y);                                  //Display the array at index point t2
}                                                        //Close the function

void twoPictureAnimation(PImage [] arr, float x, float y) {  //Overloaded two picture animation function that takes floats instead of ints
  if (t < 60) {                                              //If the timer is less than 60
    t += 1;                                                  //Plus one to the timer
    t2=0;                                                    //At index point 0
  } else if (t < 120) {                                      //If the timer is over 60 but less than 120
    t += 1;                                                  //Plus one to the timer
    t2 = 1;                                                  //At index point 1  
  } else {                                                   //When the timer hits 120
    t = 0;                                                   //Reset the timer
  }                                                          //Close the else statement
  image(arr[t2], x, y);                                      //Display the array at index point t2
}                                                            //Close the function


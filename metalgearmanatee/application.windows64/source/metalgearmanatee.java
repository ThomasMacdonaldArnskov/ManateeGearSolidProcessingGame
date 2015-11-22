import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class metalgearmanatee extends PApplet {

/*

Welcome to the source code for manatee gear solid.
Programmed by Thomas Laurits Macdonald Arnskov,
for the project hand-in in Introduction to programming

*/

                                                      //Import the Minim library

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

public void setup() {                                           //The setup function of the game
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

public void draw() {                                            //The draw function of the game
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

public boolean collisionTest(PImage image, int x, int y, PImage pic, int xx, int yy) {        //The function that tests if two pictures collide
  //Defining bounding box of image
  float xStartOfImage = x - image.width/2*0.75f;                                        //25 percent from the left side of picture one. 75 percent to tighten the hit box
  float xEndOfImage = x + image.width/2*0.75f;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yStartOfImage = y - image.height/2*0.75f;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yEndOfImage = y + image.height/2*0.75f;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes
  //Defining bounding box of pic  
  float xxStartOfImage = xx - pic.width/2*0.75f;                                        //25 percent from the left side of picture two. 75 percent to tighten the hit box
  float xxEndOfImage = xx + pic.width/2*0.75f;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yyStartOfImage = yy - pic.height/2*0.75f;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yyEndOfImage = yy + pic.height/2*0.75f;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes

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

public boolean collisionTest(PImage image, int x, int y, PImage pic, float xx, float yy) {    //Overloaded collision test function
  //Defining bounding box of image
  float xStartOfImage = x - image.width/2*0.75f;                                        //25 percent from the left side of picture one. 75 percent to tighten the hit box
  float xEndOfImage = x + image.width/2*0.75f;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yStartOfImage = y - image.height/2*0.75f;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
  float yEndOfImage = y + image.height/2*0.75f;                                         //25 percent from the bottom of the picture. Adjusted for hitbox purposes
  //Defining bounding box of pic
  float xxStartOfImage = xx - pic.width/2*0.75f;                                        //25 percent from the left side of picture two. 75 percent to tighten the hit box
  float xxEndOfImage = xx + pic.width/2*0.75f;                                          //25 percent from the right side of the picture. Adjusted for hitbox purposes
  float yyStartOfImage = yy - pic.height/2*0.75f;                                       //25 percent from the top side of the picture. Adjusted for hitbox purposes
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

public void deathAnimation(PImage [] arr, int x, int y) {        //The function that displays the death animation, takes a PImage array and x,y coordinates as integers
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

public void deathAnimation(PImage [] arr, float x, float y) {    //Overloaded 6 picture animation that takes floating points instead of integers
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

public void twoPictureAnimation(PImage [] arr, int x, int y) {  //The function that produces the two picture animation (The movement animation)
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

public void twoPictureAnimation(PImage [] arr, float x, float y) {  //Overloaded two picture animation function that takes floats instead of ints
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

  public void display() {                                                              //The function that displays the shark
    if (_speed < 0) {                                                           //If the speed is under 0 the shark is moving to the left
      twoPictureAnimation(sharkAnimation, _x, _y);                              //Using the 2 picture animation function to display the shark movement animation
    } else {                                                                    //Else if the speed is over 0 the shark is moving to the right
      twoPictureAnimation(sharkAnimationR, _x, _y);                             //Using the 2 picture animation function to display the reversed shark movement animation
    }                                                                           //Closing the else statement
  }                                                                             //Closing the display function

  public void movement() {                                                             //The function that makes the shark move - The shark enemy moves horisontally
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

  public void sharkDeath() {                                                  //The function that displays death by shark
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

  public int returnSharkX() {                                                 //Function that returns the sharks position on the x axis
    return _x;                                                         //Return of the type integer
  }                                                                    //Close the returnSharkX function
  
  public int returnSharkSpeed() {                                            //Returns the current speed of the shark
    return _speed;                                                    //Returns the speed as an integer, used for defining which way the shark is currently heading
  }                                                                   //Close the function
  
  public int returnSharkY() {                                                //Function that returns the position on the Y axis 
    return _y;                                                        //Used for collision testing
  }                                                                   //Close the function

  public void resetValues() {                                                //Function that resets the values to the orignal values - So that when you die you are not left with impossible speeds
    _x = width/2;                                                     //X is in the middle of the screen
    _y = height/2;                                                    //Y is in the middle of the screen
    _speed = 1;                                                       //The speed is reset to 1
  }                                                                   //Close the function
}                                                                     //End of class definition

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

  public void display() {                                                          //Function that displays the ration
    if (showFood) {                                                         //If showFood is true
      image(fooditem, _x, _y);                                              //The image of the ration will be shown
    }                                                                       //Closing the if statement
  }                                                                         //Closing the display function

  public void display(boolean showFood) {                                          //Overloaded function that takes a boolean as parameter                                   
    if (showFood) {                                                         //If showFood is true
      image(fooditem, _x, _y);                                              //Display the image of the ration
    }                                                                       //Close the if statement
  }                                                                         //Close the function

  public void randomizer() {                                                       //Function that randomizes the position of the food item
    _t += 1;                                                                //The t timer goes up when the function is called
    if (_t > 300) {                                                         //If the timer hits 300
      item.rewind();                                                        //The sound for the collection of an item is rewinded
      _x = PApplet.parseInt(random(fooditem.width/2, width - fooditem.width/2));         //The x position is set from half the width of the image to the width minus half the width of the image - This is because the images is shown in center mode- Converted to int
      _y = PApplet.parseInt(random(fooditem.height/2, height - fooditem.height/2));      //The y position is set from half the height of the image to the height minus half the height of the image - This is because the images is shown in center mode - Converted to int
      _t = 0;                                                               //The timer is reset to 0
      showFood = true;                                                      //And the boolean showFood is set to true so that it is displayed
    }                                                                       //Closing the if statement
  }                                                                         //Closing the random function
  
  public void scoreCounter(boolean foodEaten) {                                    //Function that calculates your highscore
    if (foodEaten) {                                                        //If foodEaten is true
      _score += 10;                                                         //+10 to the score
      foodEaten = false;                                                    //Set food eaten to false
      _x = width + 500;                                                     //And move the position on the x axis way out of the screen so that the points only count up once. Maybe not the most elegant solution but it works
    }                                                                       //Closing the if statement
  }                                                                         //Closing the function

  public void highScore() {                                                        //Function that displays the highschore
    textFont(font, 48);                                                     //The font is defined by the variable font and the size is set to 48
    fill(255, 205, 0);                                                      //The text is colored to a delighting orange
    text("Highscore: " +_score+"", 10, 50);                                 //The text higscore + whatever your current score is, is displayed in the left hand corner
  }                                                                         //Closing the function

  public int returnFoodX() {                                                       //Function that returns the position on the x axis
    return _x;                                                              //Aaaaand returns x which is an integer
  }

  public int returnFoodY() {                                                       //Function that returns the position on the y axis
    return _y;                                                              //Aaaaand returns y which is an integer
  }                                                                         //Close the function

  public boolean returnShowFood() {                                                //Function that returns what the current state of the variable showFood is
    return showFood;                                                        //Returns showFood which is a boolean datatype
  }                                                                         //Close function

  public boolean returnFoodEaten() {                                               //Function that returns the current state of the foodEaten variable
    return foodEaten;                                                       //Returns foodEaten which is a boolean datatype
  }                                                                         //Close the function

  public int returnHighScore() {                                                   //Returns the current value of _score
    return _score;                                                          //which is an integer
  }                                                                         //Close the function

  public void resetValues() {                                                      //Function that resets the value - used upon game over
    _x = 900;                                                               //the position on the x axis
    _y = 570;                                                               //the position on the y axis
    _t = 0;                                                                 //reset the timer to 0
    _score = 0;                                                             //Reset the score to 0
  }                                                                         //Close the function
}                                                                           //Close the class definition

class Game {                                                                                          //Class definition for the Game class

  Game() {                                                                                            //Game class constructor - empty as can be

  }                                                                                                   //And close this marvelous constructor

  public void logic() {                                                                                      //logic function - contains every instance of collision test
    if (collisionTest(hero, pointX, pointY, cardboardBox, hideX, hideY)) {                            //If the hero and the cardboard box colides
      image(heroHidden, pointX, pointY);                                                              //Display the hidden hero
      manatee.movement();                                                                             //Make the hero able to move
      hide.display();                                                                                 //Display the hiding spot
      hide.randomizer();                                                                              //Randomize the hide position
      shark.display();                                                                                //Display the shark
      shark.movement();                                                                               //Make the shark move
      octo.display();                                                                                 //Display the octopus
      octo.movement();                                                                                //Make the octopus move
      manta.display();                                                                                //Display the mantaray
      manta.movement();                                                                               //Make the mantaray move
      killer.display();                                                                               //Display the orca
      killer.movement();                                                                              //Make the orca move
      food.display();                                                                                 //Display the ration
    } else if (collisionTest(hero, pointX, pointY, sharkpic, sharkX, sharkY) && heroSafe == false) {  //Else if the hero colides with the shark and the hero is not safe
      alert.play();                                                                                   //Play the alert sound
      hide.display();                                                                                 //Display the cardboard box
      hide.randomizer();                                                                              //Randomize the cardboard box location
      octo.display();                                                                                 //Display the octopus
      octo.movement();                                                                                //Make the octopus move
      manta.display();                                                                                //Display the mantaray - one killed Steve Irwin you know!
      manta.movement();                                                                               //Make the mantaray move
      killer.display();                                                                               //Display the orca
      killer.movement();                                                                              //Make the orca move
      food.display();                                                                                 //Display the ration
      shark.sharkDeath();                                                                                 //And finally display the death animation of the shark, along with the death screen
    } else if (collisionTest(hero, pointX, pointY, enemyOctopus, octoX, octoY) && heroSafe == false) {    //If the hero and the octpus colides and the hero is not safe
      alert.play();                                                                                       //Play the alert sound
      hide.display();                                                                                     //Display the cardboard box
      hide.randomizer();                                                                                  //Randomize the carboard box position
      shark.display();                                                                                    //Display the shark
      shark.movement();                                                                                   //Make the shark move
      manta.display();                                                                                    //Display the mantaray
      manta.movement();                                                                                   //Make the mantaray move
      killer.display();                                                                                   //Display the orca
      killer.movement();                                                                                  //Make the orca move
      food.display();                                                                                     //Display the ration
      octo.octoDeath();                                                                                   //And finally display the death animation of the octopus along with the death screen
    } else if (collisionTest(hero, pointX, pointY, enemyManta, mantaX, mantaY) && heroSafe == false) {    //If the hero colides with the mantaray and the hero is not safe
      alert.play();                                                                                       //Play the alert sound
      hide.display();                                                                                     //Display the cardboard box
      hide.randomizer();                                                                                  //Randomize the cardboard box position
      shark.display();                                                                                    //Display the shark
      shark.movement();                                                                                   //Make the shark move
      octo.display();                                                                                     //Display the octopus
      octo.movement();                                                                                    //Make the octopus move
      killer.display();                                                                                   //Display the orca
      killer.movement();                                                                                  //Make the orca move
      food.display();                                                                                     //Display the ration
      manta.mantaDeath();                                                                                 //And finally display the death animation of the mantaray along with the death screen
    } else if (collisionTest(hero, pointX, pointY, enemyWhale, whaleX, whaleY) && heroSafe == false) {    //If the hero colides with the orca and the hero is not safe
      alert.play();                                                                                       //Play the alert sound
      hide.display();                                                                                     //Display the cardboard box
      hide.randomizer();                                                                                  //Randomize the cardboard box
      shark.display();                                                                                    //Display the shark
      shark.movement();                                                                                   //Make the shark move
      octo.display();                                                                                     //Display the octopus
      octo.movement();                                                                                    //Make the octopus move
      manta.display();                                                                                    //Display the mantaray
      manta.movement();                                                                                   //Make the mantaray move
      food.display();                                                                                     //Display the ration
      killer.whaleDeath();                                                                                //And finally display the death animation of the orca along with the death screen
    } else if (collisionTest(hero, pointX, pointY, fooditem, foodX, foodY)) {                             //If the hero colides with the ration
      item.play();                                                                                        //Play the item collect sound
      hide.display();                                                                                     //Display the cardboard box
      hide.randomizer();                                                                                  //Randomize the cardboard box location
      shark.display();                                                                                    //Display the shark
      shark.movement();                                                                                   //Make the shark move
      octo.display();                                                                                     //Display the octopus
      octo.movement();                                                                                    //Make the octopus move
      manta.display();                                                                                    //Display the mantaray
      manta.movement();                                                                                   //Make the mantaray move
      killer.display();                                                                                   //Display the orca
      killer.movement();                                                                                  //Make the orca move
      showFood = false;                                                                                   //Change the state of showfood to false
      foodEaten = true;                                                                                   //And the state of foodEaten to true
      food.display(showFood);                                                                             //Parse showfood through the ration display function
      food.scoreCounter(foodEaten);                                                                       //And the foodEaten through the score counter so that it adds 10 to the highscore
      manatee.display();                                                                                  //Display the hero
      manatee.movement();                                                                                 //Alow the hero to move
    } else {                                                                                              //If the hero isn't coliding with anything
      manatee.movement();                                                                                 //Alow the hero to move
      if (heroSafe) {                                                                                     // If the heroSafe = true
        manatee.isHeroSafe(heroSafe);                                                                     //Parse heroSafe through the isHeroSafe function - the hero blinks showing immortality
      } else {                                                                                            //Otherwise
        manatee.display();                                                                                //Just display the regular hero
      }                                                                                                   //Closing the else statement
      hide.display();                                                                                     //Display the cardboard box
      hide.randomizer();                                                                                  //Randomize the cardboard box
      shark.display();                                                                                    //Display the shark
      shark.movement();                                                                                   //Make the shark move
      octo.display();                                                                                     //Display the octopus
      octo.movement();                                                                                    //Make the octopus move
      manta.display();                                                                                    //Display the mantaray
      manta.movement();                                                                                   //Make the mantaray move
      killer.display();                                                                                   //Display the orca
      killer.movement();                                                                                  //Make the orca move
      food.display();                                                                                     //Display the ration
    }                                                                                                     //Close the else statement
  }                                                                                                       //Close the logic function        
}                                                                                                         //Close the class definition

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

  public void display() {                                                                         //Display function for the health class
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

  public void healthCalculator() {                                                                //Function that calculates health when hit
    _health -= 1;                                                                          //If the function is called 1 health is subtracted
  }                                                                                        //Close the function

  public int returnHealth() {                                                                     //Function that returns the current health so that it can be used everywhere in the game
    return _health;                                                                        //Returns an integer
  }                                                                                        //Close the function

  public void resetValues() {                                                                     //Function that resets the value of the health variable
    _health = 3;                                                                           //Health is set to 3
  }                                                                                        //Close the function
}                                                                                          //Close the class definition

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

  public void movement() {                                                       //Function that moves the hero
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

  public void display() {                                                                     //Function that displays the hero
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

  public void isHeroSafe(boolean safe) {                                                      //Function that set the heros status to safe or not
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

  public boolean returnHeroSafe() {                                                           //Function that returns boolean
    return _safe;                                                                      //Return the _safe variable
  }                                                                                    //Close the function

  public int returnManateeX() {                                                               //Function that returns integer
    return pointX;                                                                     //Returns heros x position
  }                                                                                    //Close function

  public int returnManateeY() {                                                               //Function that returns integer
    return pointY;                                                                     //Returns heros y position
  }                                                                                    //Close function
}                                                                                      //Close class definition

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

  public void display() {                                                               //Function that displays the hiding place
    if (_t < 420) {                                                              //If the timer is under 420 (420/60 = 7 seconds)
      image(cardboardBox, _x, _y);                                               //Display the cardboard box
    } else {                                                                     //When the timer is over 420
      twoPictureAnimation(hidespotAnimation, _x, _y);                            //Display the blinking animation
    }                                                                            //Close the else statement
  }                                                                              //Close the function

  public void randomizer() {                                                            //Function that randomizes the position of the cardboardbox
    _t += 1;                                                                     //Add one to the timer
    if (_t > 600) {                                                              //When the timer hits 600
      _x = PApplet.parseInt(random(cardboardBox.width/2, width - cardboardBox.width/2));      //Change the x position to a random position
      _y = PApplet.parseInt(random(cardboardBox.height/2, height - cardboardBox.height/2));   //Change the y position to a random position
      _t = 0;                                                                    //Reset the timer
    }                                                                            //Close the if statement
  }                                                                              //Close the function

  public int returnHideX() {                                                            //Function that returns integer
    return _x;                                                                   //Return the current x position
  }                                                                              //Close the function

  public int returnHideY() {                                                            //Function that returns an integer
    return _y;                                                                   //Return the y position
  }                                                                              //Close the function

  public void resetValues() {                                                           //Function that resets the values
    _x = 500;                                                                    //Reset the x position
    _y = 250;                                                                    //Reset the y position
    _t=0;                                                                        //Reset the timer
  }                                                                              //Close the function
}                                                                                //Close the class definition

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

  public void display() {                                                              //Function that displays the mantaray
    if (_speed < 0) {                                                           //When the mantaray is moving to the left
      twoPictureAnimation(mantaAnimationR, _x, _y);                             //Display the flipped movement animation
    } else {                                                                    //When the mantaray is moving to the right
      twoPictureAnimation(mantaAnimation, _x, _y);                              //Display the movement animation
    }                                                                           //Close the else statement
  }                                                                             //Close the function

  public void movement() {                                                             //Function that moves the mantaray
    if (_x >= width - enemyManta.width/2) {                                     //If the mantaray hits the right edge
      _speed *= -1.00f;                                                          //Reverse the speed
    } else if (_x <= enemyManta.width/2) {                                      //If the mantaray hits the left edge
      _speed = abs(_speed);                                                     //Reverse the speed
    }                                                                           //Close the else if statement
    _x += _speed;                                                               //Speed is added to the x position
    _y = (768.0f/1366.0f)*_x;                                                     //Mathematical formular for points in a line - y=mx+b
    _t +=1;                                                                     //Add one to the timer
    if (_t > 900 && _speed > 0) {                                               //When the timer hits 900 and the mantaray is moving right
      _speed += 1;                                                              //Add one to the speed
      _t = 0;                                                                   //Reset the timer
    } else if (_t > 900 && _speed < 0) {                                        //When the timer hits 900 and the mantaray is moving left
      _speed -= 1;                                                              //Subtract one from the speed
      _t = 0;                                                                   //Reset the timer
    }                                                                           //Close else if statement
  }                                                                             //Close the function

  public void mantaDeath() {                                                           //Function that displays death by mantaray
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

  public float returnMantaX() {                                                        //Function that returns float
    return _x;                                                                  //Return the x position
  }                                                                             //Close the function

  public float returnMantaY() {                                                        //Function that returns float
    return _y;                                                                  //Return the y position
  }                                                                             //Close the function

  public int returnMantaSpeed() {                                                      //Function that returns integer
    return _speed;                                                              //Returns the speed of the mantaray
  }                                                                             //Close the function

  public void resetValues() {                                                          //Function that resets the values
    _x = width - enemyManta.width/2;                                            //Set the x position
    _y = height - enemyManta.height/2;                                          //Set the y position
    _speed = 1;                                                                 //Set the speed
  }                                                                             //Close the function
}                                                                               //Close the class definition

class Menu {                                                                                                   //Creating the class desctription of the class Menu  

    PImage _credits;                                                                                           //Declaring the PImage for the credit button variable
  PImage _creditsOver;                                                                                         //Declaring the PImage for the credit button when the mouse is hovering over it
  PImage _howto;                                                                                               //Declaring the PImage for the "how to" button
  PImage _howtoOver;                                                                                           //Declaring the PImage for the "how to button when the mouse is hovering over it
  PImage _play;                                                                                                //Declaring the PImage for the "play" button
  PImage _playOver;                                                                                            //Declaring the PImage for the "play" button when the mouse is hovering over it

  Menu() {                                                                                                                 //Defining the class constructor
    _credits = loadImage("credits.png");                                                                                   //loading image for the credit button
    _creditsOver = loadImage("creditsover.png");                                                                           //loading image for the credit button while hovering
    _howto = loadImage("howto.png");                                                                                       //loading image for the how to button
    _howtoOver = loadImage("howtoover.png");                                                                               //loading image for the how to button while hovering
    _play = loadImage("play.png");                                                                                         //loading image for the play button
    _playOver = loadImage("playover.png");                                                                                 //loading image for the play button while hovering
  }                                                                                                                        //Closing the class constructor

  public void display() {                                                                                                         //Declaring a method called display in the class

                                                                                                                           //If the mouse is between the coordinates and the mouse is pressed,
    if (mouseX > 66 && mouseX < 389 && mouseY > 622 && mouseY < 737 && mousePressed) {                                     //change state of program to reflect the choice, level page          
      image(_playOver, 228, 680);                                                                                          //Show the hover image for the play button                                          
      image(_howto, 662, 680);                                                                                             //Still show the other buttons even when mouse is pressed!
      image(_credits, 1112, 676);                                                                                          //Still show the other buttons even when mouse is pressed!
      current = loadingPage;                                                                                                 //Changes state of the program. Changes background to the loading jpg
    } else if (mouseX > 66 && mouseX < 389 && mouseY > 622 && mouseY < 737) {                                              //If the mouse is within the coordinates change to show hover button
      image(_playOver, 228, 680);                                                                                          //Display play hover button
      image(_howto, 662, 680);                                                                                             //Still show the other buttons!
      image(_credits, 1112, 676);                                                                                          //Still show the other buttons!

      //If the mouse is between the coordinates and the mouse is pressed,
    } else if (mouseX > 458 && mouseX < 865 && mouseY > 622 && mouseY < 737 && mousePressed) {                             //change state of program to reflect the choice, "how to" page
      image(_play, 228, 680);                                                                                              //Show the buttons while pressing the mouse!
      image(_howtoOver, 662, 680);                                                                                         //Display hover button
      image(_credits, 1112, 676);                                                                                          //Still show other buttons!
      current = howToPage;                                                                                                 //Changes state of program. Changes background to the "how to" jpg
    } else if (mouseX > 458 && mouseX < 865 && mouseY > 622 && mouseY < 737) {                                             //If the mouse is within the coordinates change to show hover button
      image(_play, 228, 680);                                                                                              //Still show other buttons!
      image(_howtoOver, 662, 680);                                                                                         //Display hover button!         
      image(_credits, 1112, 676);                                                                                          //Stil show other buttons!

      //If the mouse is between the coordinates and the mouse is pressed,
    } else if (mouseX > 908 && mouseX < 1315 && mouseY > 622 && mouseY < 737 && mousePressed) {                            //change state of program to reflect the choice, credits page
      image(_play, 228, 680);                                                                                              //Still show other buttons!
      image(_howto, 662, 680);                                                                                             //Still show other buttons!
      image(_creditsOver, 1112, 676);                                                                                      //Display the hover button!
      current = creditsPage;                                                                                               //Changes state of program. Changes background to the "credits" jpg
    } else if (mouseX > 908 && mouseX < 1315 && mouseY > 622 && mouseY < 737) {                                            //If the mouse is within the coordinates change to show hover button
      image(_play, 228, 680);                                                                                              //Still show other buttons!
      image(_howto, 662, 680);                                                                                             //Still show other buttons!
      image(_creditsOver, 1112, 676);                                                                                      //Display the hover button!
    } else {                                                                                                               //In the case that none of the above is true, do the following
      image(_play, 228, 680);                                                                                              //Display play button
      image(_howto, 662, 680);                                                                                             //Display how to button
      image(_credits, 1112, 676);                                                                                          //Display credits button
    }                                                                                                                      //Close the else statement
  }                                                                                                                        //Close the display function
}                                                                                                                          //Close the class definition

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
  
  public void display() {                                                           //Function that displays the octopus
    if (_speed < 0) {                                                        //If the octopus moves up
      twoPictureAnimation(octoAnimation, _x, _y);                            //Play the movement animation
    } else {                                                                 //If the octopus moves down
      twoPictureAnimation(octoAnimationR, _x, _y);                           //Play the flipped animation
    }                                                                        //Close the else statement
  }                                                                          //Close the function

  public void movement() {                                                          //Function that moves the octopus
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

  public void octoDeath() {                                                         //Function that displays death by octopus
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

  public int returnOctoY () {                                                       //Function that returns integer
    return _y;                                                               //Return the y position
  }                                                                          //Close the function

  public int returnOctoX() {                                                        //Function that returns integer
    return _x;                                                               //Return x position
  }                                                                          //Close function

  public int returnOctoSpeed() {                                                    //Function that returns integer
    return _speed;                                                           //Return the current speed
  }                                                                          //Close function

  public void resetValues() {                                                       //Function that resets values
    _speed = 1;                                                              //Set speed to 1
    _x = width/2;                                                            //Set x position
    _y = 100;                                                                //Set y position
  }                                                                          //Close function
}                                                                            //Close class definition

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

  public void display() {                                                                     //function that displays the orca
    if (_speed < 0) {                                                                  //If the orca is moving to the left
      twoPictureAnimation(whaleAnimationR, _x, _y);                                    //display the flipped movement animation
    } else {                                                                           //if the orca is moving to the right
      twoPictureAnimation(whaleAnimation, _x, _y);                                     //display the movement animation
    }                                                                                  //close the else statement
  }                                                                                    //close the function

  public void movement() {                                                                    //function that moves the orca
    if (_x >= width - enemyWhale.width/2) {                                            //if the orca hits the right edge of the screen
      _speed *= -1;                                                                    //reverse the speed
    } else if (_x <= enemyWhale.width/2) {                                             //if the orca hits the left edge of the screen
      _speed = abs(_speed);                                                            //reverse the speed
    }                                                                                  //close the else if statement
    _x += _speed;                                                                      //add the speed to the x coordinate
    _y = (-768.0f/1366.0f)*_x + 768.0f;                                                   //Mathematical formula for coordinates on a line - y=mx+b
    _t += 1;                                                                           //add one to the timer
    if (_t > 900 && _speed > 0) {                                                      //if the speed timer hits 900 and the orca is moving to the right
      _speed += 1;                                                                     //add one to the speed
      _t = 0;                                                                          //reset the timer
    } else if (_t > 900 && _speed < 0) {                                               //if the speed timer hits 900 and the orca is moving to the left
      _speed -= 1;                                                                     //subtract one from the speed
      _t = 0;                                                                          //reset the timer
    }                                                                                  //close the else if statement
  }                                                                                    //close the function

  public void whaleDeath() {                                                                  //function that displays the death animation for death by orca
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

  public float returnWhaleX() {                                                               //function that returns a floating value
    return _x;                                                                         //return the x position
  }                                                                                    //close the function
  
  public float returnWhaleY() {                                                               //function that returns a floating value
    return _y;                                                                         //return the y position
  }                                                                                    //close the function

  public int returnWhaleSpeed() {                                                             //function that returns an integer
    return _speed;                                                                     //return the current speed
  }                                                                                    //close the function

  public void resetValues() {                                                                 //function that resets the values
    _x = width - enemyWhale.width/2;                                                   //set the x position
    _y = enemyWhale.height/2;                                                          //set the y position
    _speed = 1;                                                                        //set the speed back to 1
  }                                                                                    //close the function
}                                                                                      //close the class description

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "metalgearmanatee" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

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

  void display() {                                                                                                         //Declaring a method called display in the class

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


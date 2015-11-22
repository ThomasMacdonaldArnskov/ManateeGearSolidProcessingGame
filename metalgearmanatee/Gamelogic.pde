class Game {                                                                                          //Class definition for the Game class

  Game() {                                                                                            //Game class constructor - empty as can be

  }                                                                                                   //And close this marvelous constructor

  void logic() {                                                                                      //logic function - contains every instance of collision test
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


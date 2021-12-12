# COMP2042_CW_hcykt3

##MAINTENANCE & EXTENSION

>##REFACTORING

###1. Apply Model-View-Controller Pattern
4 packages were created which are Controller, Model, View and Game. 
All the classes were partitioned into respective packages based on functionalities. 
In Model, there are classes that stores and works on the data.
In View, there are classes that renders the game view. 
In Controller, I modified the code in GameFrame to act as the main connector to connect View and Controller classes 
and rename it as GameFrameController. 
Also, 3 Controller classes are created to act as the coordinators between Model and View:
> DebugController - DebugPanel (Model) and DebugConsole (View)

> GameController - Wall (Model) and GameBoard (View)

> ScoreController - Score (Model) and ScoreBoard (View) 

###2. BASIC MAINTENANCE

####2.1 APPLY SINGLE RESPONSIBILITY PRINCIPLE
In original source code, some classes implemented multiple functionalities. 
Hence, I modified. 
For instance, I split the nested class, Crack, out from the Brick Class to improve the application of single responsibility.


####2.2 RENAMING METHODS AND VARIABLES
Some names of the methods and variables in original was not accurate, or rather, wrong.
Therefore, I modified.
For instance, in HomeMenu class, I renamed the MENU_TEXT, menuButton to EXIT_TEXT, exitButton based on their function.
Furthermore, there was a repeated name, makeCrack, is used in Crack class.
Hence, I rename one of them to drawCrack based on functionality.

####2.3 IMPROVE ENCAPSULATIONS
I improved the encapsulations of the code. 
In Ball class, I changed the access modifiers of up, down, left and right variables to private.
Then, added the new getter methods for them.

###3. MAVEN AND JUNIT TEST
####3.1 MAVEN
Maven was used to create build files.

####3.2 JUNIT TEST
9 test classes were created for the classes in package Model.

>##ADDITIONS
###1. PERMANENT HIGH SCORE 
Score (Model) and ScoreBoard (View) were created to deal with the scoring system.
During the game, the current game score will be displayed.
When game is over in normal way (exclude exit/close the game), the Score object will sort the result and create a new ranking based on the score.
After that, save to the Score.txt.
In the home menu page, there is a button "Scoreboard" added. 
Score board will be displayed as it is clicked.

###2. INFORMATION MENU 
InfoMenu (View) was created to display an information menu to player.
In the home menu page, there is a button "Information" added.
Information menu will be displayed as it is clicked.

###3. GAME OVER MENU 
GameOverMenu (View) was created to display a game over menu to player.
When the game is over, a game over menu will be displayed. 
It will demonstrate the final game score and display comments according to the resulted score. 
Furthermore, there is a back button at the bottom.  

###4. ADDITIONAL LEVEL by SlimeBrick
3 more levels related with SlimeBrick (Model) are added.
SlimeBrick is a special brick that moves the ball to random position as an impact between ball and brick is occurred.
It adds more fun to the game and makes the game becoming difficult.  

















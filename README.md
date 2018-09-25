# Memory-Game
Memory Game (java)

Introduction The goal of this assignment is to build a stand-alone Java program that will allow a user to play the Memory game.
The graphical user interface supports the basic elements of the game: start, restart, play of the game itself, recording
the number of guesses so far, and detecting the termination state. 

The Game Memory is played on a 5x5 board (5 rows and 5 columns). 
One element of the grid is a “happy face” which doesn’t match anything. 
Once that is selected it is revealed at no penalty. 
All other elements of the grid represent a card. 
The game starts with all positions on the board blank, the “guesses made” counter at 0 and the “matches made” counter at 0. 


Play is as follows: 
• The player makes a guess by clicking with the mouse on one grid element (card).
  The game toggles that grid to reveal the pattern it hides (a unique image). 
• If that pattern is the “happy face”, the “guesses made” counter is NOT incremented, 
the card is revealed (it has no match), and play continues. 
• The player revealed a non-“happy face” card, the player must make a second guess by 
clicking on another square with the mouse. If the second square reveals a “match” with the first square, 
the squares are permanently displayed (remain unhidden and are no longer responsive to clicks) and the “matches made” 
counter is incremented. If the second guess is the “happy face”, it is NOT a match. 
• If the two selected squares do not match, both are toggle back to the hidden state 
• The “number of guesses” counter is incremented by 1 
• The game ends when all grid elements are revealed.

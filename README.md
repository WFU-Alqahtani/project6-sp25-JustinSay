Within this project I have created a program that allows for me to play blind man's game against a computer. 
The only problem that I am having so far is that the randomize feature is not really that randomize but appears random which makes it so that sometimes we get the same suit over and over. However that is apart of 
the program. The sanity check was helpful in order to verify that I was going through every card and that cards were not being lost, this makes it easier for me to know that the methods are wokring as how I 
intended them to. The hardest part about this project was making sure that I was not losing any cards and making sure that the ragequit was working properly. Otherwise this project was very doable and demonstrated how
doubly linkedlist can be used in a game such as cards for structuring the shuffling and how the game operates. 

I essentially filled in the gaps for the methods that were not completed yet, applied them to where they can be used, and utilized that one working part of my code to ensure that the rest of the code works. 
Initialization begins with the going throuhg all of the cards, which was already added beforehand. 

I then initialize play blind man's bluff, I get the necessary conditions to track wins, losses, and the rage quit feature (consecutive losses). I then ask for user input for higher or lower based upon the computer's
hand and the player's hand which they do not know. After that I do a comparison, the cases that I established should track whether it is a win or loss based on whether they say higher or lower, if it matches the suit
then it has to beat out the rank. If there are 3 consecutive losses there is a rage quit. Otherwise the game goes on for 5 turns and tells you how many times you won and lost at the end. 


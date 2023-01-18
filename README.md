# Blackjack

This application will allow you to play the game Blackjack via a command line interface. 

## Game Rules

The objective of the game is to beat the dealer, which can be done in the following ways:

* Get 21 points (blackjack)
* Reach a final score higher than the dealer without exceeding 21
* Let the dealer draw additional cards until his or her hand exceeds 21 (bust)

The game is implemented with standard 1 deck of cards. A player can chose to hit or stand after receiving 2 inital cards  
* Hit - Take another card from dealer
* Stand - Player takes no more cards and dealer draws the card 

Following rules are implemented for the dealer in the game:

* Dealer stands on 17 or more 
* Dealer hits until his cards total 17 or more points

The player is paid according to the standard method: 

* Player get paid 2:1 for BlackJack 
* Player 1:1 for other win

In the case of a tied score, known as "push" or "standoff", bets are returned without adjustment


### Prerequisites

JDK Version 17 or 19 

```
java -version
```

### Running the Game 

Clone repository to machine and run the Blackjack.java file inside the src directory. 



```
git clone https://github.com/keyadaniel/Blackjack.git
```

### Additional Features 
Player enters the game with a set amount of money and places a bet on each hand


## Sample Output of Game  

![image](https://user-images.githubusercontent.com/74474124/213061870-ac069f4b-a7bf-4075-a1b8-16025a86eea5.png)



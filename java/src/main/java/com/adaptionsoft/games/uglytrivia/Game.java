package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final List<Player> players;

    private final QuestionDispenser questionDispenser;

    private int currentPlayer = 0;

    public Game(Player playerOne, Player playerTwo, QuestionDispenser questionDispenser)
    {
        if (playerOne == null || playerTwo == null)
        {
            throw new IllegalArgumentException("Players cannot be null");
        }

        if (questionDispenser == null)
        {
            throw new IllegalArgumentException("A question dispenser needs to be provided");
        }

        this.players = new ArrayList<>();
        add(playerOne);
        add(playerTwo);

        this.questionDispenser = questionDispenser;
    }

    public void add(final Player player)
    {
        players.add(player);

        displayAddedPlayer(player);
    }

    public void roll(int roll)
    {
        displayCurrentPlayerRoll(roll);

        if (currentPlayer().inPenaltyBox() && !currentPlayerIsFreed(roll))
        {
            return;
        }

        doRoll(roll);
    }

    public boolean wasCorrectlyAnswered()
    {
        if (currentPlayer().inPenaltyBox())
        {
            nextPlayer();
            return true;
        }

        incrementCurrentPlayerPurse();

        boolean winner = currentPlayer().isWinner();
        nextPlayer();

        return winner;
    }

    public boolean wrongAnswer()
    {
        sendCurrentPlayerToPenaltyBox();

        nextPlayer();
        return true;
    }

    private void sendCurrentPlayerToPenaltyBox()
    {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer() + " was sent to the penalty box");
        currentPlayer().inPenaltyBox(true);
    }

    private boolean currentPlayerIsFreed(final int roll)
    {
        if (!isGettingOutOfPenaltyBox(roll))
        {
            System.out.println(currentPlayer() + " is not getting out of the penalty box");
            return false;
        }

        setCurrentPlayerFree();
        return true;
    }

    private void setCurrentPlayerFree()
    {
        System.out.println(currentPlayer() + " is getting out of the penalty box");
        currentPlayer().inPenaltyBox(false);
    }

    private int howManyPlayers()
    {
        return players.size();
    }

    private boolean isGettingOutOfPenaltyBox(final int roll)
    {
        return (roll % 2 != 0);
    }

    private void doRoll(final int roll)
    {
        currentPlayer().move(roll);
        displayCurrentPlayerNewLocation();
        displayCurrentCategory();
        askQuestion();
    }

    private void askQuestion()
    {
        questionDispenser.askQuestion(currentPlayer().position());
    }

    private void incrementCurrentPlayerPurse()
    {
        System.out.println("Answer was correct!!!!");
        currentPlayer().incrementPurse();
        System.out.println(currentPlayer() + " now has " + currentPlayer().purse() + " Gold Coins.");
    }

    private void nextPlayer()
    {
        currentPlayer++;
        if (lastPlayer())
        {
            currentPlayer = 0;
        }
    }

    private boolean lastPlayer()
    {
        return currentPlayer == players.size();
    }

    private Player currentPlayer()
    {
        return players.get(currentPlayer);
    }

    private void displayAddedPlayer(final Player player)
    {
        System.out.println(player.name() + " was added");
        System.out.println("They are player number " + howManyPlayers());
    }

    private void displayCurrentPlayerRoll(final int roll)
    {
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);
    }

    private void displayCurrentCategory()
    {
        System.out.println("The category is " + CategoryChooser.currentCategory(currentPlayer().position()));
    }

    private void displayCurrentPlayerNewLocation()
    {
        System.out.println(currentPlayer() + "'s new location is " + currentPlayer().position());
    }
}

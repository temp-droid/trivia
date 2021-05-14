package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Game
{
    private final List<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final int[] purses = new int[6];
    private final boolean[] inPenaltyBox = new boolean[6];

    private final LinkedList<String> popQuestions = new LinkedList<>();
    private final LinkedList<String> scienceQuestions = new LinkedList<>();
    private final LinkedList<String> sportsQuestions = new LinkedList<>();
    private final LinkedList<String> rockQuestions = new LinkedList<>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game()
    {
        for (int i = 0; i < 50; i++)
        {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable()
    {
        return (howManyPlayers() >= 2);
    }

    public void add(String playerName)
    {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers()
    {
        return players.size();
    }

    public void roll(int roll)
    {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        isGettingOutOfPenaltyBox = (roll % 2 != 0);
        if (inPenaltyBox[currentPlayer])
        {
            if (!isGettingOutOfPenaltyBox)
            {
                System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
                return;
            }
            System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
        }

        doRoll(roll);
    }

    private void displayCurrentPlayerNewLocation()
    {
        System.out.println(getCurrentPlayer()
                + "'s new location is "
                + getCurrentPlayerPlace());
    }

    private void displayCurrentCategory()
    {
        System.out.println("The category is " + CategoryChooser.currentCategory(getCurrentPlayerPlace()));
    }

    private void doRoll(final int roll)
    {
        moveCurrentPlayer(roll);
        displayCurrentPlayerNewLocation();
        displayCurrentCategory();
        askQuestion();
    }

    private void moveCurrentPlayer(final int roll)
    {
        places[currentPlayer] = getCurrentPlayerPlace() + roll;
        if (getCurrentPlayerPlace() > 11)
        {
            places[currentPlayer] = getCurrentPlayerPlace() - 12;
        }
    }

    private void askQuestion()
    {
        switch (CategoryChooser.currentCategory(getCurrentPlayerPlace()))
        {
            case "Pop":
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }

    public boolean wasCorrectlyAnswered()
    {
        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox)
        {
            nextPlayer();
            return true;
        }

        incrementCurrentPlayerPurse();

        boolean winner = didPlayerWin();
        nextPlayer();

        return winner;
    }

    private void incrementCurrentPlayerPurse()
    {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(getCurrentPlayer()
                + " now has "
                + getCurrentPlayerPurse()
                + " Gold Coins.");
    }

    public boolean wrongAnswer()
    {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        nextPlayer();
        return true;
    }

    private void nextPlayer()
    {
        currentPlayer++;
        if (currentPlayer == players.size())
        {
            currentPlayer = 0;
        }
    }

    private String getCurrentPlayer()
    {
        return players.get(currentPlayer);
    }

    private int getCurrentPlayerPlace()
    {
        return places[currentPlayer];
    }

    private int getCurrentPlayerPurse()
    {
        return purses[currentPlayer];
    }

    private boolean didPlayerWin()
    {
        return !(getCurrentPlayerPurse() == 6);
    }
}

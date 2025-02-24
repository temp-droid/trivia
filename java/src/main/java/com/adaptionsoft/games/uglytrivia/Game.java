package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.uglytrivia.question.QuestionDispenser;
import com.adaptionsoft.games.uglytrivia.question.TopicChooser;
import com.adaptionsoft.games.uglytrivia.question.model.Question;
import com.adaptionsoft.games.uglytrivia.question.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    private final List<Player> players;

    private final TopicChooser topicChooser;
    private final QuestionDispenser questionDispenser;

    private int currentPlayer = 0;

    public Game(Player playerOne, Player playerTwo, TopicChooser topicChooser, QuestionDispenser questionDispenser)
    {
        validateInputs(playerOne, playerTwo, topicChooser, questionDispenser);

        this.players = new ArrayList<>();
        add(playerOne);
        add(playerTwo);

        this.topicChooser = topicChooser;
        this.questionDispenser = questionDispenser;
    }

    public void add(final Player player)
    {
        players.add(player);

        displayAddedPlayer(player);
    }

    public void roll(final Roll roll)
    {
        displayCurrentPlayerRoll(roll);

        if (currentPlayerStaysInPenaltyBox(roll))
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

    private boolean currentPlayerStaysInPenaltyBox(final Roll roll)
    {
        return currentPlayer().inPenaltyBox() && !currentPlayerIsFreed(roll);
    }

    private boolean currentPlayerIsFreed(final Roll roll)
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

    private boolean isGettingOutOfPenaltyBox(final Roll roll)
    {
        return (roll.value() % 2 != 0);
    }

    private void doRoll(final Roll roll)
    {
        currentPlayer().move(roll);
        displayCurrentPlayerNewLocation();
        displayCurrentCategory();
        askQuestion();
    }

    private void askQuestion()
    {
        Topic topic = topicChooser.topic(currentPlayer().position());
        Question question = questionDispenser.dispense(topic);

        System.out.println(question);
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

    private void validateInputs(final Player playerOne, final Player playerTwo, final TopicChooser topicChooser, final QuestionDispenser questionDispenser)
    {
        if (playerOne == null || playerTwo == null)
        {
            throw new IllegalArgumentException("Players cannot be null");
        }

        if (topicChooser == null)
        {
            throw new IllegalArgumentException("A topic chooser needs to be provided");
        }

        if (questionDispenser == null)
        {
            throw new IllegalArgumentException("A question dispenser needs to be provided");
        }
    }

    private void displayAddedPlayer(final Player player)
    {
        System.out.println(player.name() + " was added");
        System.out.println("They are player number " + howManyPlayers());
    }

    private void displayCurrentPlayerRoll(final Roll roll)
    {
        System.out.println(currentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll.value());
    }

    private void displayCurrentCategory()
    {
        System.out.println("The category is " + topicChooser.topic(currentPlayer().position()));
    }

    private void displayCurrentPlayerNewLocation()
    {
        System.out.println(currentPlayer() + "'s new location is " + currentPlayer().position());
    }
}

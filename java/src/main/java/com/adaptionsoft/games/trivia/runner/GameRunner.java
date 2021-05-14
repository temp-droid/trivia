package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;

import java.util.Random;

public class GameRunner
{

    public static void main(String[] args)
    {
        Random rand = new Random();
        playGame(rand);
    }

    public static void playGame(final Random rand)
    {
        Player chet = new Player("Chet");
        Player pat = new Player("Pat");

        Game aGame = new Game(chet, pat);

        aGame.add(new Player("Sue"));

        boolean notAWinner;
        do
        {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7)
            {
                notAWinner = aGame.wrongAnswer();
            }
            else
            {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
}

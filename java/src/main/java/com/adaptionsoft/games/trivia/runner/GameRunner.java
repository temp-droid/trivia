package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Roll;
import com.adaptionsoft.games.uglytrivia.question.QuestionDispenser;

import java.util.Random;

public class GameRunner
{

    public static void main(String[] args)
    {
        Random random = new Random();
        playGame(random);
    }

    public static void playGame(final Random random)
    {
        Player chet = new Player("Chet");
        Player pat = new Player("Pat");
        QuestionDispenser dispenser = new QuestionDispenser();

        Game aGame = new Game(chet, pat, dispenser);

        aGame.add(new Player("Sue"));

        boolean notAWinner;
        do
        {
            aGame.roll(new Roll(random.nextInt(5) + 1));

            if (random.nextInt(9) == 7)
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

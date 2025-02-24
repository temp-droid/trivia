package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.stream.IntStream;

public class GameRunnerTest
{
    @Test
    public void playGame()
    {
        Random randomizer = new Random(12345);

        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultStream));

        IntStream.range(1, 15).forEach(i -> GameRunner.playGame(randomizer));

        Approvals.verify(resultStream.toString());
    }
}

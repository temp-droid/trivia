package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.question.QuestionDispenser;
import org.junit.Test;

public class QuestionDispenserTest
{
    @Test(expected = IllegalArgumentException.class)
    public void dispenser_throws_on_unknown_topic()
    {
        QuestionDispenser dispenser = new QuestionDispenser();

        dispenser.dispense(null);
    }
}

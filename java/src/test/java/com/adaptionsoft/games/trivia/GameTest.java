package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.question.QuestionDispenser;
import com.adaptionsoft.games.uglytrivia.question.TopicChooser;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class GameTest
{
    @Test
    public void enforce_at_least_two_players()
    {
        try
        {
            new Game(null, null, new TopicChooser(), new QuestionDispenser());
            fail();
        }
        catch (IllegalArgumentException ex)
        {
            assertThat(ex.getMessage(), is("Players cannot be null"));
        }
    }

    @Test
    public void enforce_valid_topic_chooser()
    {
        try
        {
            new Game(new Player("player one"), new Player("player two"), null, new QuestionDispenser());
            fail();
        }
        catch (IllegalArgumentException ex)
        {
            assertThat(ex.getMessage(), is("A topic chooser needs to be provided"));
        }
    }

    @Test
    public void enforce_valid_question_dispenser()
    {
        try
        {
            new Game(new Player("player one"), new Player("player two"), new TopicChooser(), null);
            fail();
        }
        catch (IllegalArgumentException ex)
        {
            assertThat(ex.getMessage(), is("A question dispenser needs to be provided"));
        }
    }
}

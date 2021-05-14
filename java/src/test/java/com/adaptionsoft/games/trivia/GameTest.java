package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class GameTest
{
    @Test
    public void enforce_at_least_two_players()
    {
        try {
            new Game(null, null);
            fail();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage(), is("Players cannot be null"));
        }
    }
}

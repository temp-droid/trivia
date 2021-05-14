package com.adaptionsoft.games.uglytrivia.question;

import com.adaptionsoft.games.uglytrivia.question.model.Topic;

import java.util.stream.IntStream;

import static com.adaptionsoft.games.uglytrivia.question.model.Topic.*;

public class TopicChooser
{
    public static Topic topic(final int position)
    {
        if (currentCategoryIsPop(position))
        {
            return POP;
        }
        if (currentCategoryIsScience(position))
        {
            return SCIENCE;
        }
        if (currentCategoryIsSports(position))
        {
            return SPORTS;
        }
        return ROCK;
    }

    private static boolean currentCategoryIsPop(final int position)
    {
        return positionMatch(position, 0, 4, 8);
    }

    private static boolean currentCategoryIsScience(final int position)
    {
        return positionMatch(position, 1, 5, 9);
    }

    private static boolean currentCategoryIsSports(final int position)
    {
        return positionMatch(position, 2, 6, 10);
    }

    private static boolean positionMatch(final int position, final int num0, final int num1, final int num2)
    {
        return IntStream.of(num0, num1, num2).anyMatch(i -> position == i);
    }
}

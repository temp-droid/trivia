package com.adaptionsoft.games.uglytrivia;

import java.util.stream.IntStream;

public class CategoryChooser
{
    public static String currentCategory(final int position)
    {
        if (currentCategoryIsPop(position))
        {
            return "Pop";
        }
        if (currentCategoryIsScience(position))
        {
            return "Science";
        }
        if (currentCategoryIsSports(position))
        {
            return "Sports";
        }
        return "Rock";
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

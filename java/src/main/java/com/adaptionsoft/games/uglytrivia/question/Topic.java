package com.adaptionsoft.games.uglytrivia.question;

public enum Topic
{
    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");

    private final String value;

    Topic(final String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}

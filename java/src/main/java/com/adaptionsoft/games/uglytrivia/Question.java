package com.adaptionsoft.games.uglytrivia;

public class Question
{
    private final String topic;
    private final int number;

    public Question(final String topic, final int number)
    {
        this.topic = topic;
        this.number = number;
    }

    @Override
    public String toString()
    {
        return topic + " Question " + number;
    }
}

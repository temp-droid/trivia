package com.adaptionsoft.games.uglytrivia.question.model;

public class Question
{
    private final Topic topic;
    private final int number;

    public Question(final Topic topic, final int number)
    {
        this.topic = topic;
        this.number = number;
    }

    public Topic topic()
    {
        return topic;
    }

    @Override
    public String toString()
    {
        return topic + " Question " + number;
    }
}

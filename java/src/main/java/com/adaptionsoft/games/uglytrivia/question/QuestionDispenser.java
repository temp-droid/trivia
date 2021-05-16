package com.adaptionsoft.games.uglytrivia.question;

import com.adaptionsoft.games.uglytrivia.question.model.*;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class QuestionDispenser
{
    private static final int QUESTIONS_PER_TOPIC = 50;

    private final LinkedList<Question> questions;

    public QuestionDispenser()
    {
        questions = new LinkedList<>();

        initQuestions();
    }

    public Question dispense(final Topic topic)
    {
        Question question = questions.stream()
                .filter(hasTopic(topic))
                .findFirst()
                .orElseThrow(invalidTopic(topic));

        questions.remove(question);

        return question;
    }

    private void initQuestions()
    {
        for (int i = 0; i < QUESTIONS_PER_TOPIC; i++)
        {
            addQuestion(new PopQuestion(i));
            addQuestion(new ScienceQuestion(i));
            addQuestion(new SportsQuestion(i));
            addQuestion(new RockQuestion(i));
        }
    }

    private void addQuestion(final Question question)
    {
        questions.addLast(question);
    }

    private Predicate<Question> hasTopic(final Topic topic)
    {
        return q -> q.topic().equals(topic);
    }

    private Supplier<? extends RuntimeException> invalidTopic(final Topic topic)
    {
        return () -> new IllegalArgumentException("Invalid topic " + topic);
    }
}
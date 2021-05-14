package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionDispenser
{
    private final LinkedList<Question> popQuestions;
    private final LinkedList<Question> scienceQuestions;
    private final LinkedList<Question> sportsQuestions;
    private final LinkedList<Question> rockQuestions;

    public QuestionDispenser()
    {
        popQuestions = new LinkedList<>();
        scienceQuestions = new LinkedList<>();
        sportsQuestions = new LinkedList<>();
        rockQuestions = new LinkedList<>();

        initQuestions();
    }

    void askQuestion(final int position)
    {
        Question question = fetchQuestion(position);

        System.out.println(question);
    }

    private Question fetchQuestion(final int position)
    {
        switch (CategoryChooser.currentCategory(position))
        {
            case "Pop":
                return popQuestions.removeFirst();
            case "Science":
                return scienceQuestions.removeFirst();
            case "Sports":
                return sportsQuestions.removeFirst();
            case "Rock":
                return rockQuestions.removeFirst();
            default:
                throw new IllegalArgumentException("Unknown question topic");
        }
    }

    private void initQuestions()
    {
        for (int i = 0; i < 50; i++)
        {
            popQuestions.addLast(new Question("Pop", i));
            scienceQuestions.addLast(new Question("Science", i));
            sportsQuestions.addLast(new Question("Sports", i));
            rockQuestions.addLast(new Question("Rock", i));
        }
    }
}
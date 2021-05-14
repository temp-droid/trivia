package com.adaptionsoft.games.uglytrivia;

import java.util.LinkedList;

public class QuestionDispenser
{
    private final LinkedList<String> popQuestions;
    private final LinkedList<String> scienceQuestions;
    private final LinkedList<String> sportsQuestions;
    private final LinkedList<String> rockQuestions;

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
        switch (CategoryChooser.currentCategory(position))
        {
            case "Pop":
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }

    private void initQuestions()
    {
        for (int i = 0; i < 50; i++)
        {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
    }
}
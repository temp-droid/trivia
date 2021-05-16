package com.adaptionsoft.games.uglytrivia.question;

import com.adaptionsoft.games.uglytrivia.question.model.Topic;

public class TopicChooser
{
    public Topic topic(final int position)
    {
        Topic[] topics = Topic.values();
        final int numberOfTopics = topics.length;

        final int index = position % numberOfTopics;

        return topics[index];
    }
}

package br.com.alura.forum.model;

import br.com.alura.forum.model.topic_domain.Topic;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class PossibleSpammer {

    private List<Topic> topics;

    public PossibleSpammer(List<Topic> topics) {
        this.topics = topics;
    }

    public boolean hasTopicLimitExceeded() {
        return this.topics.size() >= 4;
    }

    public long minutesToNextTopic(Instant from) {
        Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();

        return Duration.between(from, instantOfTheOldestTopic)
                .getSeconds() / 60;
    }
}

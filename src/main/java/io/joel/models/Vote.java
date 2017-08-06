package io.joel.models;

import javax.persistence.*;

@Entity
@Table(name="vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private VoteStyle style;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Vote() { }

    public Vote(VoteStyle style, Topic topic) {

        this.style = style;
        this.topic = topic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VoteStyle getStyle() {
        return style;
    }

    public void setStyle(VoteStyle style) {
        this.style = style;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

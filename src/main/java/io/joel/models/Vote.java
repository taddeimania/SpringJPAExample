package io.joel.models;

import javax.persistence.*;

@Entity
@Table(name="vote")
public class Vote {

    private long id;
    private VoteStyle style;
    private Topic topic;

    public Vote() { }

    public Vote(VoteStyle style, Topic topic) {

        this.style = style;
        this.topic = topic;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "topic_id")
    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}

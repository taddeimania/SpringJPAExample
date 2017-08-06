package io.joel.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class Topic {

    private long id;
    private String title;
    private String description;

    private List<Vote> votes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Topic() {
    }

    public Topic(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Transient
    public int getScore() {
        int total = 0;
        for (Vote vote : votes) {
            if (vote.getStyle() == VoteStyle.UP) {
                total++;
            } else {
                total--;
            }
        }
        return total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

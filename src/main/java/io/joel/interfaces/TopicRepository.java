package io.joel.interfaces;

import io.joel.models.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long>{
    List<Topic> findAll();
}

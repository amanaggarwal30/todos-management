package net.guides.todo.todomanagement.repository;

import net.guides.todo.todomanagement.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserName(String userName);

}

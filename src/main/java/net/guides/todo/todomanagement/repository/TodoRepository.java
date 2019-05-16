package net.guides.todo.todomanagement.repository;

import net.guides.todo.todomanagement.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}

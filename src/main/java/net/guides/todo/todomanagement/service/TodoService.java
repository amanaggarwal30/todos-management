package net.guides.todo.todomanagement.service;

import net.guides.todo.todomanagement.model.Todo;
import net.guides.todo.todomanagement.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService{

    @Autowired
    private TodoRepository todoRepository;

    
    public List<Todo> getTodosByUser(String user) {
        return todoRepository.findByUserName(user);
    }

    
    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    
    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }

    
    public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
        todoRepository.save(new Todo(name, desc, targetDate));
    }

    
    public void deleteTodo(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if(todo.isPresent()){
            todoRepository.delete(todo.get());
        }
    }

    
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
}

package com.apsus.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoService {

  private static List<Todo> todos = new ArrayList<>();
  static {
    todos.add(new Todo(1, "admin", "Learn CPP", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(2, "admin", "Learn Devops", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(3, "admin2", "Learn Spring with zero java", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(4, "admin2", "Learn Spring Boot", LocalDate.now().plusYears(2), false));
    todos.add(new Todo(5, "admin", "Learn AWS Services", LocalDate.now().plusYears(3), false));
    todos.add(new Todo(6, "edmo", "Learn Spring Security", LocalDate.now().plusYears(3), false));
  }

  public List<Todo> findByUsername(String username) {
    return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(username))
            .collect(Collectors.toList());
  }
  public void addTodo(String username, String description, LocalDate targetDate, boolean done){
    int id = todos.size() + 1;
    Todo newTodo =  new Todo(id,username,description,targetDate, done);
    todos.add(newTodo);
  }

  public void removeTodo(int id) {
    Predicate<? super Todo> predicate = todo -> todo.getId() == id;
    todos.removeIf(predicate);
  }
  public Todo findById(int id){
    return todos.stream().filter(todo -> todo.getId() == id).findFirst().orElse(null);
  }

  public void updateTodo(@Valid Todo todo) {
    removeTodo(todo.getId());
    todos.add(todo);
  }
}

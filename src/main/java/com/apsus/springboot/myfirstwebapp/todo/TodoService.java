package com.apsus.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

  private static List<Todo> todos = new ArrayList<>();
  static {
    todos.add(new Todo(1, "admin", "Learn CPP", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(2, "admin", "Learn Devops", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(3, "admin2", "Learn Spring", LocalDate.now().plusYears(1), false));
    todos.add(new Todo(4, "admin2", "Learn Spring Boot", LocalDate.now().plusYears(2), false));
    todos.add(new Todo(5, "admin", "Learn AWS", LocalDate.now().plusYears(3), false));
  }

  public List<Todo> findByUsername(String username) {
    return todos.stream().filter(todo -> todo.getUsername().equalsIgnoreCase(username))
            .collect(Collectors.toList());
  }
}

package com.apsus.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

  private TodoService todoService;
  private TodoRepository todoRepository;
  public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
    super();
    this.todoService = todoService;
    this.todoRepository = todoRepository;
  }

  @RequestMapping(value="/todos", method = RequestMethod.GET)
  public String listAllTodos(ModelMap model) {
    String name = getLoggedInUsername();
    List<Todo> todos = todoRepository.findByUsername(name);
    model.addAttribute("todos", todos);
    return "listTodos";
  }

  @RequestMapping(value="/add-todo", method = RequestMethod.GET)
  public String showAddTodoPage(ModelMap model) {
    String name = getLoggedInUsername();
    Todo todo = new Todo(0, name, "", LocalDate.now().plusYears(1), false );
    model.put("todo", todo);
    return "todo";
  }
  @RequestMapping(value="/add-todo", method = RequestMethod.POST)
  public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult){
    String username = getLoggedInUsername();
    if(bindingResult.hasErrors()) {
      return "todo";
    }
    todo.setUsername(username);
    todoRepository.save(todo);
    return "redirect:/todos";
  }

  private static String getUsername(ModelMap model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

  @RequestMapping(value = "/delete-todo")
  public String deleteTodo(@RequestParam int id) {
    todoRepository.deleteById(id);
    return "redirect:/todos";
  }

  @RequestMapping(value="/update-todo", method = RequestMethod.POST)
  public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
    String username = getLoggedInUsername();
    if(bindingResult.hasErrors()) {
      return "todo";
    }
    todo.setUsername(username);
    Optional<Todo> foundTodo = todoRepository.findById(todo.getId());
    if(foundTodo.isEmpty()){
      return "redirect:/todos";
    }
    Todo todoToUpdate = foundTodo.get();
    todoToUpdate.setUsername(username);
    todoToUpdate.setDescription(todo.getDescription());
    todoToUpdate.setTargetDate(todo.getTargetDate());
    todoRepository.save(todoToUpdate);
    return "redirect:/todos";
  }

  @RequestMapping(value= "/update-todo", method = RequestMethod.GET)
  public String showUpdateTodo(@RequestParam int id, ModelMap model) {
    Optional<Todo> todo = todoRepository.findById(id);
    model.put("todo", todo);
    return "todo";
  }
  private String getLoggedInUsername(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }
}

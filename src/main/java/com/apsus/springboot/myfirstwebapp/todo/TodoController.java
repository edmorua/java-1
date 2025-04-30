package com.apsus.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

  private TodoService todoService;
  public TodoController(TodoService todoService) {
    super();
    this.todoService = todoService;
  }

  @RequestMapping(value="/todos", method = RequestMethod.GET)
  public String listAllTodos(ModelMap model) {
    String name = getLoggedInUsername();
    List<Todo> todos = this.todoService.findByUsername(name);
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
    todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
    return "redirect:/todos";
  }

  private static String getUsername(ModelMap model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }

  @RequestMapping(value = "/delete-todo")
  public String deleteTodo(@RequestParam int id) {
    todoService.removeTodo(id);
    return "redirect:/todos";
  }

  @RequestMapping(value="/update-todo", method = RequestMethod.POST)
  public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult bindingResult) {
    String username = getLoggedInUsername();
    if(bindingResult.hasErrors()) {
      return "todo";
    }
    todo.setUsername(username);
    todoService.updateTodo(todo);
    return "redirect:/todos";
  }

  @RequestMapping(value= "/update-todo", method = RequestMethod.GET)
  public String showUpdateTodo(@RequestParam int id, ModelMap model) {
    Todo todo = todoService.findById(id);
    model.put("todo", todo);
    return "todo";
  }
  private String getLoggedInUsername(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return auth.getName();
  }
}

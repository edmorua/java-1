package com.apsus.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    String name = (String) model.get("name");
    List<Todo> todos = this.todoService.findByUsername(name);
    model.addAttribute("todos", todos);
    return "listTodos";
  }

  @RequestMapping(value="/add-todo", method = RequestMethod.GET)
  public String showAddTodoPage() {
    return "todo";
  }
  @RequestMapping(value="/add-todo", method = RequestMethod.POST)
  public String addNewTodo(@RequestParam String description, ModelMap model){
    String username = (String) model.get("name");
    todoService.addTodo(username, description, LocalDate.now().plusYears(1), false);
    return "redirect:/todos";
  }

}

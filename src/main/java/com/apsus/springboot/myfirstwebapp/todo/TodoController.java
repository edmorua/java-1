package com.apsus.springboot.myfirstwebapp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodoController {

  private TodoService todoService;
  public TodoController(TodoService todoService) {
    super();
    this.todoService = todoService;
  }
  @RequestMapping(value="/todos", method = RequestMethod.GET)
  public String listAllTodos(ModelMap model) {
    List<Todo> todos = this.todoService.findByUsername("admin");
    model.addAttribute("todos", todos);
    return "listTodos";
  }

}

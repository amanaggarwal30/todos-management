package net.guides.todo.todomanagement.controller;

import net.guides.todo.todomanagement.model.Todo;
import net.guides.todo.todomanagement.service.ITodoService;
import net.guides.todo.todomanagement.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list-todos")
    public String showTodos(ModelMap map){
        String userName = getloggedInUser(map);
        map.put("todo", todoService.getTodosByUser(userName));
        return "list-todos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add-todo")
    public String showAddTodoPage(ModelMap map){
        map.addAttribute("todo", new Todo());
        return "todo";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-todo")
    public String deleteTodo(@RequestParam long id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update-todo")
    public String showUpdateTodopage(@RequestParam long id, ModelMap map){
        Todo todo = todoService.getTodoById(id).get();
        map.put("todo", todo);
        return "todo";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update-todo")
    public String updateTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "not updated";
        }
        todo.setUserName(getloggedInUser(map));
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add-todo")
    public String addTodo(ModelMap map, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "error";
        }

        todo.setUserName(getloggedInUser(map));
        todoService.saveTodo(todo);
        return "redirect:/list-todos";
    }

    public String getloggedInUser(ModelMap map){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = "";
        if(principal  instanceof UserDetails){
            userName = ((UserDetails)principal).getUsername();
        }
        if(!userName.equals("")){
            userName = principal.toString();
        }
        return userName;
    }
}
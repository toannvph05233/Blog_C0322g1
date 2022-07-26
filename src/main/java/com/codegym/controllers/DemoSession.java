package com.codegym.controllers;

import com.codegym.models.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoSession {
    @Autowired
    HttpSession httpSession;

    @GetMapping("/list")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<TodoList> todoLists = (List<TodoList>) httpSession.getAttribute("todoLists");
        modelAndView.addObject("listTodo", todoLists);
        return modelAndView;
    }

    @PostMapping("/addToDo")
    public ModelAndView addToDo(TodoList todoList) {
        List<TodoList> todoLists = (List<TodoList>) httpSession.getAttribute("todoLists");
        if (todoLists == null){
            todoLists = new ArrayList<>();
        }
        todoLists.add(todoList);
        httpSession.setAttribute("todoLists", todoLists);
        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        return modelAndView;
    }
}

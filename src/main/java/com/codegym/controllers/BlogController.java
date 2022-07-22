package com.codegym.controllers;

import com.codegym.models.Blogs;
import com.codegym.models.Category;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import com.codegym.validate.ValidateBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ValidateBlog validateBlog;

    @ModelAttribute(name = "blog")
    public Blogs blogs(){
        return new Blogs();
    }

    @ModelAttribute(name = "categories")
    public List<Category> categories(){
        return categoryService.getAll();
    }

    @GetMapping("/blogs")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("blogs", blogService.getAll(PageRequest.of(page, 2, Sort.by("date"))));
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(defaultValue = "0") int page, @RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("blogs", blogService.getAllByTitle(PageRequest.of(page, 2, Sort.by("date")), search));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("blog") Blogs blogs,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile upImg) {
        validateBlog.validate(blogs,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }

        String nameImg = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("/Users/johntoan98gmail.com/Desktop/Blog/src/main/webapp/WEB-INF/img/" + nameImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blogs.setImg("/img/" + nameImg);
        blogService.save(blogs);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        return modelAndView;
    }

}

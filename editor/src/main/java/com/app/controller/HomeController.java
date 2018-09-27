package com.app.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.editor.IsbnEditor;
import com.app.vo.Isbn;

@Controller
public class HomeController {
     
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
 
    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    public String getBook(@PathVariable Isbn isbn, Map<String, Object> model)
    {
        LOGGER.info("You searched for book with ISBN :: " + isbn.getIsbn());
        model.put("isbn", isbn);
        return "index";
    }
     
    @InitBinder
    public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(Isbn.class, new IsbnEditor());
    }
}

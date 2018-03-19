package com.example.bookspringbootdemo.controller;

import com.example.bookspringbootdemo.domain.Book;
import com.example.bookspringbootdemo.domain.Result;
import com.example.bookspringbootdemo.enums.ResultEnum;
import com.example.bookspringbootdemo.repository.BookRepository;
import com.example.bookspringbootdemo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    //通过名字查询书列表
    @GetMapping(value = "/books/name/{name}")
    public Result<Book> bookListByName(@PathVariable("name") String name){
        return ResultUtil.success(ResultEnum.SUCCESS,bookRepository.findByName(name));
    }

    //查询一本书
    @GetMapping(value = "/books/id/{id}")
    public  Result<Book> bookFindOne(@PathVariable("id") long id) {
        return  ResultUtil.success(ResultEnum.SUCCESS,bookRepository.findOne(id));
    }

    /**
     * 查询所有书列表
     * @return
     */
    @GetMapping(value = "/books")
    public Result<Book> bookList(){
        return ResultUtil.success(ResultEnum.SUCCESS,bookRepository.findAll());
    }


    /**
     * 添加一本书
     * @return
     */
    @PostMapping(value = "/books")
    public Result<Book> bookAdd(Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(201, bindingResult.getFieldError().getDefaultMessage());
        }

        book.setId(book.getId());
        book.setName(book.getName());
        book.setNumber(book.getNumber());

        return ResultUtil.success(ResultEnum.SUCCESS,bookRepository.save(book));
    }

    //更新
    @PutMapping(value = "/books/{id}")
    public Result<Book> bookUpdate(@PathVariable("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("number") Integer number) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setNumber(number);

        return  ResultUtil.success(ResultEnum.SUCCESS,bookRepository.save(book));
    }

    //删除
    @DeleteMapping(value = "/books/{id}")
    public Result<Book> bookDelete(@PathVariable("id") long id) {
        bookRepository.delete(id);
        return ResultUtil.success(ResultEnum.SUCCESS,null);
    }



}

package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

@Controller
public class BookController {
	@Autowired
	private BookService service;
	
	@Autowired
	private MyBookListService myBookService;
	
	@GetMapping("/")
	public String home() {
		return "home"; //this will go home.html
	}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";  //this will go to bookRegister.html
	}
	
	@GetMapping("/available_book")
	public ModelAndView getAllBooks() {
		//send data from controller to view
		List<Book> list =service.getAllBooks();
		/*
		 * ModelAndView m= new ModelAndView(); m.setViewName("bookList");
		 * m.addObject("book", list); return m;
		 */  //this will go to bookList.html
		return new ModelAndView("bookList","book",list);//viewpagename,name,list
	}
	
	@GetMapping("/my_books")
	public String myBooks(Model model) {
		List <MyBookList> list =myBookService.getAllMyBook();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		service.save(b);
		return "redirect:/available_book";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=service.getBookById(id);
		MyBookList mb= new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.save(mb);
		return "redirect:/my_books";
	}
	
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		myBookService.deleteById(id);
		return "redirect:/my_books";
		
	}
	
	@RequestMapping("/deleteAvailableBooksById/{id}")
	public String deleteBooksByID(@PathVariable("id") int id) {
		service.deleteAvailableBookById(id);
		return "redirect:/available_book";
		
	}

}

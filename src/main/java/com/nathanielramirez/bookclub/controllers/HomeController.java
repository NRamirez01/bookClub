package com.nathanielramirez.bookclub.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nathanielramirez.bookclub.models.Book;
import com.nathanielramirez.bookclub.models.LoginUser;
import com.nathanielramirez.bookclub.models.User;
import com.nathanielramirez.bookclub.services.BookService;
import com.nathanielramirez.bookclub.services.UserService;

@Controller
public class HomeController {
    
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home(
			@ModelAttribute("user") User user,
			@ModelAttribute("userLogin") LoginUser userLogin
			) {
		return "loginRegistration.jsp";
	}
	
	@GetMapping("/addBook")
	public String bookAdd(@ModelAttribute("book") Book book) {
		return "bookAdd.jsp";
	}
	
	@PostMapping("/createBook")
	public String bookCreate(@Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session){
		if (result.hasErrors()) {
			return "bookAdd.jsp";
		} else {
			bookService.createBook(book);
			return "redirect:/bookClub";
		}
	}
	
	@GetMapping("/bookClub")
	public String splashPage(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("userId", userService.userByID(userId));
		model.addAttribute("books", bookService.allBooks());
		return "splash.jsp";
	}
	
	@PostMapping("/loginUser")
	public String login(@Valid @ModelAttribute("userLogin") LoginUser userLogin, BindingResult result, HttpSession session, Model model) {
		Optional<User> validate = userService.userByEmail(userLogin.getEmail());
		if(validate.isPresent() && BCrypt.checkpw(userLogin.getPassword(), validate.get().getPassword())) {
			session.setAttribute("userId", validate.get().getId());
			return "redirect:/bookClub";
		}
		result.rejectValue("email","Email", "Invalid email/password");
		model.addAttribute("user", new User());
		return "loginRegistration.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userId");
		return "redirect:/";
	}
	
	@PostMapping("/registerUser")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
		Optional<User> validate = userService.userByEmail(user.getEmail());
		if(validate.isPresent()){
			result.rejectValue("email", "Email", "Please enter a valid email");
		}
		if(!user.getPassword().equals(user.getConfirm()))
			result.rejectValue("password", "Matches", "The passwords must match");
        if(result.hasErrors()){
        	model.addAttribute("userLogin", new LoginUser());
			return "loginRegistration.jsp";
		}   
		    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	        user.setPassword(hashedPassword);
	        this.userService.create(user);
	        session.setAttribute("userId", user.getId());
			return "redirect:/bookClub";
		}
	
	
	@Transactional
    @RequestMapping(value="/delete/{book}", method= {RequestMethod.POST, RequestMethod.DELETE})
    public String destroy(@PathVariable("book") Long book) {
    	bookService.deleteId(book);
    	return "redirect:/bookClub";
	}
	
}

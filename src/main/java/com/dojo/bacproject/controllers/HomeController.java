package com.dojo.bacproject.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dojo.bacproject.models.Drink;
import com.dojo.bacproject.models.FavDrink;
import com.dojo.bacproject.models.LoginUser;
import com.dojo.bacproject.models.User;
import com.dojo.bacproject.services.DrinkService;
import com.dojo.bacproject.services.FavDrinkService;
import com.dojo.bacproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	


		@Autowired
		private UserService userService;
		
		@Autowired
		private DrinkService drinkService;
		
		@Autowired
		private FavDrinkService favDrinkService;
		
		@GetMapping("/")
		public String index(Model model) {
		    model.addAttribute("newUser", new User());
		    model.addAttribute("newLogin", new LoginUser());
		    return "index.jsp";
		}
		
		@PostMapping("/register")
		public String register(@Valid @ModelAttribute(name = "newUser") User newUser, 
				BindingResult result, Model model, HttpSession session) {

		    User user = userService.register(newUser, result);
		     
		    if(result.hasErrors()) {
		        model.addAttribute("newLogin", new LoginUser());
		        return "index.jsp";
		    }
		    session.setAttribute("userId", user.getId());
		 
		    return "redirect:/home";
		}
		
		@PostMapping("/login")
		public String login(@Valid @ModelAttribute(name= "newLogin") LoginUser newLogin, 
				BindingResult result, Model model, HttpSession session) {
		     
			User user = userService.login(newLogin, result);
		 
		    if(result.hasErrors()) {
		        model.addAttribute("newUser", new User());
		        return "index.jsp";
		    }
		     
		    session.setAttribute("userId", user.getId());
		 
		    return "redirect:/home";
		}
		
		@GetMapping("/home")
		public String home(HttpSession session, Model model) {
		 
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findById(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("assignedDrinks", drinkService.getAssignedDrinks(user));
			 
			return "home.jsp";
		}
		
		@GetMapping("/drinks/{id}")
		public String viewDrink(@PathVariable("id") Long id, HttpSession session, Model model) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			Drink drink = drinkService.findById(id);
			model.addAttribute("drink", drink);
			return "view.jsp";
		}
		
		@GetMapping("/drink/edit/{id}")
		public String editDrink(@PathVariable("id") Long id, HttpSession session, Model model) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			Drink drink= drinkService.findById(id);
			model.addAttribute("drink", drink);
			return "edit.jsp";
		}
		
		@PostMapping("/drink/edit/{id}")
		public String editDrink(
				@PathVariable("id") Long id, 
				@Valid @ModelAttribute("drink") Drink drink, 
				BindingResult result, 
				HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			
			User user = userService.findById(userId);
			
			if(result.hasErrors()) {
				return "edit.jsp";
			}
			
			else {
				Drink thisDrink= drinkService.findById(id);
				drink.setUsers(thisDrink.getUsers());
				drink.setDrinker(user);
				drinkService.updateDrink(drink);
				return "redirect:/home";
			}
		}
		
		@RequestMapping("/drink/delete/{id}")
		public String deleteDrink(@PathVariable("id") Long id, HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			Drink drink= drinkService.findById(id);
			drinkService.deleteDrink(drink);
			
			return "redirect:/home";
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.setAttribute("userId", null); 
		    return "redirect:/";
		}
		
		@GetMapping("/drinks/new")
		public String newDrink(@ModelAttribute("drink") Drink drink, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			
			model.addAttribute("userId", userId);
			return "new.jsp";
		}
		
		@PostMapping("/drinks/new")
		public String addNewDrink(@Valid @ModelAttribute(name= "drink") Drink drink, BindingResult result, HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			if(result.hasErrors()) {
				return "new.jsp";
			}
			
			else {
				drinkService.addDrink(drink);
				
				Long userId = (Long) session.getAttribute("userId");
				User user = userService.findById(userId);
				user.getDrinks().add(drink);
				userService.updateUser(user);
				return "redirect:/home";
			}
		}
		
		@GetMapping("/favdrinks/new")
		public String newFavDrink(@ModelAttribute("drink") FavDrink drink, HttpSession session, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			
			model.addAttribute("userId", userId);
			return "newfav.jsp";
		}
		
		@PostMapping("/favdrinks/new")
		public String addNewFavDrink(@Valid @ModelAttribute(name= "drink") FavDrink drink, BindingResult result, HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			if(result.hasErrors()) {
				return "newfav.jsp";
			}
			
			else {
				favDrinkService.addDrink(drink);
				
				Long userId = (Long) session.getAttribute("userId");
				User user = userService.findById(userId);
				user.getFavdrinks().add(drink);
				userService.updateUser(user);
				return "redirect:/populardrinks";
			}
		}
		
		@GetMapping("/favdrink/edit/{id}")
		public String editFavDrink(@PathVariable("id") Long id, HttpSession session, Model model) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			FavDrink drink= favDrinkService.findById(id);
			model.addAttribute("drink", drink);
			return "editfav.jsp";
		}
		
		@PostMapping("/favdrink/edit/{id}")
		public String editFavDrink(
				@PathVariable("id") Long id, 
				@Valid @ModelAttribute("drink") FavDrink drink, 
				BindingResult result, 
				HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			
			User user = userService.findById(userId);
			
			if(result.hasErrors()) {
				return "editfav.jsp";
			}
			
			else {
				FavDrink thisDrink= favDrinkService.findById(id);
				drink.setUsers(thisDrink.getUsers());
				drink.setDrinker(user);
				favDrinkService.updateDrink(drink);
				return "redirect:/populardrinks";
			}
		}
		
		@GetMapping("/populardrinks")
		public String community(HttpSession session, Model model) {
		 
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			Long userId = (Long) session.getAttribute("userId");
			User user = userService.findById(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("allDrinks", favDrinkService.allDrinks());
			 
			return "popular.jsp";
		}
		
		@RequestMapping("/favdrink/delete/{id}")
		public String deleteFavDrink(@PathVariable("id") Long id, HttpSession session) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			FavDrink drink= favDrinkService.findById(id);
			favDrinkService.deleteDrink(drink);
			
			return "redirect:/populardrinks";
		}
		
		@GetMapping("/favdrinks/{id}")
		public String viewFavDrink(@PathVariable("id") Long id, HttpSession session, Model model) {
			
			if(session.getAttribute("userId") == null) {
				return "redirect:/logout";
			}
			
			FavDrink drink = favDrinkService.findById(id);
			model.addAttribute("drink", drink);
			return "viewfav.jsp";
		}
		
	}


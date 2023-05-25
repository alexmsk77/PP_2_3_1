package web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import web.service.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String allUsers(Model model) {
		model.addAttribute("allUsers", userService.allUsers());
		return "listUsers";
	}

	@GetMapping("/{id}/edit")
	public String editPage(Model model, @PathVariable(value = "id") int id) {
		model.addAttribute("user", userService.readById(id));
		return "edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user) {
		userService.edit(user);
		return "redirect:/";
	}

	@GetMapping("/add")
	public String addPage(@ModelAttribute("user") User user) {
		return "add";
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.delete(id);
		return "redirect:/";
	}
}

package fr.diginamic.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.user.model.User;
import fr.diginamic.user.model.UserDTO;
import fr.diginamic.user.service.MapperUserService;
import fr.diginamic.user.service.UserService;

@CrossOrigin
@RestController()
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userServ;

	@Autowired
	private MapperUserService mpu;

	@GetMapping
	public List<User> findAll() {
		return this.userServ.findAll();
	}

	@PostMapping
	@Transactional
	public UserDTO verification(@RequestBody UserDTO userDTO) {
		return userServ.save(userDTO);

	}

}

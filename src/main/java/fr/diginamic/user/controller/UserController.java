package fr.diginamic.user.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.user.model.UserDTO;
import fr.diginamic.user.service.UserService;

@CrossOrigin
@RestController()
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userServ;

	@GetMapping
	public List<UserDTO> findAll() {
		return this.userServ.findAll();
	}

	@PostMapping
	@Transactional
	public UserDTO verification(@RequestBody UserDTO userDTO) {
		return userServ.save(userDTO);

	}

	@PatchMapping
	public UserDTO update(@RequestBody UserDTO user) {
		return this.userServ.save(user);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		this.userServ.deleteById(id);
	}
	@GetMapping("/withMission")
	public List<UserDTO> findAllUserWithMission() {
		return this.userServ.findAllUserWithMission();
	}
	
}

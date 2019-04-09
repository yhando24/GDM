package fr.diginamic.user.service;

import java.util.List;

import javax.jws.WebService;

import fr.diginamic.user.model.User;

@WebService(name = "UserMissionService", targetNamespace = "http://ce.que.tu.veux")
public interface UserMissionService {
	
	void addUser(User u);
    
    List<User> getUser();
    
    User getUserById(Long id);

}

package fr.diginamic.GDM;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Component
public class StartupDataInit {

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    UserRepository userRepository;
    
    @EventListener(ContextRefreshedEvent.class)
    public void init() {

    	userRepository.save(new User(1L, "Plop1", "Plopeur1", "ADMIN", "ADMIN@ADMIN.fr", RoleEnum.ADMIN));
    	userRepository.save(new User(2L, "Plop2", "Plopeur2", "MANAGER", "MANAGER@MANAGER.fr", RoleEnum.MANAGER));
    	userRepository.save(new User(3L, "Plop3", "Plopeur3", "USER", "USER@USER.fr", RoleEnum.USER));

    }
}

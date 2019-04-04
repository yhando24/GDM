package fr.diginamic.GDM;





import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import fr.diginamic.expenseaccount.model.ExpenseAccount;
import fr.diginamic.expenseaccount.model.ExpenseAccountStatusEnum;
import fr.diginamic.expenseaccount.repository.ExpenseAccountRepository;
import fr.diginamic.kind.model.Kind;
import fr.diginamic.kind.service.KindService;
import fr.diginamic.kindversion.model.KindVersion;
import fr.diginamic.kindversion.model.MapperKindVersionService;
import fr.diginamic.kindversion.repository.KindVersionRepository;
import fr.diginamic.mission.model.Mission;
import fr.diginamic.mission.model.MissionStatusEnum;
import fr.diginamic.mission.model.TransportEnum;
import fr.diginamic.mission.repository.MissionRepository;
import fr.diginamic.user.model.RoleEnum;
import fr.diginamic.user.model.User;
import fr.diginamic.user.repository.UserRepository;

@Component
public class StartupDataInit {

    @Autowired
    MissionRepository missionRepository;
    
    @Autowired
    KindVersionRepository kindVersionRepository;
    
    @Autowired
    fr.diginamic.kind.repository.KindRepository kindRepository;
    
    @Autowired
    ExpenseAccountRepository expenseAccountRepository;
    
    
    @Autowired
    KindService ks;
    
    @Autowired
    MapperKindVersionService mapperKindVersionService;
    
    @Autowired
    UserRepository userRepository;
       
    @EventListener(ContextRefreshedEvent.class)
    @org.springframework.transaction.annotation.Transactional
    public void init() {
    	
    	
    	// Ajout user
    	User u = new User(1L, "Plop1", "Plopeur1", "ADMIN", "ADMIN@ADMIN.fr", RoleEnum.ADMIN);
    	userRepository.save(u);
    	userRepository.save(new User(2L, "Plop2", "Plopeur2", "MANAGER", "MANAGER@MANAGER.fr", RoleEnum.MANAGER));
    	userRepository.save(new User(3L, "Plop3", "Plopeur3", "USER", "USER@USER.fr", RoleEnum.USER));
   
    	
    	Kind k = new Kind("Formation", 12.05f, 10f, LocalDateTime.now(), true, true, 10.1f, true);
    	
    
  
    	KindVersion kv = new KindVersion("Formation", 12.05f, 10f, true, true, 10.1f, true, k, 1L, LocalDateTime.now());
     	
    	KindVersion kv2 = new KindVersion("Formation", 15.05f, 10f, true, true, 10.1f, true, k, 2L, LocalDateTime.now());
    	
    	
    	
    	Mission m = new Mission(LocalDate.now(), LocalDate.now().plusDays(5), "paris", "madrid", 12f, MissionStatusEnum.VALIDE, TransportEnum.BUS, kv, u, 150.01f);
    	ExpenseAccount ea = new ExpenseAccount(1L, LocalDate.now(),  1250F, ExpenseAccountStatusEnum.EN_ATTENTE,m);
    	m.addexpenseAccounts(ea);
    	
    	kv.addMission(m);
    	kv2.addMission(m);

    	
    	
    	
  
    	kindRepository.save(k);
       	kindVersionRepository.save(kv);
     	kindVersionRepository.save(kv2);
    	missionRepository.save(m);
    	expenseAccountRepository.save(ea);
 
    
    	System.out.println(kindVersionRepository.findTopByNameOrderByVersionDesc(kv2.getName()).getVersion());

    }
}

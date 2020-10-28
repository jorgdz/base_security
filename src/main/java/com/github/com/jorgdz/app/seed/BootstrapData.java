package com.github.com.jorgdz.app.seed;
/*
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.com.jorgdz.app.entity.Privilege;
import com.github.com.jorgdz.app.entity.Role;
import com.github.com.jorgdz.app.entity.User;
import com.github.com.jorgdz.app.repository.PrivilegeRepository;
import com.github.com.jorgdz.app.repository.RoleRepository;
import com.github.com.jorgdz.app.repository.UserRepository;

@Component
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent>{
	
	private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;
		
		Privilege showUsersPrivilege = createPrivilegeIfNotFound("SHOW_USERS");
	    Privilege createUserPrivilege = createPrivilegeIfNotFound("CREATE_USER");
  
        List<Privilege> adminPrivileges = Arrays.asList(showUsersPrivilege, createUserPrivilege);        
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(showUsersPrivilege));
 
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);
 
        alreadySetup = true;
	}
	
	
	@Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
  
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
        
    }
 
    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
        
    }
}*/
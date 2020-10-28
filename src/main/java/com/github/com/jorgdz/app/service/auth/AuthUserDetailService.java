package com.github.com.jorgdz.app.service.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.entity.Privilege;
import com.github.com.jorgdz.app.entity.Role;
import com.github.com.jorgdz.app.entity.User;
import com.github.com.jorgdz.app.repository.RoleRepository;
import com.github.com.jorgdz.app.repository.UserRepository;

@Service("userDetailService")
@Transactional
public class AuthUserDetailService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(AuthUserDetailService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);	
		
		if (user == null) {
			 return new org.springframework.security.core.userdetails.User(" ", " ",
					 true, true, true, true, 
					 getAuthorities(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), 
				user.getPassword(), 
				user.isEnabled(), 
				true, true, true, 
				getAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<Privilege> getPrivileges(Collection<Role> roles) {
        List<Privilege> privileges = new ArrayList<>();
        
        for (Role role : roles) {   	
        	privileges.addAll(role.getPrivileges());
        }
        
        return privileges;
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(List<Privilege> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (Privilege privilege : privileges) {
        	logger.info(privilege.getName());
        	authorities.add(new SimpleGrantedAuthority(privilege.getName()));
		}
        
        return authorities;
    }
}

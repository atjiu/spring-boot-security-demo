package com.example.demo.module.security.core;

import com.example.demo.module.security.model.Permission;
import com.example.demo.module.security.service.PermissionService;
import com.example.demo.module.user.model.User;
import com.example.demo.module.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

	private Logger log = Logger.getLogger(MyUserDetailService.class);

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	public UserDetails loadUserByUsername(String username) {
		User user = userService.findByUsername(username);
		if (user != null) {
			List<Permission> permissions = permissionService.findByAdminUserId(user.getId());
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			for (Permission permission : permissions) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
				grantedAuthorities.add(grantedAuthority);
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					true, true, true, !user.getBlock(), grantedAuthorities);
		} else {
			log.info("User: " + username + " not exist");
			throw new UsernameNotFoundException("Username or password incorrect");
		}
	}

}

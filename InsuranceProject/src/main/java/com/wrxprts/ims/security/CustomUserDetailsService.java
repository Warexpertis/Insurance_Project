package com.wrxprts.ims.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wrxprts.ims.entity.Role;
import com.wrxprts.ims.entity.User;
import com.wrxprts.ims.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String tc) throws UsernameNotFoundException
	{
		User user = userRepository.findByTc(tc);
		if (user != null)
		{
			return new org.springframework.security.core.userdetails.User(user.getTc(), user.getPassword(),
					mapRolesToAuthorities(user.getRoles()));
		}
		
		else
		{
			throw new UsernameNotFoundException("Invalid TC or password");
		}
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles)
	{
		Collection<? extends GrantedAuthority> mapRoles = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getEmail())).collect(Collectors.toList());
		return mapRoles;
	}
	
}

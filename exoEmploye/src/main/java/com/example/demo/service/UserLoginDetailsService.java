package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserLoginDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = userRepo.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            return new UserLogin(user);

    }

}

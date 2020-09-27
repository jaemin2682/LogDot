package com.papple.blog.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.papple.blog.models.User;
import com.papple.blog.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final User user = userRepository.findById(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

		return UserDetailsImpl.build(user);
	}
}

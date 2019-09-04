package com.dnc.dispatcher.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dnc.dispatcher.rest.common.exception.CUserNotFoundException;
import com.dnc.dispatcher.rest.member.entity.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return memberRepository.findByEmail(username).orElseThrow(CUserNotFoundException::new);
	}

}

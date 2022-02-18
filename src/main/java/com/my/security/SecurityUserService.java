package com.my.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.my.main.dao.MainDao;

public class SecurityUserService implements UserDetailsService {

	@Resource
	private MainDao mainDao;
	
	@Override
	public SecurityUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String, Object> newmap = new HashMap<String, Object>();
		
		newmap.put("loginId", username);
		
		Map<String, Object> newuser = mainDao.selectUser(newmap); //요기는 맵을 넣어줘야 되는데 username넣어서 에러 난거구
		if(username == null) throw new UsernameNotFoundException(username);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(newmap.get("authorxity").toString())); //여기는 newMap.get해야 되는댕 "authority").toString()그냥 이것만 적어서 에러난거궁
		SecurityUserDetail user = new SecurityUserDetail(username, "1", authorities, "nickname");
		System.out.println("zzzzzz");
//		return new SecurityUserDetail(newuser.get("username").toString(), newuser.get("password").toString(), authorities, newuser.get("nickname").toString()); //요기 마지막 닉네임에 .toString()안적어서 앞에 유저 디테일 스트링 형식이랑 달라서 에러 난거궁
		return user;
		
		
		
	}
	
	

}

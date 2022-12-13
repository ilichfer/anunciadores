package com.anunciadores.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anunciadores.dto.PersonaDto;
import com.anunciadores.model.Persona;
import com.anunciadores.model.Rol;
import com.anunciadores.repository.PersonaRepoImpl;
import com.anunciadores.repository.RolesRepoImpl;

@Service("userDetailService")
public class UsuarioService /*implements UserDetailsService*/ {
	
	@Autowired
	private PersonaRepoImpl personaRepository;
	
	@Autowired
	private RolesRepoImpl rolesDao;

//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		PersonaDto personadto = new PersonaDto();
//		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//		
//		Persona persona =  personaRepository.buscarEmail(email);
//		if (persona == null) {
//			throw new UsernameNotFoundException(email);
//			
//		}
//		personadto.setRoles(rolesDao.buscarRolesPersona(persona.getId()));
//		
//		for (Rol rol : personadto.getRoles()) {
//			roles.add(new SimpleGrantedAuthority(rol.getDescripcion()));
//		}
//		
////		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////		persona.setPassword( encoder.encode(persona.getPassword()));
//		
//		return new User(persona.getNombre(), persona.getPassword(), roles);
//	}

}

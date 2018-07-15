package com.usuario.api.serviceImpl;

import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.usuario.api.dao.UsuarioRepository;
import com.usuario.api.model.Usuario;
import com.usuario.api.service.UsuarioService;


@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getEmail(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public Usuario save(Usuario usuario) {
	    Usuario newUser = new Usuario();
	    newUser.setUserName(usuario.getUserName());
	    newUser.setEmail(bcryptEncoder.encode(usuario.getEmail()));
		newUser.setPrimerNombre(usuario.getPrimerNombre());
		newUser.setSegundoNombre(usuario.getSegundoNombre());
		return usuarioRepository.save(newUser);
	}

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario listarPorCodigo(Integer codigo) {
		return usuarioRepository.findByCodigo(codigo);
	}

	@Override
	public void eliminar(Integer codigo) {
		usuarioRepository.deleteById(codigo);
		
	}

	@Override
	public Usuario findOne(String login) {
		return usuarioRepository.findByUserName(login);
	}

}

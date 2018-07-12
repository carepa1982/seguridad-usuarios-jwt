package com.usuario.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usuario.api.dao.UsuarioRepository;
import com.usuario.api.model.Usuario;
import com.usuario.api.service.UsuarioService;


@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
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

}

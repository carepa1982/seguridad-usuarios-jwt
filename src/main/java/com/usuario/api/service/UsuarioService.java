package com.usuario.api.service;

import java.util.List;

import com.usuario.api.model.Usuario;

public interface UsuarioService {

	Usuario save(Usuario usuario);

	List<Usuario> listar();

	Usuario listarPorCodigo(Integer codigo);

	void eliminar(Integer codigo);

}

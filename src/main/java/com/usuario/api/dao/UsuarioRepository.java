package com.usuario.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usuario.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Usuario findByCodigo(Integer codigo);

	Usuario findByEmail(String email);

	Usuario findByUserName(String username);

}

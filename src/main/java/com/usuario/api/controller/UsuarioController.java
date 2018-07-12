package com.usuario.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usuario.api.model.Usuario;
import com.usuario.api.service.UsuarioService;
import com.usuario.api.util.CustomErrorType;
import com.usuario.api.util.CustomMessageType;
import com.usuario.api.util.CustomResponseType;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping("/usuario")
	public ResponseEntity<CustomResponseType> guardarUsuario(@RequestBody String userJson)
			throws JsonParseException, JsonMappingException, IOException {
		Usuario usuario = objectMapper.readValue(userJson, Usuario.class);

		if (!isValidate(usuario)) {
			return new ResponseEntity<>(new CustomMessageType("Error"), HttpStatus.NOT_ACCEPTABLE);
		}

		usuarioService.save(usuario);

		return new ResponseEntity<>(new CustomMessageType("Exito"), HttpStatus.OK);
	}

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> lista = usuarioService.listar();
		return validarListaEntidad(lista);
	}

	@GetMapping("/usuario/{codigo}")
	public ResponseEntity<?> listarUsuarioPorCodigo(@PathVariable("codigo") Integer codigo) {
		Usuario usuario = usuarioService.listarPorCodigo(codigo);
		return validarEntidad(usuario);
	}

	@DeleteMapping("/usuario/{codigo}")
	public ResponseEntity<CustomResponseType> eliminarUsuarioPorCodigo(@PathVariable("codigo") Integer codigo) {
		usuarioService.eliminar(codigo);
		return new ResponseEntity<>(new CustomMessageType("Exito al eliminar el usuario"), HttpStatus.OK);

	}

	private boolean isValidate(Usuario usuario) {
		boolean isValid = true;

		if (StringUtils.trimToNull(usuario.getEmail()) == null) {
			isValid = false;
		}
		if (StringUtils.trimToNull(usuario.getLogin()) == null) {
			isValid = false;
		}
		if (StringUtils.trimToNull(usuario.getPrimerNombre()) == null) {
			isValid = false;
		}
		if (StringUtils.trimToNull(usuario.getSegundoNombre()) == null) {
			isValid = false;
		}

		return isValid;
	}

	/**
	 * Validar si una lista de resultados de entidad del modelo está vacía o tiene
	 * resultados.
	 * 
	 * @param listaEntidad
	 *            Lista de alguna de las entidades del modelo
	 * @return Lista de resultados con estatus OK si no está vacía. Estatus
	 *         NO_CONTENT si no tiene resultados.
	 */
	private <T> ResponseEntity<List<T>> validarListaEntidad(List<T> listaEntidad) {
		if (listaEntidad.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(listaEntidad, HttpStatus.OK);
	}

	/**
	 * Validar si un objeto de entidad del modelo está vacía o tiene resultados.
	 * 
	 * @param entidad
	 *            Alguna de las entidades del modelo
	 * @return Entidad con estatus OK si existe. Estatus NO_CONTENT si no existe.
	 */
	private <T> ResponseEntity<?> validarEntidad(T entidad) {
		if (entidad == null) {
			return new ResponseEntity<>(new CustomErrorType("No se encontraron resultados para la consulta"),
					HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(entidad, HttpStatus.OK);
	}
}

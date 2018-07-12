package com.usuario.api.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

/**
 * Clase para definir propiedades y métodos para las respuestas de los
 * servicios, con errores
 * 
 *
 */
public class CustomErrorType extends CustomResponseType {
	private int statusCode;
	private List<FieldError> fieldErrors = new ArrayList<>(0);

	public CustomErrorType(String message) {
		super(message);
	}

	public CustomErrorType(int statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void addFieldError(String path, String field, String message) {
		FieldError error = new FieldError(path, field, message);
		fieldErrors.add(error);
	}


}

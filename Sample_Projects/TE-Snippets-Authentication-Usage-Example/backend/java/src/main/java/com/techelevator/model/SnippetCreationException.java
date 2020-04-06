package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class SnippetCreationException extends Exception {
	
	private static final long serialVersionUID = 9154702287751082647L;

	public SnippetCreationException(String messages) {
		super(messages);
	}

}

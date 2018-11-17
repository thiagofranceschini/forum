package br.com.alura.forum.service.infra;

public class ForumMailServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ForumMailServiceException(String message, Throwable cause){
		super(message, cause);
	}

}

package fr.diginamic.notedefrais.exception;

public class NoteDeFraisException extends Exception {

	public NoteDeFraisException() {
		super();
	}

	public NoteDeFraisException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoteDeFraisException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteDeFraisException(String message) {
		super(message);
	}

	public NoteDeFraisException(Throwable cause) {
		super(cause);
	}
	
}

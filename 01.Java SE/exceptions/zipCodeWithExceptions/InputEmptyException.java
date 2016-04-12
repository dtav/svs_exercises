package zipCodeWithExceptions;

public class InputEmptyException extends RuntimeException{
	public InputEmptyException() {
		// TODO Auto-generated constructor stub
	}

	public InputEmptyException(String msg) {
		super(msg);
	}
}

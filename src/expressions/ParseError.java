package expressions;

public class ParseError extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParseError(final String message) {
		super(message);
	}
}

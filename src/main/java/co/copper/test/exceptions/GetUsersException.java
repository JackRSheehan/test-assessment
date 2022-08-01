package co.copper.test.exceptions;

public class GetUsersException extends Exception {
    public GetUsersException(final String errorMessage) {
        super(errorMessage);
    }
}

package pl.lenistwo.uzicape.exception;

public class ApiCallException extends RuntimeException {
    public ApiCallException(Exception e) {
        super(String.format("API_CALL_EXCEPTION %s", e));
    }
}

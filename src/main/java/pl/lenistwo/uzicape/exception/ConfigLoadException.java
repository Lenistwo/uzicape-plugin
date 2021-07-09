package pl.lenistwo.uzicape.exception;

public class ConfigLoadException extends RuntimeException {
    public ConfigLoadException(Exception e) {
        super(String.format("ERROR_DURING_LOADING_CONFIG_FILE %s", e));
    }
}

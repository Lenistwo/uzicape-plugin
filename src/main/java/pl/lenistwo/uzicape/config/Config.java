package pl.lenistwo.uzicape.config;

import lombok.Data;

@Data
public class Config {
    private final String serviceURL;
    private final String apiKey;
    private final String commandName;
    private final String commandDescription;
    private final String commandUsage;
    private final String apiErrorMessage;
}

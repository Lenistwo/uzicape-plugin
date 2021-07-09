package pl.lenistwo.uzicape.config;

import lombok.Data;

@Data
public class Config {
    private final String serviceURL;
    private final String commandName;
    private final String apiKey;
}

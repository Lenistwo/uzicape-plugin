package pl.lenistwo.uzicape.service;

import lombok.RequiredArgsConstructor;
import pl.lenistwo.uzicape.config.Config;

@RequiredArgsConstructor
public class HttpService {

    private final Config config;

    public String get(){
        return "get";
    }
}

package pl.lenistwo.uzicape.response;

import lombok.Data;

@Data
public class HttpResponse {
    private final int code;
    private final String message;
}

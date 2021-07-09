package pl.lenistwo.uzicape.response;

import lombok.Data;


@Data
public class RedeemResponse {
    private final boolean success;
    private final String message;
    private final String minecraft;
    private final HttpResponse http;
}

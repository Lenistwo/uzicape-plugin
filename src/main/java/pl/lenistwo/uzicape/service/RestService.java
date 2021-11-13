package pl.lenistwo.uzicape.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import pl.lenistwo.uzicape.config.Config;
import pl.lenistwo.uzicape.dto.request.RedeemRequest;
import pl.lenistwo.uzicape.dto.response.RedeemResponse;
import pl.lenistwo.uzicape.exception.ApiException;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class RestService {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    private final Gson gson;
    private final Config config;
    private final OkHttpClient okHttpClient;

    public RedeemResponse redeemCode(String code, String username) throws ApiException {
        String URL = prepareUrl(code);
        Request.Builder builder = new Request.Builder();
        RequestBody requestBody = RequestBody.create(gson.toJson(new RedeemRequest(username)), MediaType.parse(CONTENT_TYPE_VALUE));
        Request request = builder.url(URL).post(requestBody).addHeader(API_KEY_HEADER, config.getApiKey()).build();
        String body = null;
        try (Response response = okHttpClient.newCall(request).execute()) {
            body = Objects.requireNonNull(response.body()).string();
            return gson.fromJson(body, RedeemResponse.class);
        } catch (Exception e) {
            log.error("Exception {} \nApi Response: {}", e.getMessage(), body);
            throw new ApiException(config.getApiErrorMessage());
        }

    }

    private String prepareUrl(String code) {
        return config.getServiceURL().endsWith("/") ? config.getServiceURL() + code : config.getServiceURL() + "/" + code;
    }
}

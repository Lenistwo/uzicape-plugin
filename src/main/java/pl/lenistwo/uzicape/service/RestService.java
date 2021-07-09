package pl.lenistwo.uzicape.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import pl.lenistwo.uzicape.config.Config;
import pl.lenistwo.uzicape.request.RedeemRequest;
import pl.lenistwo.uzicape.response.HttpResponse;
import pl.lenistwo.uzicape.response.RedeemResponse;

import java.util.Objects;

@RequiredArgsConstructor
public class RestService {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String CONTENT_TYPE_VALUE = "application/json";

    private final Gson gson;
    private final Config config;
    private final OkHttpClient okHttpClient;

    public RedeemResponse redeemCode(String code, String username) {
        Request.Builder builder = new Request.Builder();
        String slash = "/";
        String URL = config.getServiceURL().endsWith(slash) ? config.getServiceURL() + code : config.getServiceURL() + slash + code;
        RequestBody requestBody = RequestBody.create(gson.toJson(new RedeemRequest(username)), MediaType.parse(CONTENT_TYPE_VALUE));
        Request request = builder.url(URL).post(requestBody).addHeader(API_KEY_HEADER, config.getApiKey()).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return gson.fromJson(Objects.requireNonNull(response.body()).string(), RedeemResponse.class);
        } catch (Exception e) {
            return new RedeemResponse(false, "API ERROR", config.getApiErrorMessage(), new HttpResponse(400, "Bad Request"));
        }

    }
}

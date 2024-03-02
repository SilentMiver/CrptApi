package org.example;

import java.net.http.HttpClient;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    public static final String API_LINK = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private final HttpClient httpClient;
    private final AtomicInteger requestCounter;

    public CrptApi(TimeUnit timeUnit, int request_limit) {
        this.httpClient = HttpClient.newHttpClient();
        this.requestCounter = new AtomicInteger(request_limit);

    }

}

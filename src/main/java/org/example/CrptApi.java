package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

import java.net.http.HttpClient;
import java.time.Instant;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    public static final String API_LINK = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private final OkHttpClient httpClient;
    private final Semaphore semaphore;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CrptApi(TimeUnit timeUnit, int request_limit) {
        this.semaphore = new Semaphore(request_limit);
        this.httpClient = new OkHttpClient();


    }


}

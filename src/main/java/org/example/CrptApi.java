package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi {
    public static final String API_LINK = "https://ismp.crpt.ru/api/v3/lk/documents/create";
    private final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final AtomicInteger requestCounter = new AtomicInteger(0);
    private final Semaphore semaphore;
    private final int request_limit;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CrptApi(TimeUnit timeUnit, int request_limit) {
        this.request_limit = request_limit;
        this.semaphore = new Semaphore(request_limit);


    }


    public void createDocument(Document document, String signature) throws IOException, InterruptedException {
        while (true){
        if (requestCounter.get() > request_limit) {
            System.out.println("Reached request limit."+requestCounter.get() + " Waiting for next period." + Thread.currentThread().getName());
            Thread.sleep(1000);

           continue;
        }
            break;
        }
        requestCounter.incrementAndGet();
        semaphore.acquire();


        try {
            System.out.println("Я занял. ждем 1000 миллисекунд");
//            String json = objectMapper.writeValueAsString(document);
//            RequestBody requestBody = RequestBody.create(JSON, json);
//            Request request = new Request.Builder()
//                    .url(API_LINK)
//                    .post(requestBody)
//                    .addHeader("Signature", signature)
//                    .build();
//            Response response = httpClient.newCall(request).execute();
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected response code: " + response);
//            }
//            // Handle response here if needed
//            System.out.println("Document created successfully");
            Thread.sleep(10000);




        } finally {
            System.out.println("vse" + Thread.currentThread().getName());
            semaphore.release();
            requestCounter.decrementAndGet();
        }
    }

    public static class Document {
        public Description description;
        public String doc_id;
        public String doc_status;
        public String doc_type;
        public boolean importRequest;
        public String owner_inn;
        public String participant_inn;
        public String producer_inn;
        public String production_date;
        public String production_type;
        public ArrayList<Product> products;
        public String reg_date;
        public String reg_number;

        public Document(Description description, String doc_id, String doc_status, String doc_type,
                        boolean importRequest, String owner_inn, String participant_inn, String producer_inn,
                        String production_date, String production_type, ArrayList<Product> products, String reg_date,
                        String reg_number)
        {
            this.description = description;
            this.doc_id = doc_id;
            this.doc_status = doc_status;
            this.doc_type = doc_type;
            this.importRequest = importRequest;
            this.owner_inn = owner_inn;
            this.participant_inn = participant_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.production_type = production_type;
            this.products = products;
            this.reg_date = reg_date;
            this.reg_number = reg_number;
        }
        // Значения из файла ТЗ
        public static Document createTemplate() {
            Description description = new Description();
            description.participantInn = "string";

            Product product = new Product();
            product.certificate_document = "string";
            product.certificate_document_date = "2020-01-23";
            product.certificate_document_number = "string";
            product.owner_inn = "string";
            product.producer_inn = "string";
            product.production_date = "2020-01-23";
            product.tnved_code = "string";
            product.uit_code = "string";
            product.uitu_code = "string";

            ArrayList<Product> products = new ArrayList<>();
            products.add(product);

            return new Document(description, "string", "string", "LP_INTRODUCE_GOODS",
                    true, "string", "string", "string",
                    "2020-01-23", "string", products, "2020-01-23", "string");
        }

        public static class Description {
            public String participantInn;
        }

        public static class Product {
            public String certificate_document;
            public String certificate_document_date;
            public String certificate_document_number;
            public String owner_inn;
            public String producer_inn;
            public String production_date;
            public String tnved_code;
            public String uit_code;
            public String uitu_code;
        }
    }


}

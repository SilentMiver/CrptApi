package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
//        CrptApi.Document document = CrptApi.Document.createTemplate();
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(document);
//        System.out.println(json);
        CrptApi crptApi = new CrptApi(TimeUnit.MILLISECONDS,1);
        new Thread(() -> {
            try {
                System.out.println("Запуск 1");
                crptApi.createDocument(CrptApi.Document.createTemplate(),"based");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println("Запуск 2");
                crptApi.createDocument(CrptApi.Document.createTemplate(),"cringe");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
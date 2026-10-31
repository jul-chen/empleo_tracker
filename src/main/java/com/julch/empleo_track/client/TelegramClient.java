package com.julch.empleo_track.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramClient {

    private final RestClient restClient;

    @Value("$telegram.bot-token")
    private String botToken;
    @Value("$telegram.chat-id")
    private String chatId;

    public TelegramClient(){
        this.restClient = RestClient.builder().baseUrl("https://api.telegram.org").build();
    }

    public void enviarMensaje(String mensaje){
        Map<String,String> map = new HashMap<>();
        map.put("chat_id",chatId);
        map.put("text",mensaje);

        restClient.post().uri("/bot{token}/mensaje}",botToken)
                .body(map)
                .retrieve()
                .toBodilessEntity();
    }






}

package com.julch.empleo_track.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramClient {

    private final RestClient restClient;

    @Value("${telegram.bot-token}")
    private String botToken;
    @Value("${telegram.chat-id}")
    private String chatId;

    public TelegramClient(){
        this.restClient = RestClient.builder().baseUrl("https://api.telegram.org").build();
    }

    public void enviarMensaje(String mensaje){
        Map<String, String> map = Map.of(
                "chat_id", chatId,
                "text", mensaje,
                "parse_mode", "HTML"
        );
        String urlTelegram = "/bot" + botToken + "/sendMessage";
        restClient.post().uri(urlTelegram)
                .contentType(MediaType.APPLICATION_JSON)
                .body(map)
                .retrieve()
                .toBodilessEntity();
    }






}

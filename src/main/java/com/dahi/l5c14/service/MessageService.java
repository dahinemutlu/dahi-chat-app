package com.dahi.l5c14.service;

import com.dahi.l5c14.mapper.MessageMapper;
import com.dahi.l5c14.model.ChatForm;
import com.dahi.l5c14.model.ChatMessage;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Created MessageService bean");
    }

    public List<ChatMessage> getChatMessages() {
        return messageMapper.getAllMessages();
    }

    public void addMessage(ChatForm newChatForm) {

        ChatMessage newChatMessage = new ChatMessage();

        newChatMessage.setUsername(newChatForm.getUsername());

        switch(newChatForm.getMessageType()) {
            case "Normal":
                newChatMessage.setMessage(newChatForm.getMessage());
                break;
            case "Büyük Harf":
                newChatMessage.setMessage(newChatForm.getMessage().toUpperCase());
                break;
            case "Küçük Harf":
                newChatMessage.setMessage(newChatForm.getMessage().toLowerCase());
                break;
        }
        messageMapper.addMessage(newChatMessage);
    }
}

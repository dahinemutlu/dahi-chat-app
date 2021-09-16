package com.dahi.l5c14.controller;

import com.dahi.l5c14.model.ChatForm;
import com.dahi.l5c14.model.ChatMessage;
import com.dahi.l5c14.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChat(ChatForm chatForm, Model model){
        model.addAttribute("chatMessages",this.messageService.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChat(Authentication authentication, ChatForm chatForm, Model model) {
        chatForm.setUsername(authentication.getName());
        this.messageService.addMessage(chatForm);
        chatForm.setMessage("");
        model.addAttribute("chatMessages",this.messageService.getChatMessages());
        return "chat";
    }
    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes () {
        return new String[] { "Normal", "Büyük Harf", "Küçük Harf" };
    }
}

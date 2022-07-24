package com.eeit45.champion.vegetarian.controller.websocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @GetMapping("/one")
    public void pushOne()
    {
        WebSocketService.sendMessage("cat","貓咪");
    }
}

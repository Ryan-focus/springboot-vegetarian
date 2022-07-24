package com.eeit45.champion.vegetarian.controller.websocket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/websocket")
public class WebSocketController {

    @GetMapping("/pushone")
    public void pushone()
    {
        WebSocketService.sendMessage("badao","公眾號:霸道的程序猿");
    }
}

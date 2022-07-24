package com.eeit45.champion.vegetarian.controller.websocket;

import com.eeit45.champion.vegetarian.model.WebSocketClient;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{userName}")
@Component
public class WebSocketService {

    private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);

    //靜態變量，用來記錄當前在線連接數。應該把它設計成線程安全的。
    private static int onlineCount = 0;
    //concurrent包的線程安全Set，用來存放每個客戶端對應的WebSocketServer對象。
    private static ConcurrentHashMap<String, WebSocketClient> webSocketMap = new ConcurrentHashMap<>();


    /**與某個客戶端的連接會話，需要通過它來給客戶端發送數據*/
    private Session session;
    /**接收userName*/
    private String userName="";
    /**
     * 連接建立成功調用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("userName") String userName) {
        if(!webSocketMap.containsKey(userName))
        {
            addOnlineCount(); // 在線數+1
        }
        this.session = session;
        this.userName= userName;
        WebSocketClient client = new WebSocketClient();
        client.setSession(session);
        client.setUri(session.getRequestURI().toString());
        webSocketMap.put(userName, client);

        log.info("----------------------------------------------------------------------------");
        log.info("用戶連接:"+userName+",當前在線人數為 : " + getOnlineCount());
        try {
            sendMessage("有 " + getOnlineCount() + "人，在瀏覽此頁面");
        } catch (IOException e) {
            log.error("用戶:"+userName+",網絡異常!!!!!!");
        }
    }

    /**
     * 連接關閉調用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userName)){
            if(webSocketMap.size()>0)
            {
                //從set中刪除
                subOnlineCount();
                webSocketMap.remove(userName);
            }
        }
        log.info("----------------------------------------------------------------------------");
        log.info(userName+"用戶退出,當前在線人數為:" + getOnlineCount());
    }

    /**
     * 收到客戶端消息後調用的方法
     *
     * @param message 客戶端發送過來的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到用戶消息:"+userName+",內容:"+message);
        //可以群發消息
        //消息保存到數據庫、redis
        if(StringUtils.isNotBlank(message)){

        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用戶錯誤:"+this.userName+",原因:"+error.getMessage());
        error.printStackTrace();
    }

    /**
     * 連接服務器成功後主動推送
     */
    public void sendMessage(String message) throws IOException {
        synchronized (session){
            this.session.getBasicRemote().sendText(message);
        }
    }

    /**
     * 向指定客戶端發送消息
     * @param userName
     * @param message
     */
    public static void sendMessage(String userName,String message){
        try {
            WebSocketClient webSocketClient = webSocketMap.get(userName);
            if(webSocketClient!=null){
                webSocketClient.getSession().getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketService.onlineCount--;
    }

    public static void setOnlineCount(int onlineCount) {
        WebSocketService.onlineCount = onlineCount;
    }


    public static ConcurrentHashMap<String, WebSocketClient> getWebSocketMap() {
        return webSocketMap;
    }

    public static void setWebSocketMap(ConcurrentHashMap<String, WebSocketClient> webSocketMap) {
        WebSocketService.webSocketMap = webSocketMap;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
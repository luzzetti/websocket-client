import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@ClientEndpoint
public class ChatClientEndpoint {

    Session userSession = null;

    Logger logger = Logger.getLogger(getClass().getName());
    private MessageHandler messageHandler;

    public ChatClientEndpoint(URI endpointURI) {
        try {
            System.out.println("Creo la WebSocket");
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("Client: onOpen");
        this.userSession = userSession;
    }


    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        logger.info("sessione chiusa " + reason);
        this.userSession = null;
    }


    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        if (this.messageHandler != null)
            this.messageHandler.handleMessage(message);
    }

    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public void sendMessage(String message) {
//        this.userSession.getAsyncRemote().sendText(message);
        try {
            this.userSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.warning("Non è stato possibile inviare il messaggio");
        }
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}

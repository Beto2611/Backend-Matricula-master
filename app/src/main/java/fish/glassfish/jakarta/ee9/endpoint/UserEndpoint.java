package fish.glassfish.jakarta.ee9.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import backend.matricula.controllers.UserController;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import fish.glassfish.jakarta.ee9.endpoint.decode.JsonObjectDecoder;
import fish.glassfish.jakarta.ee9.endpoint.encode.JsonObjectEncoder;


@ServerEndpoint(value = "/crearUsuario", decoders = {JsonObjectDecoder.class}, encoders = {JsonObjectEncoder.class})
public class UserEndpoint {
    private static final UserController controller = UserController.getInstance();

   private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    public void broadcast(JSONObject message) throws EncodeException, IOException {
        if (message == null) return;
        for (var session : sessions) {
            session.getBasicRemote().sendObject(controller.processQuery(message, session));
        }   
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(JSONObject message, Session session) throws IOException, EncodeException {
        var response = controller.processQuery(message, session);
        if (response != null) {
            session.getBasicRemote().sendObject(response);
            this.broadcast(switch (response.optString("action")) {
                case "CREATE", "UPDATE", "DELETE" -> new JSONObject().put("action", "GET_ALL");
                default -> null;
            });
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.format("Error for session %s: %s%n", session.getId(), throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}

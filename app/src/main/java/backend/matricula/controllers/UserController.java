package backend.matricula.controllers;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import backend.matricula.models.UserModel;
import jakarta.websocket.Session;

public class UserController {
    //private static final UserModel model = UserModel.getInstance();
    private static UserController instance = null;
    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }

    public JSONObject processQuery(JSONObject object, Session session) {
        if (object == null) return null;
        try {
            return switch (object.getString("action")) {
                // case "GET" -> get(object.optString("token"), session);
                case "REGISTER" -> register(object);
                // case "UPDATE" -> update(object, session);
                default -> null;
            };
        } catch (JSONException ex) {
            return null;
        }
    }

    private JSONObject register(JSONObject object) {
        var response = new JSONObject();
        try {
            UserModel.getInstance().create(
                    object.getString("username"),
                    object.getString("password"),
                    object.getString("rol")
                           
            );
            response.put("action", "REGISTER");
        } catch (SQLException ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }
}

package backend.matricula.controllers;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;
import backend.matricula.models.AlumnoModel;
import jakarta.websocket.Session;

public class AlumnoController {
    private static final AlumnoModel model = AlumnoModel.getInstance();
    private static AlumnoController instance = null;
    public static AlumnoController getInstance() {
        if (instance == null) instance = new AlumnoController();
        return instance;
    }

    public JSONObject processQuery(JSONObject object, Session session) {
        if (object == null) return null;
        try {
            return switch (object.getString("action")) {
                // case "GET" -> get(object.optString("token"), session);
                case "GETALUMNOID" -> getAlumnoID(object);
                case "GETALUMNONOMBRE" -> getAlumnoNombre(object);
                case   "REGISTER"-> register(object); 
                case   "GETALUMNOCARRERA"-> getAlumnoCarrera(object);
                default -> null;
            };
        } catch (JSONException ex) {
            return null;
        }
    }
    private JSONObject getAlumnoNombre(JSONObject object) {
        var response = new JSONObject();
        JSONObject data = null;
        try {
            AlumnoModel.getInstance().getAlumnoPorNombre(
                    object.getString("nombre")                         
            );
            String nombre = (String) object.get("nombre");
            data = model.getAlumnoPorNombre(nombre);
            response.put("action", "GETALUMNONOMBRE");
            response.put("data", data);
        } catch (SQLException ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }

    private JSONObject getAlumnoID(JSONObject object) {
        var response = new JSONObject();
        JSONObject data = null;
        try {
            AlumnoModel.getInstance().getAlumnoPorId(
                    object.getString("id")                         
            );
            String id = (String) object.get("id");
            data = model.getAlumnoPorId(id);
            response.put("action", "GETALUMNOID");
            response.put("data", data);
        } catch (SQLException ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }
    private JSONObject register(JSONObject object) {
        var response = new JSONObject();
        try {
            AlumnoModel.getInstance().create(
                object.getString("id"),
                object.getString("Nombre"),
                object.getString("Telefono"),
                object.getString("Email"),
                object.getString("FechaNacimiento"),
                object.getString("CodigoCarrera")               
            );
            response.put("action", "REGISTER");
        } catch (Exception ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        }
        return response;
    }
    private JSONObject getAlumnoCarrera(JSONObject object){
        var response = new JSONObject();
        JSONObject data = null;
        try {
            AlumnoModel.getInstance().getAlumnoPorCarrera(
                    object.getString("id")                         
            );
            String nombre = (String) object.get("nombre");
            data = model.getAlumnoPorId(nombre);
            response.put("action", "GETALUMNOCARRERA");
            response.put("data", data);
        } catch (SQLException ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }
    
}

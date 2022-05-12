package backend.matricula.controllers;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import backend.matricula.models.ProfesorModel;
import jakarta.websocket.Session;

public class ProfesorController {
    private static ProfesorController instance = null;
    private ProfesorModel model = ProfesorModel.getInstance();


    public static ProfesorController getInstance(){
        if(instance==null) instance= new ProfesorController();
        return instance;
    }
    public JSONObject processQuery(JSONObject object, Session session) {
        if (object == null) return null;
        try {
            return switch (object.getString("action")) {
                // case "GET" -> get(object.optString("token"), session);
                case "GETPROFESORID" -> GETPROFESORID(object);
                case   "REGISTER"-> register(object); 
                case   "GETPROFESORPORNOMBRE"-> GETPROFESORPORNOMBRE(object);
                default -> null;
            };
        } catch (JSONException ex) {
            return null;
        }
    }
    private JSONObject GETPROFESORID(JSONObject object) {
        var response = new JSONObject();
        JSONObject data = null;
        try {
            ProfesorModel.getInstance().getProfesorPorId(
                    object.getString("id")                         
            );
            String id = (String) object.get("id");
            data = model.getProfesorPorId(id);
            response.put("action", "GETPROFESORID");
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
            ProfesorModel.getInstance().create(
                object.getString("Id"),
                object.getString("Nombre"),
                object.getString("Telefono"),
                object.getString("Email")              
            );
            response.put("action", "REGISTER");
        } catch (Exception ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        }
        return response;
    }
    private JSONObject GETPROFESORPORNOMBRE(JSONObject object){
        var response = new JSONObject();
        JSONObject data = null;
        try {
            ProfesorModel.getInstance().getProfesorPorId(
                    object.getString("nombre")                         
            );
            String nombre = (String) object.get("nombre");
            data = model.getProfesorPorNombre(nombre);
            response.put("action", "GETPROFESORPORNOMBRE");
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

package backend.matricula.controllers;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import backend.matricula.models.NotaModel;
import jakarta.websocket.Session;

public class NotaController {
    private static NotaController instance = null;
    //private NotaModel model = NotaModel.getInstance();

    public static NotaController getInstance(){
        if(instance == null) instance= new NotaController();
        return instance;
    }


    public JSONObject processQuery(JSONObject object,Session session) throws SQLException{
        if(object==null) return null;
        try {
            return switch (object.getString("action")) {
                // case "GET" -> get(object.optString("token"), session);
                
                case   "REGISTER"-> register(object); 
                case "GETHISTORIAL"->getHistorialPorIdYCiclo(object);
                case "GETHISTORIALPORID"->getHistorialPorId(object);
                case "UPDATENOTA"->UpdateNota(object);
                default -> null;
            };
        } catch (JSONException ex) {
            return null;
        }
}
private JSONObject register(JSONObject object) {
    var response = new JSONObject();
    try {
        NotaModel.getInstance().create(
            object.getInt("idCiclo"),
            object.getString("CodigoCurso"),
            object.getString("idProfesor"),
            object.getString("idAlumno"),
            object.getInt("Nota")
                         
        );
        response.put("action", "REGISTER");
    } catch (Exception ex) {
        response.put("action", "ERROR");
        response.put("type", "DUPLICATE");
    }
    return response;
}
        private JSONObject UpdateNota(JSONObject object) {
            var response = new JSONObject();
            try {
                NotaModel.getInstance().update(
                    object.getString("codigoCurso"),
                    object.getInt("Nota"),
                    object.getString("idAlumno")            
                );
                response.put("action", "UPDATENOTA");
            } catch (Exception ex) {
                response.put("action", "ERROR");
                response.put("type", "DUPLICATE");
            }
            return response;
        }

    public JSONObject getHistorialPorIdYCiclo(JSONObject object){
        var response = new JSONObject();
        try {
          
           ;
            try {  
            String idAlumno = (String) object.get("idAlumno");
            String idCiclo = (String) object.get("idCiclo");
               var data = NotaModel.getInstance().getHistorialPorIdYCiclo(idAlumno,idCiclo);
                response.put("action", "GETHISTORIAL");
                response.put("data", data);
            } catch (SQLException e) {
                return null;
            }  
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }

    public JSONObject getHistorialPorId(JSONObject object){
        var response = new JSONObject();
        try {
          
           ;
            try {  
            String idAlumno = (String) object.get("idAlumno");;
               var data = NotaModel.getInstance().getHistorialPorId(idAlumno);
                response.put("action", "GETHISTORIALPORID");
                response.put("data", data);
            } catch (SQLException e) {
                return null;
            }  
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }

}

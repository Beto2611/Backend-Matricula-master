package backend.matricula.controllers;



import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

import backend.matricula.models.GrupoModel;
import jakarta.websocket.Session;

public class GrupoController {
    private static GrupoController instance = null;
    private static final GrupoModel model = GrupoModel.getInstance();

    
    public static  GrupoController getInstance() {
        if (instance == null) instance = new GrupoController();
        return instance;
    }
    public JSONObject processQuery(JSONObject object, Session session) throws SQLException {
        if (object == null) return null;
        try {
            return switch (object.getString("action")) {
                case "GETALUMNOSPORIDGRUPO" -> getAlumnosPorIdGrupo(object);
                case "GETGRUPOSPORIDCURSO" -> buscarGrupoPorCodigoCurso(object);
                case "GETGRUPOSPORCARRERA" -> buscarGrupoPorCarrera(object);
                case "POSTGRUPO" -> register(object);
                case "GETGRUPOSPORIDPROFESOR" -> getProfesorPorId(object);
                
                case "MATRICULAR" -> Matricular(object);
                default -> null;
            };
        } catch (JSONException ex) {
            return null;
        }
    }

    public JSONObject getAlumnosPorIdGrupo(JSONObject object) {
        var response = new JSONObject();
        try {
            Integer idGrupo = (Integer) object.get("IdGrupo");
            var data = model.buscarAlumnosPorIdGrupoNotas(idGrupo);
            response.put("action", "GETALUMNOSPORIDGRUPO");
            response.put("data", data);
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }
    public JSONObject getProfesorPorId(JSONObject object) {
        var response = new JSONObject();
        try {
            String idGrupo = (String) object.get("IdProfesor");
            var data = model.buscarGrupoPorIdProfesor(idGrupo);
            response.put("action", "GETGRUPOSPORIDPROFESOR");
            response.put("data", data);
        } catch (JSONException ex) {
            return null;
        }
        return response;
    }

    public JSONObject buscarGrupoPorCodigoCurso(JSONObject object) throws SQLException {
        var response = new JSONObject();
        try {
            String idCurso =  object.getString("idCurso");
            var data = model.getGruposPorCodigo(idCurso);
            response.put("action", "GETGRUPOSPORIDCURSO");
            response.put("data", data);
        } catch (JSONException ex) {
            return response.put("data","error al obtener grupo");
        }
        return response;
    }

    public JSONObject buscarGrupoPorCarrera(JSONObject object) throws SQLException {
        var response = new JSONObject();
        try {
            String idCarrera =  object.getString("idCarrera");
            var data = model.getGruposPorCarrera(idCarrera);
            response.put("action", "GETGRUPOSPORCARRERA");
            response.put("data", data);
        } catch (JSONException ex) {
            return response.put("data","error al obtener grupo");
        }
        return response;
    }

    private JSONObject register(JSONObject object) {
        var response = new JSONObject();
        try {
            GrupoModel.getInstance().create(
                object.getString("profesor"),
                object.getInt("idCiclo"),
                object.getString("codigoCurso"),
                object.getString("horaInicio"),
                object.getString("horaFinal")       
            );
            response.put("action", "POSTGRUPO");
        } catch (Exception ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        }
        return response;
    }

    private JSONObject Matricular(JSONObject object) {
        var response = new JSONObject();
        try {
            GrupoModel.getInstance().matricular(
                object.getString("Id"),
                object.getString("Nombre"),
                object.getInt("IdGrupo")
            );
            response.put("action", "MATRICULAR");
        } catch (Exception ex) {
            response.put("action", "ERROR");
            response.put("type", "DUPLICATE");
        }
        return response;
    }
}

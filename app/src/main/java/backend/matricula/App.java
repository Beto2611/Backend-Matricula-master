/*
 * This Java source file was generated by the Gradle "'init" task.
 */
package backend.matricula;



import backend.matricula.data.GrupoDBA;
import backend.matricula.data.NotaDBA;
import backend.matricula.data.ProfesorDBA;
import backend.matricula.data.UserDBA;

// import backend.matricula.data.CarreraDBA;
// import backend.matricula.data.CicloDBA;
// import backend.matricula.data.CursoDBA;
// import backend.matricula.data.NotaDBA;



// import java.sql.Connection;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import backend.matricula.data.databaseconnection;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        NotaDBA puto = NotaDBA.getInstance();
        try {
           // var resultado = puto.insertarGrupo("116335485",2,"EIF402","17:00","20:40");
          //puto.registerStudent("999","AURON","1234","Estudiante","5235-3366","Willy@gmail.com", "12/04/2022","INF");
         //puto.registerProfessor("1346754","LORIA","1234","Profesor","5235-3366","Loria@gmail.com");
         //  puto.registerUsers("AdminRobot", "1234", "Admin");
         var resultado = puto.buscarHistorialPorId("777");
         System.out.println(resultado);
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }
}

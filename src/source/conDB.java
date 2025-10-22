/**
 * @author KELUARGA ZALONI PC
 * @see https://github.com/rizvalni9/
 **/
package source;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class conDB {
    
    public Connection paths;
    public Statement contains;
    public DriverManager drivers;
    
    public void routing(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            paths=drivers.getConnection("jdbc:mysql://localhost/modul_3","root","");
            contains=paths.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "DATABASE NOT CONNECTED !!!","ERROR",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}

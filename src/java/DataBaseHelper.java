/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tara Ram Goyal
 */
public class DataBaseHelper {

    private final String DB = "BlobDB";
    private final String PASSWORD = "root";
    private final String USRNAME = "root";

    protected Connection conn;
    protected PreparedStatement pst;
    protected ResultSet resultset;

    public DataBaseHelper() {
    }

    protected Connection setConnection() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + DB, USRNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("Set Connection Error !!!  " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Set Connection Error !!!  " + ex.getMessage());
        } finally {
            return conn;
        }
    }

    protected int writeResultset(PreparedStatement pst) {

        this.pst = pst;
        int ra = 0;
        try {
            ra = pst.executeUpdate();
            if (ra != 0) {
               System.out.println("Done.");
            }

        } catch (SQLException ex) {
          System.out.println("Write Data Error !!!  " + ex.getMessage());
        }
        System.out.println("Row Affectd " + ra);
        return ra;
    }

    protected ResultSet getResultset(PreparedStatement pst) {
        this.pst = pst;

        try {
            resultset = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Get Data Error !!!  " + ex.getMessage());
        }

        return resultset;
    }

}

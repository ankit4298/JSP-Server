/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANKIT
 */
public class FetchEmployeeLocations {

    ArrayList<ArrayList> al;

    Connection con;
    Statement stmt;
    ResultSet rs;

    public FetchEmployeeLocations() {
        al = new ArrayList<>();
        con = DBConnection.getDBConnection();
    }

    public ArrayList<ArrayList> returnLocations() {

        ArrayList eid = new ArrayList();
        ArrayList lat = new ArrayList();
        ArrayList lng = new ArrayList();

        try {
            stmt = con.createStatement();

            TimeZone tz = TimeZone.getTimeZone("GMT+5:30");
            Calendar c = Calendar.getInstance(tz);
            String strDate = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
            
            String sql = "select eid,latitude,longitude from attendance_details where date='" +strDate+"' and outtime=-1";
            rs = stmt.executeQuery(sql);

            
            while (rs.next()) {
                String db_eid = rs.getString("eid");
                double db_lat = rs.getDouble("latitude");
                double db_lng = rs.getDouble("longitude");
                
                eid.add(db_eid);
                lat.add(db_lat);
                lng.add(db_lng);
            }

            al.add(eid);
            al.add(lat);
            al.add(lng);

        } catch (SQLException ex) {
            Logger.getLogger(FetchEmployeeLocations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }

}

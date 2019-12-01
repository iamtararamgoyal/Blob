/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tara Ram Goyal
 */
@WebServlet("/list")
public class items extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List:Items</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>List of all items</h3>");
            out.println("<table border=2>");
            out.println("<tr><th>SR No.</th><th>Item Name</th><th>Item Image</th></tr>");
            String query="SELECT * FROM items";
            DataBaseHelper dbh=new DataBaseHelper();
            Connection con=dbh.setConnection();
            try{
                PreparedStatement ps=con.prepareStatement(query);
                ResultSet res = ps.executeQuery();
                while(res.next()){
                    out.println("<tr><td>"+res.getInt("srno")+"</td>");
                    out.println("<td>"+res.getString("iname")+"</td>");
                    Blob blob=res.getBlob("iimage");
                    out.println("<td><center><img src=\"data:image/gif;base64,"+blobProcess(blob)+"\"height=\"42\" width=\"42\"/></center></td></tr>");
                }
                out.println("</table>");
                out.println("<a href=\"index.jsp\">Go to home</a>");
            }catch(SQLException sqle){
               out.println("<h4 style=\"color:red\">"+sqle.getMessage()+"</h4>");
            }
            catch(IOException ie){
                out.println("<h4 style=\"color:red\">"+ie.getMessage()+"</h4>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public String blobProcess(Blob blob) throws SQLException, IOException{
        
        String imgurl="";
        InputStream inputStream= blob.getBinaryStream();
        ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();
        byte [] buffer=new byte[4096];
        int bytesRead=-1;
        while((bytesRead=inputStream.read(buffer)) != -1)
            arrayOutputStream.write(buffer, 0, bytesRead);
        
        byte [] bytes=arrayOutputStream.toByteArray();
        imgurl=Base64.getEncoder().encodeToString(bytes);
        
        /***
         * shortcut way
         *
        bytes=blob.getBytes(1, (int) blob.length());
        imgurl=Base64.getEncoder().encodeToString(bytes);
        * 
        */
        inputStream.close();
        arrayOutputStream.close();
        return imgurl;
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

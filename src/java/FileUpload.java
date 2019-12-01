/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Tara Ram Goyal
 */
@WebServlet("/FileUpload")
@MultipartConfig(maxFileSize = 16177215) //Upload file size up to 16MB
public class FileUpload extends HttpServlet {

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

            String itemName = request.getParameter("item_name");
            Part filePart = request.getPart("filename");
            InputStream inputStream = null;
            PreparedStatement ps;
            String query = "";
            DataBaseHelper dbh = new DataBaseHelper();
            Connection con;
            String message;
            if (filePart != null) 
                inputStream = filePart.getInputStream();
            query = "INSERT INTO items (iname,iimage) VALUES(?,?)";
            con = dbh.setConnection();
            try{
                ps = con.prepareStatement(query);
                ps.setString(1, itemName);
                if(inputStream != null)
                    ps.setBlob(2, inputStream);
                int row = ps.executeUpdate();
                if(row > 0)
                    message = "Image Uploaded and saved into DB.";
                else
                    message = "Image Upload Failed";
                
            }catch(SQLException sqe){
                message="Error:"+sqe.getMessage();
                sqe.printStackTrace();
            }
            finally{
                if(con!=null){
                    try{
                        con.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            request.setAttribute("message", message); 
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        }
        
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

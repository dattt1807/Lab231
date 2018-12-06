/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emvh.controller;

import emvh.dao.RegistrationDAO;
import emvh.dto.Role;
import emvh.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    private final String ERROR = "error.jsp";
    private final String ADMIN = "admin.jsp";
    private final String SUBSCRIBER = "subscriber.jsp";
    private final String FAILED = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            RegistrationDAO dao = new RegistrationDAO();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String role = dao.checkLogin(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("USERNAME", username + "[" + role + "]");
            //Get role from db
            //If message return Ad then foward admin page
            // message return not AD then foward sub page
            // message return falied that mean username and password doesn't match
            if (role.equals("Adminitrator")) {
                url = ADMIN;
                //Set some information to display
                List<User> listUser = dao.getAllUser();
                request.setAttribute("listUser", listUser);
                List<Role> listRole = dao.getAllRole();
                request.setAttribute("listRole", listRole);
                request.setAttribute("ROLE", "ALL");
                Map<String, Integer> map = dao.countRole();
                request.setAttribute("map", map);
                request.setAttribute("TOTAL", dao.countAllUser());
            } else if (!role.equals("Adminitrator") && !role.equals("failed")) {
                url = SUBSCRIBER;
            } else {
                request.setAttribute("ERROR", "Invalid username or password!");
                url = FAILED;
            }
        } catch (SQLException e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

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
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class InsertController extends HttpServlet {

    private final String SUCCESS = "admin.jsp";
    private final String FAILED = "insert.jsp";
    private final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("txtUserID");
            String email = request.getParameter("txtEmail");
            String firstName = request.getParameter("txtFirstName");
            String lastName = request.getParameter("txtLastName");
            String password = request.getParameter("txtPassword");
            String notification = request.getParameter("chkNotification") == null ? "0" : "1";
            String role = request.getParameter("slRole");
            User user = new User(userID, firstName, lastName, password, email, role, notification);
            RegistrationDAO dao = new RegistrationDAO();
            String check = dao.addUser(user);
            if (check.equals("success")) {
                url = SUCCESS;
                List<User> listUser = dao.getAllUser();
                request.setAttribute("listUser", listUser);
                List<Role> listRole = dao.getAllRole();
                request.setAttribute("listRole", listRole);
                request.setAttribute("ROLE", "ALL");
                Map<String, Integer> map = dao.countRole();
                request.setAttribute("map", map);
                request.setAttribute("TOTAL", dao.countAllUser());
            } else if (check.equals("Duplicate key")) {
                request.setAttribute("ERROR", "Username has been existed !");
                url = FAILED;
            } else {
                url = FAILED;
            }
        } catch (Exception e) {
            log("ERROR at InsertController: " + e.getMessage());
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

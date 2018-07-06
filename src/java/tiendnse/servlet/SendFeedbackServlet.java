/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiendnse.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tiendnse.StudentDetail.MarkTableDetailDTO;
import tiendnse.StudentDetail.StudentPageDetailDAO;

/**
 *
 * @author Saisam
 */
@WebServlet(name = "SendFeedbackServlet", urlPatterns = {"/SendFeedbackServlet"})
public class SendFeedbackServlet extends HttpServlet {

    private final String feedBackPage = "feedbackForm.jsp";
    private final String sessionOutPage = "login.html";

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
        PrintWriter out = response.getWriter();
        String url = sessionOutPage;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                url = feedBackPage;
                String StudentID = request.getParameter("StudentID");
                List<MarkTableDetailDTO> listSubjectFromFbForm = (List<MarkTableDetailDTO>) session.getAttribute("SUBJECTFEEDBACK");
                String[] feedbackIndex = request.getParameterValues("chkSubjectRemoveFromFBForm");
                System.out.println("New fb");
                for(String item: feedbackIndex ){                
                    StudentPageDetailDAO dao = new StudentPageDetailDAO();
                    boolean result = dao.sendFeedback(listSubjectFromFbForm.get(Integer.parseInt(item)), StudentID);
                    if(result){
                        System.out.println("fb thanh cong");
                    }
                    else {
                        System.out.println("fb fail");
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SendFeedbackServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendFeedbackServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SendFeedbackServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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

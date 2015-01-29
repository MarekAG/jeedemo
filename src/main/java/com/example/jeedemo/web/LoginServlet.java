package com.example.jeedemo.web;

import com.example.jeedemo.domain.User;
import com.example.jeedemo.service.UserManager;
import com.google.common.primitives.Bytes;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserManager um;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String pass = request.getParameter("password");

        String hashedPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());

            byte[] bytes = md.digest();

            StringBuilder temp = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                temp.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hashedPass = temp.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (um.isLoginDataOk(userName, hashedPass)) {
            HttpSession session = request.getSession();
            session.setAttribute("logged", userName);
            response.sendRedirect("../");
        } else {
            response.sendRedirect("/login");
        }

    }
}

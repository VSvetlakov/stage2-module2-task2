package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || login.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (password == null || password.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        List<String> users = Users.getInstance().getUsers();

        if (!users.contains(login)) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user",login);

        resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String user = (String) session.getAttribute("user");

        if (user == null || user.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }else {
            resp.sendRedirect(req.getContextPath() + "/user/hello.jsp");
        }
    }
}

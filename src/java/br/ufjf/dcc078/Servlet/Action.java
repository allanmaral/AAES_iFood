package br.ufjf.dcc078.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException;
}

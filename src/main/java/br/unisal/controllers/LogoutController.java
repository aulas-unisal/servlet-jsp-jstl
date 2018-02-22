package br.unisal.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import br.unisal.util.Constantes;

@WebServlet(name = "LogoutController", description = "Controlador para efetivar o logout", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7910097348305863827L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.removeAttribute("usuario.uuid");
		session.invalidate();

		req.getRequestDispatcher(Constantes.raiz + "index.jsp").forward(req, resp);
	}
}

package br.unisal.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import br.unisal.util.Constantes;

@WebServlet(name = "LogoutController", description = "Controlador para efetivar o logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7910097348305863827L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		HttpSession session = req.getSession();
		session.removeAttribute("usuario.uuid");
		session.invalidate();

		RequestDispatcher dispatcher = req.getRequestDispatcher(Constantes.raiz + "index.jsp");
		dispatcher.forward(req, resp);
	}
}

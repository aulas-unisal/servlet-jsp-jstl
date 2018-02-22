package br.unisal.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unisal.dao.UsuarioDAO;
import br.unisal.model.Usuario;
import br.unisal.util.Constantes;

@WebServlet(name = "UsuarioListController", description = "Controlador para listar usuários", urlPatterns = {
		"/listaUsuarios" })
public class UsuarioListController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2380365497770693025L;

	private static final UsuarioDAO USUARIO_DAO = UsuarioDAO.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
		List<Usuario> usuarios = new ArrayList<>();
		
		try {			
			usuarios = USUARIO_DAO.findAll();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.toString());
		}

		req.setAttribute("usuarios", usuarios);
		RequestDispatcher dispatcher = req.getRequestDispatcher(Constantes.raiz + "inicio.jsp");
		dispatcher.forward(req, resp);
	}

}

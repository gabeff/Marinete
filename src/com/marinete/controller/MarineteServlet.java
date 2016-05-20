package com.marinete.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.marinete.bean.Usuario;
import com.marinete.business.UsuarioBusiness;

public class MarineteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");

		try {

			if (acao.equals("login")) {

				Usuario usuario = new Usuario();
				usuario.setNome(request.getParameter("login"));
				usuario.setSenha(request.getParameter("senha"));

				UsuarioBusiness uBusiness = new UsuarioBusiness();

				String logado = uBusiness.logar(usuario);
				HttpSession session = request.getSession(true);

				if (logado.equals("0")) {
					session.setAttribute("currentSessionUser", usuario);
					session.removeAttribute("invalidLogin");
					response.sendRedirect("/Marinete/webservice/marinete/listar");
				}
				else {
					session.invalidate();
					response.sendRedirect("home.jsp");
				}

			}

			if (acao.equals("logout")) {
				HttpSession session = request.getSession(true);
				if (session.getAttribute("currentSessionUser") != null) {
					session.removeAttribute("currentSessionUser");
					session.invalidate();
				}
				response.sendRedirect("home.jsp");
			}

			if (acao.equals("paginaprincipal")) {
				response.sendRedirect("home.jsp");
			}

			// if (acao.equals("enviarContato")) {
			// boolean retorno = true;
			// Contato contato = new Contato();
			// contato.setEmail(request.getParameter("email"));
			// contato.setNome(request.getParameter("nome"));
			// contato.setMensagem(request.getParameter("mensagem"));
			//
			// ContatoDAO cdao = new ContatoDAO();
			// retorno = cdao.enviarContato(contato);
			//
			// if (retorno) {
			// response.sendRedirect("home.jsp");
			// } else {
			// response.sendRedirect("home.jsp");
			// }
			// }
		} catch (

		Throwable theException) {
			System.out.println(theException);
		}
	}
}

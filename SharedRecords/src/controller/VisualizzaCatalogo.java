package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StanzaDao;
import dao.UtenteDao;


public class VisualizzaCatalogo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	UtenteDao utenti;
	StanzaDao stanze;
	
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		utenti = (UtenteDao)this.getServletContext().getAttribute("utenti");
		stanze = (StanzaDao)this.getServletContext().getAttribute("stanze");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String request = req.getParameter("azione");
		RequestDispatcher dispUtente = getServletContext().getRequestDispatcher("/pages/homeStanza.jsp");
		RequestDispatcher dispAmministratore = getServletContext().getRequestDispatcher("/pages/homeAmministratore.jsp");
		RequestDispatcher dispDash = getServletContext().getRequestDispatcher("/pages/dashboard.jsp");
		
		if(request!=null && request.equals("accedi")) {
			String username = (String)req.getSession().getAttribute("utente");
			String nomeStanza = req.getParameter("nomeStanza");
			if(stanze.getStanza(nomeStanza).getUsernameAmministratore().equals(username)) {
				req.setAttribute("stanza", stanze.getStanza(nomeStanza));
				req.getSession().setAttribute("stanza",stanze.getStanza(nomeStanza).getNome());
				dispAmministratore.forward(req, resp);
			}
			else {
				req.setAttribute("stanza", stanze.getStanza(nomeStanza));
				req.getSession().setAttribute("stanza",stanze.getStanza(nomeStanza).getNome());
				if( !(stanze.getStanza(nomeStanza).getInventario(username)==null) ) {
					dispUtente.forward(req, resp);
				}
				else {
					req.setAttribute("risultato", "non sei iscritto");
					dispDash.forward(req, resp);
				}
			}
		}
		else if(request!=null && request.equals("esci")) {
			dispDash.forward(req, resp);
		}
	}
}

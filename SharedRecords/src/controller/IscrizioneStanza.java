package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StanzaDao;
import dao.UtenteDao;
import model.Inventario;

public class IscrizioneStanza extends HttpServlet{
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
		RequestDispatcher dispDash = getServletContext().getRequestDispatcher("/pages/dashboard.jsp");

		if(request!=null && request.equals("iscriviti")) {
			String nomeStanza = req.getParameter("nomeStanza");
			String username = (String)req.getSession().getAttribute("utente");
			Inventario nuovo = new Inventario(username);
			
			if( (stanze.getStanza(nomeStanza).getInventario(username)==null) ) {
				stanze.getStanza(nomeStanza).aggiungiInventario(nuovo);
				req.setAttribute("nomeStanza", nomeStanza);
				req.getSession().setAttribute("stanza",nomeStanza);
				dispUtente.forward(req, resp);
			}
			else {
				req.setAttribute("risultato", "sei già iscritto");
				dispDash.forward(req, resp);
			}
		}
			
	}
}

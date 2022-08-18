package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StanzaDao;
import dao.UtenteDao;
import model.Conversazione;
import model.Inventario;
import model.Messaggio;
import model.Stanza;
import model.Utente;

public class ConversazioniGruppo extends HttpServlet{
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
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/pages/viewMessaggiDiGruppo.jsp");
		RequestDispatcher dispInd = getServletContext().getRequestDispatcher("/pages/homeStanza.jsp");
		RequestDispatcher dispAmm = getServletContext().getRequestDispatcher("/pages/homeAmministratore.jsp");
		
		String utente = (String)req.getSession().getAttribute("utente");
		Utente u = null;
		for(Utente ut : utenti.getUtenti()) {
			if(utente.equals(ut.getUsername())) {
				u = ut;
			}
		}
	String nomestanza = (String)req.getSession().getAttribute("stanza");
		Stanza stanza = stanze.getStanza(nomestanza);
		
		if(request!=null && request.equals("invia")) {
			String text = req.getParameter("text");
			int id = Integer.parseInt(req.getParameter("id"));
			Messaggio m = new Messaggio(text, LocalDateTime.now(), utente);
			Conversazione c = stanza.getConversazione();
				if(c.getId()==id) {
					c.invioMessaggio(m);
				}
			disp.forward(req, resp);
		}
		
		else if(request!=null && request.equals("esci")) {
			req.setAttribute("stanza", stanza);
			if(req.getSession().getAttribute("utente").equals(stanza.getUsernameAmministratore())) {
				dispAmm.forward(req, resp);
			}
			else {
				dispInd.forward(req, resp);
			}
		}
		
	}
}

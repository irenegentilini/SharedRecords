package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
import model.Prestito;
import model.Stanza;
import model.Titolo;
import model.Utente;

public class GestioneUtenti extends HttpServlet{
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
		RequestDispatcher disp = getServletContext().getRequestDispatcher("/pages/homeGestioneUtenti.jsp");
		RequestDispatcher dispNomina = getServletContext().getRequestDispatcher("/pages/homeStanza.jsp");
		
		String utente = req.getParameter("utente");
		String nomestanza = (String)req.getSession().getAttribute("stanza");
		Stanza stanza = stanze.getStanza(nomestanza);
		
		if(request!=null && request.equals("rimuovi")) {
			Inventario i=null;
			for(Inventario temp : stanza.getInventari().getInventari()) {
				if(temp.getUtente().equals(utente)) i=temp;
			}
			if (i!=null)
				stanza.getInventari().getInventari().remove(i);
			disp.forward(req, resp);
		}
		else if(request!=null && request.equals("nomina")) {
			stanza.setUsernameAmministratore(utente);
			req.setAttribute("stanza", stanza);
			dispNomina.forward(req, resp);
		}
		
	}
}

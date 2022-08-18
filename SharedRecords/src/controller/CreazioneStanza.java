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
import model.Inventario;
import model.Stanza;


public class CreazioneStanza extends HttpServlet{
	
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
		RequestDispatcher dispCorretto = getServletContext().getRequestDispatcher("/pages/viewCreaStanza.jsp");
		RequestDispatcher dispIndietro = getServletContext().getRequestDispatcher("/pages/dashboard.jsp");
		RequestDispatcher dispCreazione = getServletContext().getRequestDispatcher("/pages/homeAmministratore.jsp");

		if(request!=null && request.equals("crea stanza")) {
			String username = req.getParameter("utente");
			req.setAttribute("utente", username);
			List<String> tagList = new ArrayList<String>();
			req.getSession().setAttribute("tags", tagList);
			dispCorretto.forward(req, resp);
		}
		else if(request!=null && request.equals("conferma")) {
			String nomeStanza = req.getParameter("nome");
			String amministratore = (String)req.getSession().getAttribute("utente");
			List<String> tags = (List<String>)req.getSession().getAttribute("tags");
			Stanza nuovaStanza = new Stanza(nomeStanza, amministratore);
			nuovaStanza.setTag(tags);
			nuovaStanza.aggiungiInventario(new Inventario(amministratore));
			stanze.aggiungiStanza(nuovaStanza);
			req.setAttribute("stanza", nuovaStanza);
			req.getSession().setAttribute("stanza",nuovaStanza.getNome());
			//req.setAttribute("stanza", stanze.getStanza(nomeStanza));
			dispCreazione.forward(req, resp);
		}
		else if(request!=null && request.equals("indietro")) {
			dispIndietro.forward(req, resp);
		}
	}
	
}

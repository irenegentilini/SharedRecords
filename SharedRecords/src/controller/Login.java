package controller;

import java.io.IOException;
import java.util.Date;

import dao.StanzaDao;
import dao.TitoloDao;
import dao.UtenteDao;
import model.Inventario;
import model.Stanza;
import model.Titolo;
import model.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UtenteDao utenti;
	StanzaDao stanze;
	TitoloDao titoli;
	
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		utenti = (UtenteDao)this.getServletContext().getAttribute("utenti");
		stanze = (StanzaDao)this.getServletContext().getAttribute("stanze");
		titoli = (TitoloDao)this.getServletContext().getAttribute("titoli");
		
		Utente u = new Utente("irene", "irene");
		utenti.aggiungiUtente(u);
		
		u= new Utente("alessandro", "alessandro");
		utenti.aggiungiUtente(u);
		
		Stanza s = new Stanza("Rock classico", "irene");
		s.getTag().add("rock");
		s.getTag().add("musica");
		s.aggiungiInventario(new Inventario("irene"));
		stanze.aggiungiStanza(s);
		
		Titolo t = new Titolo("The Wall", "2021/7/31");
		s.getInventario("irene").aggiungiTitolo(t);
		t = new Titolo("Toxicity", "2021/8/5");
		s.getInventario("irene").aggiungiTitolo(t);
		
		s= new Stanza("Film italiani", "alessandro");
		s.getTag().add("cinema");
		s.aggiungiInventario(new Inventario("alessandro"));
		stanze.aggiungiStanza(s);
		
		t = new Titolo("Lo Chiamavano Trinità", "2021/8/7");
		s.getInventario("alessandro").aggiungiTitolo(t);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String request = req.getParameter("reg");
		RequestDispatcher dispRegistrazione = getServletContext().getRequestDispatcher("/pages/viewRegistrazione.jsp");
		RequestDispatcher dispCorretto = getServletContext().getRequestDispatcher("/pages/dashboard.jsp");
		RequestDispatcher dispScorretto = getServletContext().getRequestDispatcher("/pages/viewLogin.jsp");
		
		
		if(request!=null && request.equals("accedi")) {
			String username = req.getParameter("user");
			String password = req.getParameter("password");
			
			if(username.isBlank() || password.isBlank()) {
				req.setAttribute("risultato", "inserire tutti i parametri richiesti");
				dispScorretto.forward(req, resp);
			}
			else if(utenti.verificaDatiUtente(username)) {
				req.setAttribute("utente", username);
				dispCorretto.forward(req, resp);
				req.getSession().setAttribute("utente", username);
			}
			else {
				req.setAttribute("risultato", "l'utente non esiste");
				dispScorretto.forward(req, resp);
			}
			
		}
		
		if(request!=null && request.equals("registrati")) {
			dispRegistrazione.forward(req, resp);
		}
		
		if (request!= null && request.equals("logout"))
		{
			req.getSession().invalidate();
			dispScorretto.forward(req,resp);
		}
	}
}

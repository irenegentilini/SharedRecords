package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UtenteDao;
import model.Utente;

public class RegistrazioneUtente extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	UtenteDao utenti;
	
	public void init(ServletConfig conf) throws ServletException
	{
		super.init(conf);
		utenti = (UtenteDao)this.getServletContext().getAttribute("utenti");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String request = req.getParameter("reg");
		RequestDispatcher dispCorretto = getServletContext().getRequestDispatcher("/pages/viewLogin.jsp");
		RequestDispatcher dispScorretto = getServletContext().getRequestDispatcher("/pages/viewRegistrazione.jsp");
		
		if(request!=null && request.equals("registrati")) {
			String username = req.getParameter("user");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if(username.isBlank() || email.isBlank() || password.isBlank()) {
				req.setAttribute("risultato", "inserire tutti i parametri richiesti");
				dispScorretto.forward(req, resp);
			}
			else if(!utenti.verificaDatiUtente(username)) {
				Utente u = new Utente(username, email);
				utenti.aggiungiUtente(u);
				dispCorretto.forward(req, resp);
			}
			else {
				req.setAttribute("risultato", "l'utente è già registrato");
				dispScorretto.forward(req, resp);
			}
		}
	}
}

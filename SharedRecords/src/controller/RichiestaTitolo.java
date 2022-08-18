package controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
import model.Conversazione;
import model.Inventario;
import model.Prestito;
import model.Stanza;
import model.Titolo;
import model.Utente;


public class RichiestaTitolo extends HttpServlet{
	
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
		RequestDispatcher dispRichiesta = getServletContext().getRequestDispatcher("/pages/viewRichieste.jsp");
		
		if(request!=null && request.equals("richiedi")) {
			int idTitolo = Integer.parseInt(req.getParameter("titolo"));
			String nomeUtente = (String)req.getSession().getAttribute("utente");
			String nomeStanza = (String)req.getParameter("nomeStanza");
			
			Stanza stanza = stanze.getStanza(nomeStanza);
			Titolo titolo = stanza.getTitolo(idTitolo);
			List<Titolo> catalogo=stanza.getTitoli();
			List<Inventario> inventari=stanza.getInventari().getInventari();
				for (Inventario i: inventari)
				{
					for(Titolo titInv : i.getTitoli().getTitoli())
					{
						if (idTitolo==titInv.getId() && nomeUtente.equals(i.getUtente()))
						{
							if(stanza.getUsernameAmministratore().equals(nomeUtente)) {
								req.setAttribute("stanza", stanze.getStanza(nomeStanza));
								req.setAttribute("risultato", "il titolo è già tuo!");
								dispAmministratore.forward(req, resp);
							}
							else {
								req.setAttribute("stanza", stanze.getStanza(nomeStanza));
								req.setAttribute("risultato", "il titolo è già tuo!");
								dispUtente.forward(req, resp);
							}
						}
					}
				}
			if(titolo.getStatoDisponibilita().equals("DISPONIBILE")) {
				Prestito nuovoPrestito = new Prestito(titolo.getDataRiconsegna(), LocalDateTime.now(), nomeUtente, idTitolo);
				stanza.listaPrestiti().add(nuovoPrestito);
				titolo.setStatoDisponibilita("RICHIESTO");
				req.setAttribute("risultato", "la richiesta è avvenuta con successo");
			}
			else {
				req.setAttribute("risultato", "il titolo non può essere richiesto");
			}
			req.getSession().setAttribute("stanza",stanza.getNome());
			req.setAttribute("stanza", stanza.getNome());
			if(stanza.getUsernameAmministratore().equals(nomeUtente)) {
				req.setAttribute("stanza", stanze.getStanza(nomeStanza));
				dispAmministratore.forward(req, resp);
			}
			else {
				req.setAttribute("stanza", stanze.getStanza(nomeStanza));
				dispUtente.forward(req, resp);
			}
		}
		else if(request!=null && request.equals("accetta")) {
			int idTitolo = Integer.parseInt(req.getParameter("titolo"));
			String nomeUtente = (String)req.getSession().getAttribute("utente");
			String nomeStanza = (String)req.getParameter("nomeStanza");
			
			Stanza stanza = stanze.getStanza(nomeStanza);
			
			List<Prestito> richieste = stanza.getRichieste(nomeUtente);
			for(Prestito p : richieste) {
				Conversazione nuovaConv=null;
				if(p.getTitolo()==idTitolo) 
				{
					p.accettaRichiesta();
					nuovaConv = new Conversazione(nomeStanza);
					Titolo inPrestito=stanze.getStanza(nomeStanza).getTitolo(p.getTitolo());
					inPrestito.setStatoDisponibilita("INPRESTITO");
					inPrestito.incrementaPrestiti();
				}
				for(Utente u : utenti.getUtenti()) {
					if(u.getUsername().equals(nomeUtente) || u.getUsername().equals(p.getUtente())) {
						List<Conversazione> lista = u.getConversazioni().getConversazioni();
						if (nuovaConv != null) lista.add(nuovaConv);
						u.getConversazioni().setConversazioni(lista);
					}
				}
			}
			
			req.setAttribute("risultato", "richiesta accettata, ora puoi mandare messaggi al richiedente");
			req.setAttribute("stanza", stanze.getStanza(nomeStanza).getNome());
			dispRichiesta.forward(req, resp);
		}
		else if(request!=null && request.equals("rifiuta")) 
		{
			int idTitolo = Integer.parseInt(req.getParameter("titolo"));
			String nomeUtente = (String)req.getSession().getAttribute("utente");
			String nomeStanza = (String)req.getParameter("nomeStanza");
			
			Stanza stanza = stanze.getStanza(nomeStanza);
			
			List<Prestito> richieste = stanza.getRichieste(nomeUtente);
			for(Prestito p : richieste) 
			{
				if(p.getTitolo()==idTitolo) 
				{
						stanza.concludiPrestito(p);
						stanze.getStanza(nomeStanza).getTitolo(idTitolo).setStatoDisponibilita("DISPONIBILE");
				}
			}
		
			req.setAttribute("risultato", "richiesta rifiutata");
			req.getSession().setAttribute("stanza",nomeStanza);
			req.setAttribute("stanza",stanze.getStanza(nomeStanza));
			dispRichiesta.forward(req, resp);
		}
	}
	
}


package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.FactoryDao;
import dao.StanzaDao;
import dao.TitoloDao;
import dao.UtenteDao;
import model.Inventario;
import model.Prestito;
import model.Stanza;
import model.Titolo;




	
	public class GestioneInventario extends HttpServlet{
		
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
			
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			String username=(String) req.getSession().getAttribute("utente");
			String requestA = req.getParameter("azione");
			String requestM =req.getParameter("modifica");
			RequestDispatcher d=req.getRequestDispatcher("/pages/viewInventario.jsp");
			if(requestA!=null && requestA.equals("conferma")) {
				// titolo, data e stato
				String nomeTitolo = req.getParameter("titolo");
				String data = req.getParameter("data");
				data = data.replace('-', '/');
				String stato = req.getParameter("stato");
				String nomestanza= req.getParameter("nomestanza");
				Titolo nuovoTitolo=new Titolo(nomeTitolo,data);
				if (stato.equals("nonDisponibile")) {
					nuovoTitolo.setStatoDisponibilita("NONDISPONIBILE");}
				else {
					nuovoTitolo.setStatoDisponibilita("DISPONIBILE");}
				Stanza corr=stanze.getStanza(nomestanza);
				for (Inventario i: corr.getInventari().getInventari())
				{
					if (i.getUtente().equals(username))
					{
						i.aggiungiTitolo(nuovoTitolo);
						req.getSession().setAttribute("titoliInv",i);
					}
				}
			req.setAttribute("nomestanza",nomestanza);
			}
			else if(requestA!=null && requestA.equals("elimina")) {
				String nomestanza = (String)req.getSession().getAttribute("stanza");
				Stanza stanza = stanze.getStanza(nomestanza);
				String nomeutente = (String)req.getSession().getAttribute("utente");
				int idTitolo = Integer.parseInt(req.getParameter("titoloId"));
				Titolo t=null;
				for(Titolo temp : stanza.getInventario(nomeutente).getTitoli().getTitoli()) {
					if(temp.getId()==idTitolo) t=temp;
				}
				if (t!=null ) stanza.getInventario(nomeutente).rimuoviTitolo(t);
			}
			if (requestM!=null && requestM.equals("conferma"))
			{
				String nomeTitolo = req.getParameter("titolo");
				String dataModificata = req.getParameter("data");
					dataModificata = dataModificata.replace('-', '/');
				String statoModificato = req.getParameter("stato");
				String nomeStanza = req.getParameter("nomestanza");
			    for (Inventario i :stanze.getStanza(nomeStanza).getInventari().getInventari())
			    {
			    	if (i.getUtente().equals(username))
			    	{
			    		for (Titolo t: i.getTitoli().getTitoli())
			    		{
			    			if (String.valueOf(t.getId()).equals(nomeTitolo))
			    			{
			    				t.setDataRiconsegna(dataModificata);
			    				if (statoModificato.equals("nonDisponibile"))
			    					t.setStatoDisponibilita("NONDISPONIBILE");
			    				else
			    				{
			    					if (t.getStatoDisponibilita().equals("INPRESTITO"))
			    					{
			    						Prestito p=null;
			    						for (Prestito temp: stanze.getStanza(nomeStanza).getPrestiti().getPrestiti()) {
			    							if (temp.getTitolo() == t.getId())
			    								p=temp;
			    						}
			    						if (p!=null)
			    							stanze.getStanza(nomeStanza).getPrestiti().getPrestiti().remove(p);
			    							
			    					}
			    					t.setStatoDisponibilita("DISPONIBILE");
			    				}
			    			}
			    		}
			    		
			    	}
			    }
			}
			d.forward(req,resp);
		}
			
		
		
		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
			if (!req.getParameter("carica").equals(null))
			{
				RequestDispatcher d=req.getRequestDispatcher("/pages/viewInventario.jsp");
				String corr=(String)req.getSession().getAttribute("stanza");
				Stanza corrente=stanze.getStanza(corr);
				String username=(String) req.getSession().getAttribute("utente");
				List<Inventario> inv =corrente.getInventari().getInventari();
				if (inv !=null )
					for (Inventario i: inv)
					{
						String userprova = i.getUtente();
						if (i.getUtente().equals(username))
						{
							req.getSession().setAttribute("titoliInv",i);
						}
					}
				d.forward(req,resp);
			}
		}
		
	}




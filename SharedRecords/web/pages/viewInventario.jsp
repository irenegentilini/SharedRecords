<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Titolo"%>
<%@ page import="model.Prestito"%>
<%@ page import="model.Inventario"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/homeGestioneInventarioStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>
<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
<body>
<div class="title">
 <h1>Shared Records</h1>
 <% 
 String nomestanza = (String)session.getAttribute("stanza");
 Stanza s=stanze.getStanza(nomestanza);
 String utente=(String)session.getAttribute("utente");
 %>
 
 <h2 style="margin-left:80px;"><%= nomestanza %></h2>
 <ul style="width:580px;">
 <li><% if(utente.equals(s.getUsernameAmministratore())){ %>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= s.getNome() %>">Home</a></li>
		<%}
		else{%>
			<li><a href="/SharedRecords/pages/homeStanza.jsp?nomeStanza=<%= s.getNome() %>" >Home</a></li>
		<%} %></li>
			<li><a href="/SharedRecords/pages/viewConversazioni.jsp">Conversazioni</a></li>
			<li><a href="/SharedRecords/pages/viewRichieste.jsp?nomeStanza=<%= nomestanza %>">Richieste</a></li>
			<li><a href="/SharedRecords/inventario?carica=ok" style="color: #f00000">Inventario</a></li>
			<li><a href="/SharedRecords/pages/viewMessaggiDiGruppo.jsp?id=<%= s.getConversazione().getId() %>">Conversazione di
					gruppo</a></li>
 </ul>
 
 

 <!-- <label>cerca titoli:</label>
 <input type="text">
 <input type="submit" value="cerca" id="reg">

 <h2>Titoli</h2> -->
  <table>
   <tr>
    <th>Titolo</th>
    <th>Disponibilità</th>
    <th>Punteggio</th>
    <th></th>
    <th></th>
   </tr>
  <%
					
					//Inventario invUtente=(Inventario)request.getSession().getAttribute("titoliInv");
   					//if (invUtente!=null)
					//for( Titolo t : invUtente.getTitoli().getTitoli()){ 
						
						Stanza stanza = stanze.getStanza((String)session.getAttribute("stanza"));
						Inventario i = stanza.getInventario((String)session.getAttribute("utente"));
						for(Titolo t : i.getTitoli().getTitoli()){
					%> 
							<tr>
								<td><%= t.getNome() %></td>
								<td><%= t.getStatoDisponibilita() %></td>
								<% String statoInt=String.valueOf(t.getStatoDintegrita()); if(statoInt.equals("-1")) statoInt="da valutare";   %>
								<td><%= statoInt %></td>
							<td>
								<form method=post action="/SharedRecords/pages/viewModifica.jsp">
									<input type=hidden name=titolo value="<%=t.getId()%>" />
									<input type=submit id="tabreg" name=azione value="modifica"/>
								</form>
							</td>
							<td>
								<form method=post action="/SharedRecords/inventario">
									<input type=hidden name=titoloId value="<%= t.getId() %>" />
									<input type=submit id="tabreg" name=azione value="elimina"/>
								</form>
							</td>
							</tr>
							
						
						
					<% } %>
  </table>
 <br><br>
 <form action="/SharedRecords/pages/homeInserimentoDati.jsp?nomeStanza=<%= nomestanza %>" method=post>
 <input type="submit" value="aggiungi" id="reg" style="margin-left:450px;width:100px;">

</form>
</div>

</body>
</html>
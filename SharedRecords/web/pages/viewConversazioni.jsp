<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Titolo"%>
<%@ page import="model.Prestito"%>
<%@ page import="model.Utente"%>
<%@ page import="model.Conversazione"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/viewConversazioniStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>

<body>

<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
<jsp:useBean id="utenti" class="dao.UtenteDao" scope="application" />
<%
String nomestanza = (String)session.getAttribute("stanza");
Stanza s=stanze.getStanza(nomestanza);
String utente=(String)session.getAttribute("utente");
%>
<div class="title">
	<h1>Shared Records</h1>
	<h2 style="margin-left:80px;"><%= nomestanza %></h2>
	<ul style="width:580px;">
		<li><% if(utente.equals(s.getUsernameAmministratore())){ %>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= s.getNome() %>">Home</a></li>
		<%}
		else{%>
			<li><a href="/SharedRecords/pages/homeStanza.jsp?nomeStanza=<%= s.getNome() %>" >Home</a></li>
		<%} %></li>
			<li><a href="/SharedRecords/pages/viewMessaggi.jsp" style="color: #f00000">Conversazioni</a></li>
			<li><a href="/SharedRecords/pages/viewRichieste.jsp?nomeStanza=<%= nomestanza %>">Richieste</a></li>
			<li><a href="/SharedRecords/inventario?carica=ok">Inventario</a></li>
			<li><a href="/SharedRecords/pages/viewMessaggiDiGruppo.jsp?id=<%= s.getConversazione().getId() %>">Conversazione di
					gruppo</a></li>
	</ul>
	


	<!-- <label>cerca titoli:</label>
	<input type="text">
	<input type="submit" value="cerca" id="reg">

	<h2>Titoli</h2> -->
		<table class="formdata" id="myTable">
					<tr>
						<th>Utente</th>
						<th></th>
					</tr>
					
					
					<%
						for(Utente u : utenti.getUtenti()){
							if( u.getUsername().equals(utente)){
								for(Conversazione c : u.getConversazioni().getConversazioni()){
									for(Utente ut : utenti.getUtenti()){
										if(s.getInventario(ut.getUsername())!=null && !u.getUsername().equals(ut.getUsername())){
										//è nella stanza, non è l'utente in sessione	
					%> 
						
							<tr>
								<td><%= ut.getUsername() %></td>
							<form name="iscrizione" id="myform" action="/SharedRecords/pages/viewMessaggi.jsp?id=<%= c.getId() %>" method="post">
								<td>
									<input type="hidden" name="nomeStanza" value="<%= c.getId() %>"/>
									<input type="submit" id="tabreg" name="azione" value="apri"/>
								</td>
							</form>
							</tr>
						
					<% }}}}} %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
	<br><br>


</div>

</body>
</html>
<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Titolo"%>
<%@ page import="model.Prestito"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/viewRichiesteStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>

<body>
	<%
	 String nomestanza = (String)session.getAttribute("stanza");
	%>
	 <div class="title">
	<h1>Shared Records</h1>
	<h2 style="margin-left:80px;"><%= nomestanza %></h2>
	
	<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
	<% 
        String utente = (String)session.getAttribute("utente");
	 	String nome = (String)session.getAttribute("stanza");
		Stanza s=stanze.getStanza(nome);
	 %>
	
		<ul style="width: 580px;">
		<% if(utente.equals(s.getUsernameAmministratore())){ %>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= s.getNome() %>">Home</a></li>
		<%}
		else{%>
			<li><a href="/SharedRecords/pages/homeStanza.jsp?nomeStanza=<%= s.getNome() %>" >Home</a></li>
		<%} %>
			<li><a href="/SharedRecords/pages/viewConversazioni.jsp">Conversazioni</a></li>
			<li><a href="/SharedRecords/pages/viewRichieste.jsp" style="color: #f00000">Richieste</a></li>
			<li><a href="/SharedRecords/inventario?carica=ok">Inventario</a></li>
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
				<th>Utente</th>
				<th>Data</th>
				<th></th>
				<th></th>
			</tr>
			
			<%	
					List<Prestito> prestiti = s.getRichieste((String)session.getAttribute("utente"));
					for( Prestito p : prestiti ){  
						Titolo t = s.getTitolo(p.getTitolo());
			%>
			<tr>
				<td><%= t.getNome() %></td>
				<td><%= p.getUtente() %></td>
				<td><%= p.getDataRiconsegna().toString() %></td>
				<td>
					<form  name="catalogo" id="myform" action="/SharedRecords/richiestaTitolo" method="post">
						<input type="hidden" name="titolo" value="<%= t.getId() %>"/>
						<input type="hidden" name="nomeStanza" value="<%= s.getNome() %>"/>
						<input type="submit" id="tabreg" name="azione" value="accetta"/>
					</form>
				</td>
				<td>
					<form  name="catalogo" id="myform" action="/SharedRecords/richiestaTitolo" method="post">
						<input type="hidden" name="titolo" value="<%= t.getId() %>"/>						
						<input type="hidden" name="nomeStanza" value="<%= s.getNome() %>"/>
						<input type="submit" id="tabreg" name="azione" value="rifiuta"/>
					</form>
				</td>
			</tr>
			
			<% } %>
			<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
			</tr>
			
		</table>
	<br><br>
	
	<div id="result"></div>
		
	    <% 
        String risultato = (String)request.getAttribute("risultato");
		if( risultato != null ){
			// mostro il risultato della ricerca appena effettuata
			%>
    			<%= risultato %><br />
    		<%
		}
		%>


</div>

</body>
</html>
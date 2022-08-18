<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Inventario"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
<head>
<meta name="Author" content="cg">
<link type="text/css" href="/SharedRecords/styles/homeGestioneUtentiStyle.css"
	rel="stylesheet"></link>
<title>SharedRecords</title>

</head>

<body>
<div class="title">
	<h1>Shared Records</h1>
	<% String nomestanza = (String)session.getAttribute("stanza"); %>
	<h2 style="margin-left:80px;"><%= nomestanza %></h2>
	<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
	

	<ul>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= nomestanza %>" >Home</a></li>
			<li><a href="/SharedRecords/pages/homeGestioneUtenti.jsp" style="color: #f00000">Utenti</a></li>
			<li><a href="/SharedRecords/pages/viewGestioneTitoli.jsp">Titoli</a></li>
			<li><a href="/SharedRecords/pages/viewPrestiti.jsp">Prestiti</a></li>
	</ul>
	

	<!-- <label>cerca titoli:</label>
	<input type="text">
	<input type="submit" value="cerca" id="reg"> -->

	<h2>Utenti</h2>
	
	
		<table>
			<tr>
				<th>Username</th>
				<th></th>
				<th></th>
			</tr>
			

			<%	
					Stanza stanza = stanze.getStanza(nomestanza);
					for( Inventario i : stanza.getInventari().getInventari() ){  
						if(!i.getUtente().equals(stanza.getUsernameAmministratore())){
			%>
			<tr>
				<td><%= i.getUtente() %></td>
				<td>
					<form  name="catalogo" id="myform" action="/SharedRecords/gestioneUtenti" method="post">
						<input type="hidden" name="utente" value="<%= i.getUtente() %>"/>
						<input type="submit" id="tabreg" name="azione" value="rimuovi"/>
					</form>
				</td>
				<td>
					<form  name="catalogo" id="myform" action="/SharedRecords/gestioneUtenti" method="post">
						<input type="hidden" name="utente" value="<%= i.getUtente() %>"/>
						<input type="submit" id="tabreg" name="azione" value="nomina"/>
					</form>
				</td>
			</tr>
			
			<% }
			} %>
			<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					
			</tr>
			
			
		</table>
	<br><br>

</div>

</body>
</html>
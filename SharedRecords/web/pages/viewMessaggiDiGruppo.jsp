<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Utente"%>
<%@ page import="model.Titolo"%>
<%@ page import="model.Prestito"%>
<%@ page import="model.Messaggio"%>
<%@ page import="model.Inventario"%>
<%@ page import="model.Conversazione"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/viewMessaggiStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>

<body>

<jsp:useBean id="utenti" class="dao.UtenteDao" scope="application" />
<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />

<div class="title">
	<h1>Shared Records</h1>
	<h2 style="margin-left:60px;"><%= (String)session.getAttribute("stanza") %></h2>
	
	<h2 style="margin-left:60px;"></h2>
	
<div style="padding:20px;margin:0px;width: 600px; height: 450px; overflow-y: scroll;">

<%

	String classDiv = "container";
	String colorP = "#ff0000";
	String username = (String)session.getAttribute("utente");
	
	Stanza s = stanze.getStanza((String)session.getAttribute("stanza"));
	Conversazione c = s.getConversazione();
		
				for(Messaggio m : c.caricaConversazione()){
					if(m.getMittente().equals(username)){
						classDiv="container darker";
						colorP = "#b00000";
					}
					else{
						classDiv = "container";
						colorP = "#ff0000";
					}
%>

<div class="<%= classDiv  %>">
 <p style="color:<%= classDiv  %>"><%= m.getMittente() %></p>
 <p><%= m.getContenuto() %><p>
 <span class="time-right"><%= m.getDataOra() %></span>
</div>

<%
	}
%>


</div>

<form name="formdata" id="myform" action="/SharedRecords/conversazioniGruppo" method="post">
	<input type="hidden" name="id" value="<%= Integer.parseInt(request.getParameter("id")) %>" style="margin:20px; width:570px; height:25px;">
	<input type="text" name="text" value="" style="margin:20px; width:570px; height:25px;">
	<input type="submit" name="azione" value="invia" id="reg"><br><br>
</form>



	
	
	

</div>



<form name="formdata" id="myform" action="/SharedRecords/conversazioniGruppo" method="post">
<input type="submit" name="azione" value="esci" id="reg" style="margin-left:630px; margin-bottom:30px; margin-top:10px;">
</form>

</body>
</html>
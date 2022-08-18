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
		<link type="text/css" href="/SharedRecords/styles/viewModificaStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>

<body>
<div>
	<h1>Shared Records</h1>
	<h2>Modifica Titolo</h2>
	<%
	String titolo=(String)request.getParameter("titolo");
	 String nomestanza = (String)session.getAttribute("stanza");
	 String prova="ciao";
	%>
	<form method=post action="/SharedRecords/inventario?nomestanza=<%= nomestanza  %>&titolo=<%= titolo %>">

	<label>Data di riconsegna</label><br>
	<input name=data type="date"><br><br>
	
	<label>Stato di disponibilità</label>
		<select name="stato" id="stato">
			<option value="disponibile">Disponibile</option>
			<option value="nonDisponibile">Non Disponibile</option>
	</select><br><br>
	<input type="hidden" name="titolo" value=<%= titolo %>/>
	<input type="submit" name=modifica value="indietro" id="reg">
	<input type="submit" name=modifica value="conferma" id="reg" style="width:90px;">

</form>
</div>

</body>
</html>
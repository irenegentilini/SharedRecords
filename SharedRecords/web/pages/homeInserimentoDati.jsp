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
		<link type="text/css" href="/SharedRecords/styles/viewInserimentoDatiStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
</head>

<body>
<div>
	<h1>Shared Records</h1>
	<h2>Nuovo Titolo</h2>
	<%
	 String nomestanza=(String)session.getAttribute("stanza");
	%>
	<form method=post action="/SharedRecords/inventario?nomestanza=<%= nomestanza  %>">

	<label>titolo</label><br>
	<input name="titolo" type="text"><br>

	<label>Data di riconsegna</label><br>
	<input name="data" type="date"><br><br>
	
	<label >Stato di disponibilità</label>
		<select name="stato" id="stato">
			<option value="disponibile">Disponibile</option>
			<option value="nonDisponibile">Non Disponibile</option>
		</select><br><br>
	<input type="submit" name="azione" value="indietro" id="reg">
	<input type="submit" name="azione" value="conferma" id="reg" style="width:90px;">

</form>
</div>

</body>
</html>
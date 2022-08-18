<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/default.css" rel="stylesheet"></link>
		<title>SharedRecords</title>
	</head>

	<body>
<div>
	<h1>Shared Records</h1>
	<h2>Registrazione</h2>
	<jsp:useBean id="utenti" class="dao.UtenteDao" scope="application" />
	
	<form name="registrazione" id="myform" action="/SharedRecords/registrazioneUtente" method="post">
			<label>username:</label><br>
				<input type="text" name="user" value=""/> <br />
			<label>email:</label><br>
				<input type="text" name="email" value=""/> <br />
			<label>password:</label><br>
				<input type="password" name="password" value=""/> <br />
		<!-- <input type="submit" name="req" value="login" /><br />  -->
		 <!--input type="button" name="req" value="accedi" onClick="loginfunc(this.form)"/>	
		 <input type="button" name="req" value="registrati"/-->
			<input type="submit" name="reg" value="registrati" id="reg">
			
		</form>
</div>

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

</body>
</html>
<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/dashboardStyle.css" rel="stylesheet"></link>
			<title>SharedRecords</title>
	
	<script>
		function myFunction() {
			// Declare variables
			var input, filter, table, tr, td, i, txtValue;
			input = document.getElementById("myInput");
			filter = input.value.toUpperCase();
			table = document.getElementById("myTable");
			tr = table.getElementsByTagName("tr");
	
			// Loop through all table rows, and hide those who don't match the search query
			for (i = 0; i < tr.length; i++) {
				td = tr[i].getElementsByTagName("td")[1];
				if (td) {
					txtValue = td.textContent || td.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						tr[i].style.display = "";
					} else {
						tr[i].style.display = "none";
					}
				}
			}
		}
	</script>
	
</head>

	<body>
	
	 <% 
        String utente = (String)session.getAttribute("utente");
	 %>
	
	<div class="title">
		<h1>Shared Records</h1>
		<h2>Dashboard</h2>
		<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />

		<form name="accesso" id="myform" action="/SharedRecords/creazioneStanza" method="post">

			<label>cerca stanze:</label> 
			<input type="text" id="myInput"> 
			<input type="button" name="azione" value="cerca" id="reg" onCLick="myFunction()"> 
			<input type="hidden" name="utente" value="<%= utente %>"/>
			<input type="submit" name="azione" value="crea stanza" id="reg" style="width: 110px;">
	
		</form>
		
			<h2>Stanze</h2>
			
			
			
			
			<table class="formdata" id="myTable">
					<tr>
						<th>Nome</th>
						<th>Tag</th>
						<th></th>
					</tr>
					
					
					<%
					
					Stanza[] s = stanze.getStanze().toArray(new Stanza[0]);
					for( Stanza unaStanza : s ){  
					%> 
						
							<tr>
							<form name="catalogo" id="myform" action="/SharedRecords/visualizzaCatalogo" method="post">
								<td>
									<input type="hidden" name="azione" value="accedi"/>
									<input type="hidden" name="utente" value="<%= utente %>"/>
									<input type="hidden" name="nomeStanza" value="<%= unaStanza.getNome() %>"/>
									<input type="submit" id="tabreg" name="azione" value="<%= unaStanza.getNome() %>"/>
								</td>
							</form>
								<td><%= unaStanza.getTag().toString() %></td>
							<form name="iscrizione" id="myform" action="/SharedRecords/iscrizioneStanza" method="post">
								<td>
									<input type="hidden" name="utente" value="<%= utente %>"/>
									<input type="hidden" name="nomeStanza" value="<%= unaStanza.getNome() %>"/>
									<input type="submit" id="tabreg" name="azione" value="iscriviti"/>
								</td>
							</form>
							</tr>
						
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				
				
				
				
			<br>
			<br>
<form action="/SharedRecords/loginServlet" method=post>
	<input id="reg" type="submit" name="reg" value="logout" "id="reg">
</form>
			

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
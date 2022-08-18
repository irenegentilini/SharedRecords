<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.Stanza"%>
<%@ page import="model.Titolo"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/homeAmministrazioneStyle.css" rel="stylesheet"></link>
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
				td = tr[i].getElementsByTagName("td")[0];
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
	
	 
	<% String nomestanza = (String) session.getAttribute("stanza"); %>
	<div class="title">
		<h1>Shared Records</h1>
		<h2><%= nomestanza %></h2>
		<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
			
	<% 
        String utente = (String)session.getAttribute("utente");
	    Stanza s = (Stanza)request.getAttribute("stanza");
		if(s==null){
			String sNome = request.getParameter("nomeStanza");
			s = stanze.getStanza(sNome);
		}
	 %>

		<ul style="width: 580px;">
			<li><a href="/SharedRecords/pages/homeStanza.jsp?nomeStanza=<%= nomestanza %>" style="color: #f00000">Home</a></li>
			<li><a href="/SharedRecords/pages/viewConversazioni.jsp">Conversazioni</a></li>
			<li><a href="/SharedRecords/pages/viewRichieste.jsp?nomeStanza=<%= nomestanza %>">Richieste</a></li>
			<li><a href="/SharedRecords/inventario?carica=ok">Inventario</a></li>
			<li><a href="/SharedRecords/pages/viewMessaggiDiGruppo.jsp?id=<%= s.getConversazione().getId() %>">Conversazione di
					gruppo</a></li>
		</ul>
		

			<label>cerca titoli:</label> 
			<input type="text" id="myInput"> 
			<input type="button" name="azione" value="cerca" id="reg" onCLick="myFunction()">

			<h2>Titoli</h2>
			
			
			
			<table class="formdata" id="myTable">
					<tr>
						<th>Nome</th>
						<th>Disponibilita</th>
						<th>Punteggio</th>
						<th></th>
					</tr>
					
					
					<%
					List<Titolo> titoli = s.getTitoli();
					for( Titolo t : titoli ){  
					%> 
						
							<tr>
								<td><%= t.getNome() %></td>
								<td><%= t.getStatoDisponibilita() %></td>
								<% String statoInt=String.valueOf(t.getStatoDintegrita()); if(statoInt.equals("-1")) statoInt="da valutare";   %>
								<td><%= statoInt %></td>
								<td>
									<form  name="catalogo" id="myform" action="/SharedRecords/richiestaTitolo" method="post">
									<input type="hidden" name="titolo" value="<%= t.getId() %>"/>
									<input type="hidden" name="nomeStanza" value="<%= s.getNome() %>"/>
									<input type="submit" id="tabreg" name="azione" value="richiedi"/>
									</form>
								</td>
							</tr>
						
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			
			
			
			<br>
			<br> 
		<form  name="catalogo" id="myform" action="/SharedRecords/visualizzaCatalogo" method="post">
			<input type="submit" name="azione" value="esci" id="reg"
				style="margin-left: 470px;">
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
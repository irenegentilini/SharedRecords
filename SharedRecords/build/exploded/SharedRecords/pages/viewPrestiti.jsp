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
		<link type="text/css" href="/SharedRecords/styles/viewPrestitiStyle.css" rel="stylesheet"></link>
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
<div class="title">
	<h1>Shared Records</h1>
	<% String nomestanza = (String)session.getAttribute("stanza"); %>
	<h2 style="margin-left:80px;"><%= nomestanza %></h2>
	
	
	
	<ul>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= nomestanza %>" >Home</a></li>
			<li><a href="/SharedRecords/pages/homeGestioneUtenti.jsp">Utenti</a></li>
			<li><a href="/SharedRecords/pages/viewGestioneTitoli.jsp">Titoli</a></li>
			<li><a href="/SharedRecords/pages/viewPrestiti.jsp" style="color: #f00000">Prestiti</a></li>
		</ul>
	
	<form>
<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
	<label>cerca titoli in prestito:</label>
	<input type="text" id="myInput">
	<input type="button" value="cerca" id="reg" onCLick="myFunction()">
<% 
List<Prestito> all =stanze.getStanza(nomestanza).getPrestiti().getPrestiti();
List<Prestito> prestiti= new ArrayList<>();
for (Prestito p: all)
{
	if (p.isPrestitoAttivo())
		prestiti.add(p);
}

%>
	<h2>Prestiti</h2>
		<table id="myTable" class="formdata">
			<tr>
				<th>Nome</th>
				<th>Proprietario</th>
				<th>Richiedente</th>
				<th>Data Riconsegna</th>
				<th>Punteggio</th>
			</tr>
			
			<%
					String utente=(String)session.getAttribute("utente");
					String proprietario=null;
					String punteggio= null;
					String nomeTit=null;
					List<Inventario> inv =stanze.getStanza(nomestanza).getInventari().getInventari();
					for(Prestito p : prestiti) {
						proprietario=null;
						punteggio=null;
						nomeTit=null;
						for (Inventario i: inv)
						{
							for (Titolo t: i.getTitoli().getTitoli())
							{
								if (t.getId() == p.getTitolo())
								{
									nomeTit=t.getNome();
									proprietario=i.getUtente();
									punteggio=String.valueOf(t.getStatoDintegrita());
									if (punteggio.equals("-1"))
										punteggio="da valutare";
									break;
								}
							}
							if (proprietario != null)
							{
								break;
							}
						}
						
					%> 
							<tr>
								<td><%= nomeTit %></td>
								<td><%= proprietario %></td>
								<td><%= p.getUtente() %></td>
								<td><%= p.getDataRiconsegna() %></td>
								<td><%= punteggio%></td>
							</tr>
							
						
						
					<% } %>
			    
			
		</table>
	<br><br>
	
</form>
</div>

</body>
</html>
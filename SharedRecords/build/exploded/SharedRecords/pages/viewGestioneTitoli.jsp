<!-- pagina per la gestione di errori -->
<%@ page errorPage="../errors/failure.jsp"%>

<!-- import di classi Java -->
<%@ page import="java.util.*"%>
<%@ page import="model.*"%>
<%@ page import="model.Titolo"%>
<%@ page import="model.Prestito"%>

<!-- metodi richiamati nel seguito -->
<%!//%>

<!-- codice html restituito al client -->
<html>
	<head>
		<meta name="Author" content="cg">
		<link type="text/css" href="/SharedRecords/styles/viewGestioneTitoliStyle.css" rel="stylesheet"></link>
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
	<% String nomestanza=(String) session.getAttribute("stanza"); %>
	<h2 style="margin-left:80px;"><%= nomestanza %></h2>
	
	<ul>
			<li><a href="/SharedRecords/pages/homeAmministratore.jsp?nomeStanza=<%= nomestanza %>">Home</a></li>
			<li><a href="/SharedRecords/pages/homeGestioneUtenti.jsp">Utenti</a></li>
			<li><a href="/SharedRecords/pages/viewGestioneTitoli.jsp" style="color: #f00000">Titoli</a></li>
			<li><a href="/SharedRecords/pages/viewPrestiti.jsp">Prestiti</a></li>
	</ul>
	
	<form>
<jsp:useBean id="stanze" class="dao.StanzaDao" scope="application" />
	<label>cerca titoli:</label>
	<input type="text" id="myInput">
	<input type="button" value="cerca" id="reg" onCLick="myFunction()">

	<h2>Titoli</h2>
		<table id="myTable" class="formdata">
		<tr>
				<th>Nome</th>
				<th>Utente</th>
				<th>Prestiti totali</th>
				<th></th>
			</tr>
			<%
			String esito=(String)request.getParameter("azione");
			String titolo=(String)request.getParameter("titolo");
			if (esito != null && titolo != null)
			{
				for (Inventario i :stanze.getStanza(nomestanza).getInventari().getInventari())
				{
					for (Titolo tit: i.getTitoli().getTitoli())
					{
						if (String.valueOf(tit.getId()).equals(titolo))
						{
							i.rimuoviTitolo(tit);
							break;
						}
					}
				}
				
			}
			%>
			<%
				List<Titolo> titoli=stanze.getStanza(nomestanza).getTitoli();
				List<Inventario> inv=stanze.getStanza(nomestanza).getInventari().getInventari();
				String nomeTitolo=null;
				String proprietario=null;
				int numeroprestiti=-1;
			            for (Titolo t : titoli)
			            {
			            	nomeTitolo=null;
							proprietario=null;
							numeroprestiti=-1;
			            	for (Inventario i: inv)
			            	{
			            		for (Titolo j: i.getTitoli().getTitoli())
			            		{
			            			if(j.getId() == t.getId())
			            			{
			            				nomeTitolo=t.getNome();
			            				proprietario=i.getUtente();
			            				numeroprestiti=t.getContatorePrestiti();
			            				break;
			            			}
			            		}
			            		if (nomeTitolo != null && proprietario != null && numeroprestiti != -1)
			            		{
			            			break;
			            		}
			            	}
						
					%> 
							<tr>
								<td><%= nomeTitolo %></td>
								<td><%=  proprietario %></td>
								<td><%= numeroprestiti %></td>
								<td>
								<form method=post>
									<input type=hidden name=titolo value="<%= t.getId() %>"/>
									<input type=submit id="tabreg" name=azione value="rimuovi"/>
								</form>
							</td>
							</tr>
							
						
						
					<% } %>
			    
			
		</table>
	<br><br>
	
</form>
</div>

</body>
</html>
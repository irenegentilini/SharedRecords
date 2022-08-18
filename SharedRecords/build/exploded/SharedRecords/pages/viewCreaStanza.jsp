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
<link type="text/css" href="/SharedRecords/styles/viewCreaStanzaStyle.css"
	rel="stylesheet"></link>
<title>SharedRecords</title>

	<% List<String> tagList = (List<String>)session.getAttribute("tags"); %>

</head>

<body>
 	
	<div class="title">
		<h1>Shared Records</h1>
		<h2>Crea Stanza</h2>
		
		
		<%
				String nuovoTag = request.getParameter("nuovoTag");
	


						if ( request.getParameter("add") != null && request.getParameter("add").equals("aggiungi Tag") ) {
							if ( nuovoTag != null && ! nuovoTag.equals("") && !nuovoTag.contains(" ") ) {
							String tag = new String(request.getParameter("nuovoTag"));
							tagList.add(tag);
							session.setAttribute("tags", tagList);
							}
						}
				else if ( request.getParameter("remove") != null && request.getParameter("remove").equals("ok") ) {
					String tag = new String(request.getParameter("tagRem"));
					for(String t : tagList){
						if(tag.equals(t)) tagList.remove(t);
					}
					session.setAttribute("tags", tagList);
				}
			%>
		
			
			<form action="/SharedRecords/pages/viewCreaStanza.jsp">
			<label>Nuovo Tag</label><br> 
				<input type="text" name="nuovoTag" value=""> 
				<!--input type="button" value="aggiungi" id="reg" onCLick="myFunction()"-->
				<input type="submit" id="reg" name="add" value="aggiungi Tag" style="width: 120px;"/>
			</form>
			
			<form name="accesso" id="myform" action="/SharedRecords/creazioneStanza" method="post">

		
			<label>Nome Stanza</label><br> 
				<input type="text" name="nome" value=""><br>
	
			<h2>Tag</h2>
			<table class="formdata">
					<tr>
						<th style="width: 31%">Nome</th>
						<th style="width: 7%"></th>
					</tr>
					<% 
						String[] tags = tagList.toArray( new String[0] );
						for( String tag : tags ){
					%> 
						<tr>
							<td><%= tag %></td>
							<td>
								<a href="?remove=ok&tagRem=<%= tag %>">elimina</a>
							</td>
						</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table>
				
				


			<br> <br> 
			<input type="submit" name="azione" value="indietro" id="reg">
			<input type="hidden" id="tagvalue" name="amministratore" value=<% request.getAttribute("utente"); %>/>
			<input type="submit" name="azione" value="conferma" id="reg" style="width: 90px;">

		</form>
		
	
		
	</div>

</body>
</html>
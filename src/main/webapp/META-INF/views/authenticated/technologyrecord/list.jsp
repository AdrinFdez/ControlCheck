<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.technologyrecord.list.label.title" path="title" width="10%"/>
	<acme:list-column code="authenticated.technologyrecord.list.label.sector" path="sector" width="10%"/>
	<acme:list-column code="authenticated.technologyrecord.list.label.inventorName" path="inventorName" width="10%"/>
	<acme:list-column code="authenticated.technologyrecord.list.label.rating" path="rating" width="10%"/>
</acme:list>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.discussionforum.list.label.title" path="title" width="10%"/>
	<acme:list-column code="authenticated.discussionforum.list.label.investmentRound" path="investmentRound.title" width="10%"/>
	<acme:list-column code="authenticated.discussionforum.list.label.creationMoment" path="creationMoment" width="10%"/>
</acme:list>
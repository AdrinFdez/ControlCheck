<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.creditCard.list.label.cardNumber" path="cardNumber" width="25%"/>
	<acme:list-column code="patron.creditCard.list.label.brand" path="brand" width="15%"/>
</acme:list>

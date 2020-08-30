<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.cadenasbulletin.list.label.name" path="name" width="20%"/>
	<acme:list-column code="anonymous.cadenasbulletin.list.label.surname" path="surname" width="20%"/>
	<acme:list-column code="anonymous.cadenasbulletin.list.label.address" path="address" width="30%"/>
	<acme:list-column code="anonymous.cadenasbulletin.list.label.city" path="city" width="10%"/>
	<acme:list-column code="anonymous.cadenasbulletin.list.label.email" path="email" width="20%"/>
</acme:list>
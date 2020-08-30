<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.pinerobulletin.list.label.name" path="name" width="20%"/>
	<acme:list-column code="anonymous.pinerobulletin.list.label.address" path="address" width="10%"/>
	<acme:list-column code="anonymous.pinerobulletin.list.label.email" path="email" width="20%"/>
	<acme:list-column code="anonymous.pinerobulletin.list.label.date" path="date" width="20%"/>
	<acme:list-column code="anonymous.pinerobulletin.list.label.description" path="description" width="30%"/>
</acme:list>
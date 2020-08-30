<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.cruzbulletin.list.label.name" path="name" width="20%"/>
	<acme:list-column code="anonymous.cruzbulletin.list.label.gender" path="gender" width="20%"/>
	<acme:list-column code="anonymous.cruzbulletin.list.label.email" path="email" width="30%"/>
	<acme:list-column code="anonymous.cruzbulletin.list.label.phoneNumber" path="phoneNumber" width="30%"/>
</acme:list>
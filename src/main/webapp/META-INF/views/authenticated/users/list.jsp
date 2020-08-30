<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="autheticated.discussionforum.users.label.username" path="userAccount.username" width="30%"/>
	<acme:list-column code="autheticated.discussionforum.users.label.name" path="userAccount.identity.name" width="30%"/>
	<acme:list-column code="autheticated.discussionforum.users.label.surname" path="userAccount.identity.surname" width="40%"/>
</acme:list>
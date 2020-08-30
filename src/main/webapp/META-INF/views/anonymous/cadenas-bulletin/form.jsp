<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.cadenasbulletin.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.cadenasbulletin.form.label.surname" path="surname"/>
	<acme:form-textbox code="anonymous.cadenasbulletin.form.label.gender" path="gender"/>	
	<acme:form-textarea code="anonymous.cadenasbulletin.form.label.address" path="address"/>
	<acme:form-textbox code="anonymous.cadenasbulletin.form.label.city" path="city"/>
	<acme:form-textbox code="anonymous.cadenasbulletin.form.label.email" path="email"/>

	
	<acme:form-submit code="anonymous.cadenasbulletin.form.button.create" action="/anonymous/cadenas-bulletin/create"/>
  	<acme:form-return code="anonymous.cadenasbulletin.form.button.return"/>
	
</acme:form>

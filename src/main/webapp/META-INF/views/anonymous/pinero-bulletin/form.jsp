<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.pinerobulletin.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.pinerobulletin.form.label.address" path="address"/>
	<acme:form-textbox code="anonymous.pinerobulletin.form.label.email" path="email"/>
	<acme:form-textarea code="anonymous.pinerobulletin.form.label.description" path="description"/>
	
	<acme:form-submit code="anonymous.pinerobulletin.form.button.create" action="/anonymous/pinero-bulletin/create"/>
  	<acme:form-return code="anonymous.pinerobulletin.form.button.return"/>
	
</acme:form>

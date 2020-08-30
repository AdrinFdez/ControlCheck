<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.cruzbulletin.form.label.name" path="name"/>
	<acme:form-textbox code="anonymous.cruzbulletin.form.label.gender" path="gender"/>
	<acme:form-textbox code="anonymous.cruzbulletin.form.label.email" path="email"/>
	<acme:form-textarea code="anonymous.cruzbulletin.form.label.phoneNumber" path="phoneNumber"/>
	
	<acme:form-submit code="anonymous.cruzbulletin.form.button.create" action="/anonymous/cruz-bulletin/create"/>
  	<acme:form-return code="anonymous.cruzbulletin.form.button.return"/>
	
</acme:form>

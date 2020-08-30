<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.technologyrecord.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.technologyrecord.form.label.sector" path="sector" />
	<acme:form-textbox code="authenticated.technologyrecord.form.label.inventorName" path="inventorName" />
	<acme:form-textarea code="authenticated.technologyrecord.form.label.description" path="description" />
	<acme:form-url code="authenticated.technologyrecord.form.label.website" path="website" />
	<acme:form-textbox code="authenticated.technologyrecord.form.label.email" path="email"/>
	<acme:form-checkbox code="authenticated.technologyrecord.form.label.openSource" path="source" />
	<acme:form-textbox code="authenticated.technologyrecord.form.label.rating" path="rating" />
	
	<acme:form-return code="authenticated.technologyrecord.form.button.return"/>
</acme:form>

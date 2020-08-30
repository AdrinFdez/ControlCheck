<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.tool-record.form.label.title" path="title" />
	<acme:form-textbox code="anonymous.tool-record.form.label.sector" path="sector" />
	<acme:form-textbox code="anonymous.tool-record.form.label.inventorName" path="inventorName" />
	<acme:form-textarea code="anonymous.tool-record.form.label.description" path="description" />
	<acme:form-url code="anonymous.tool-record.form.label.website" path="website" />
	<acme:form-textbox code="anonymous.tool-record.form.label.email" path="email"/>
	<acme:form-checkbox code="anonymous.tool-record.form.label.openSource" path="openSource" />
	<acme:form-textbox code="anonymous.tool-record.form.label.rating" path="rating" />
	
	<acme:form-return code="anonymous.tool-record.form.button.return"/>
</acme:form>

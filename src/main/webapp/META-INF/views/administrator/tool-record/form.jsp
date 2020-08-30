<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >
	<acme:form-textbox code="authenticated.tool-record.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.tool-record.form.label.sector" path="sector" />
	<acme:form-textbox code="authenticated.tool-record.form.label.inventorName" path="inventorName" />
	<acme:form-textarea code="authenticated.tool-record.form.label.description" path="description" />
	<acme:form-url code="authenticated.tool-record.form.label.website" path="website" />
	<acme:form-textbox code="authenticated.tool-record.form.label.email" path="email"/>
	<acme:form-checkbox code="authenticated.tool-record.form.label.openSource" path="openSource" />
	<acme:form-textbox code="authenticated.tool-record.form.label.rating" path="rating" />
	
    <acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.tool-record.form.button.delete"	action="/administrator/tool-record/delete"/>
	
	<acme:form-submit test="${command == 'create'}"	code="administrator.tool-record.form.button.create" action="/administrator/tool-record/create"/>
	<acme:form-submit test="${command == 'update'}"	code="administrator.tool-record.form.button.update" action="/administrator/tool-record/update"/>
	<acme:form-submit test="${command == 'delete'}"	code="administrator.tool-record.form.button.delete" action="/administrator/tool-record/delete"/>
	<acme:form-return code="administrator.technologyrecord.form.button.return"/>
</acme:form>

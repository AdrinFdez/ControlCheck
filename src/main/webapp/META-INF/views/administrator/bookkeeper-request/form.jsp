<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.firm" readonly="true" path="firm"/>

	<acme:form-textarea code="administrator.bookkeeper-request.form.label.responsibilityStatement" readonly="true" path="responsibilityStatement"/>


	<acme:form-submit code="administrator.bookkeeper-request.form.button.accept" 
	action="/administrator/bookkeeper-request/accept"/>

	<acme:form-submit code="administrator.bookkeeper-request.form.button.reject" 
	action="/administrator/bookkeeper-request/reject"/>

	<acme:form-return code="administrator.bookkeeper-request.form.button.return"/>


</acme:form>
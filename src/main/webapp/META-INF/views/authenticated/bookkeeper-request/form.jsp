<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:choose>
<jstl:when test="${requestedError !=  null}">
<acme:message code="${requestedError}"/>
</jstl:when>
<jstl:otherwise>
<acme:form>
	<acme:form-textbox code="authenticated.bookkeeper-request.form.label.firm" path="firm"/>
	<acme:form-textarea code="authenticated.bookkeeper-request.form.label.responsibilityStatement" path="responsibilityStatement"/>
	
	<acme:form-submit code="authenticated.bookkeeper-request.form.label.create" test = "${command == 'create'}" action="/authenticated/bookkeeper-request/create"/>
	<acme:form-return code="authenticated.bookkeeper-request.form.label.return"/>
</acme:form>
</jstl:otherwise>
</jstl:choose>

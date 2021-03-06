<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-url code="authenticated.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.notice.form.label.creationDate" path="creationDate"/>
	<acme:form-moment code="authenticated.notice.form.label.deadlineDate" path="deadlineDate"/>
	<acme:form-textarea code="authenticated.notice.form.label.body" path="body"/>
	<acme:form-textarea code="authenticated.notice.form.label.optionalLink" path="optionalLink1"/>
	<acme:form-textarea code="authenticated.notice.form.label.optionalLink" path="optionalLink2"/>

	<acme:form-return code="authenticated.notice.form.button.return"/>

</acme:form>

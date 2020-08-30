<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-url code="anonymous.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="anonymous.notice.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.notice.form.label.creationDate" path="creationDate"/>
	<acme:form-moment code="anonymous.notice.form.label.deadlineDate" path="deadlineDate"/>
	<acme:form-textarea code="anonymous.notice.form.label.body" path="body"/>
	<acme:form-textarea code="anonymous.notice.form.label.optionalLink" path="optionalLink1"/>
	<acme:form-textarea code="anonymous.notice.form.label.optionalLink" path="optionalLink2"/>

	<%-- <acme:form-submit code="anonymous.notice.form.button.create" action="/anonymous/notice/create"/> --%>
	<acme:form-return code="anonymous.notice.form.button.return"/>

</acme:form>

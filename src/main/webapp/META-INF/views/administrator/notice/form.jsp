<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	
	<acme:form-url code="administrator.notice.form.label.headerPicture" path="headerPicture"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<jstl:if test="${command != 'create' }"> 
	<acme:form-moment code="administrator.notice.form.label.creationDate" path="creationDate" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.notice.form.label.deadlineDate" path="deadlineDate"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<acme:form-textarea code="administrator.notice.form.label.optionalLink" path="optionalLink1"/>
	<acme:form-textarea code="administrator.notice.form.label.optionalLink" path="optionalLink2"/>
	<jstl:if test="${command == 'create' }"> 
	<acme:form-checkbox code="administrator.notice.form.label.accept" path="aceptar"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create' }" code="administrator.notice.form.button.create" action="/administrator/notice/create"/>
	
	
	<acme:form-return code="administrator.notice.form.button.return"/>

</acme:form>

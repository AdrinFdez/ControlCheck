<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<acme:form readonly="true">
			<acme:form-textbox code="investor.accounting-record.form.label.title" path="title"/>
			<acme:form-textarea code="investor.accounting-record.form.label.body" path="body"/>
			<acme:form-textbox code="investor.accounting-record.form.label.status" path="status"/>
			<acme:form-moment code="investor.accounting-record.form.label.creationMoment" path="creationMoment"/>

  	<acme:form-return code="investor.accounting-record.form.button.return"/>
</acme:form>
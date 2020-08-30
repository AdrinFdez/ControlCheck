<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form> 

    <acme:form-hidden path="idInvestmentRound"/>

	<acme:form-textbox readonly="true" code="investor.activity.form.label.title" path="title"/>
	<acme:form-moment readonly="true" code="investor.activity.form.label.startDate" path="startDate"/>
	<acme:form-moment  readonly="true" code="investor.activity.form.label.endDate" path="endDate"/>

	<acme:form-return code="investor.activity.form.button.return"/>

</acme:form> 



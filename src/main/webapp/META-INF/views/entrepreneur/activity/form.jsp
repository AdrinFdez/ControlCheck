<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form> 

     <acme:form-hidden path="idInvestmentRound"/>
     
	<jstl:if test="${command == 'add' }">
		<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	</jstl:if>
	<jstl:if test="${command == 'add' }">
		<acme:form-moment code="entrepreneur.activity.form.label.endDate" path="endDate"/>
	</jstl:if>
	<jstl:if test="${workProgramme.investmentRound.status == 'published' }">
		<acme:form-double readonly = "true" code="entrepreneur.activity.form.label.budget" path="budget"/>
	</jstl:if>
	
	<jstl:if test="${workProgramme.investmentRound.status != 'published' }">
		<acme:form-double code="entrepreneur.activity.form.label.budget" path="budget"/>
	</jstl:if>
	
	<jstl:if test="${command != 'add' || command == 'show' }">
		<acme:form-textbox readonly="true" code="entrepreneur.activity.form.label.title" path="title"/>
	</jstl:if>
	<jstl:if test="${command != 'add' || command == 'show'}">
		<acme:form-moment readonly="true" code="entrepreneur.activity.form.label.startDate" path="startDate"/>
	</jstl:if>
	<jstl:if test="${command != 'add'  || command == 'show'}">
		<acme:form-moment  readonly="true" code="entrepreneur.activity.form.label.endDate" path="endDate"/>
	</jstl:if>

	<acme:form-return code="entrepreneur.activity.form.button.return"/>

 	<acme:form-submit code="entrepreneur.activity.form.button.create" 
	action="/entrepreneur/activity/add"
	test="${command == 'add'}"/>
	
	<acme:form-submit code="entrepreneur.activity.form.button.update" 
	action="/entrepreneur/activity/update"
	test="${(command == 'show' || command == 'update') && workProgramme.investmentRound.status != 'published'}"/>

</acme:form> 



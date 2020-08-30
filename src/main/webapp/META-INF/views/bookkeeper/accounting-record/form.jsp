<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<acme:form>
	<acme:form-textbox code="bookkeeper.accounting-record.form.label.title" path="title"/>
		<jstl:if test = "${command != 'create' }">
			<acme:form-moment code="bookkeeper.accounting-record.form.label.creationMoment" path="creationMoment"  readonly="true"/>
	</jstl:if>
	
	<acme:form-textarea code="bookkeeper.accounting-record.form.label.body" path="body"/>		
			
	<jstl:if test = "${command != 'create' }">
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.status" path="status" readonly="true"/>
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.bookkeeper.firm" path="bookkeeper.firm" readonly="true"/>
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.investment-round.ticker" path="investmentRound.ticker" readonly="true"/>
		<acme:form-textbox code="bookkeeper.accounting-record.form.label.investment-round.title" path="investmentRound.title" readonly="true"/>
	</jstl:if>			
	
	<acme:form-hidden path="idInvestmentRound"/>		
<acme:form-submit test="${command == 'create'}"
	code = "bookkeeper.accounting-record.form.label.create"
	action="/bookkeeper/accounting-record/create"
	method = "post"/>
	
	<acme:form-submit test="${command == 'show' && status == 'draft' }"
	code = "bookkeeper.accounting-record.form.label.publish"
	action="/bookkeeper/accounting-record/publish"
	method = "post"/>
	
	<acme:form-submit test="${command == 'publish' && status == 'draft' }"
		code = "bookkeeper.accounting-record.form.label.publish"
	action="/bookkeeper/accounting-record/publish"
	method = "post"/>


  	<acme:form-return code="bookkeeper.accounting-record.form.button.return"/>
</acme:form>


	
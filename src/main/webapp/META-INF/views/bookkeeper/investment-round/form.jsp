<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="bookkeeper.investment-round.form.label.ticker" path="ticker"/>
	<acme:form-moment code="bookkeeper.investment-round.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.status" path="status"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.kind" path="kind"/>
	<acme:form-textbox code="bookkeeper.investment-round.form.label.title" path="title"/>
	<acme:form-textarea code="bookkeeper.investment-round.form.label.description" path="description"/>
	<acme:form-money code="bookkeeper.investment-round.form.label.money" path="money"/>
	<acme:form-url code="bookkeeper.investment-round.form.label.link" path="link"/>
</acme:form>
	
	
	<acme:form-submit code="bookkeeper.investment-round.message.accounting-record" 
		method="get" 
		action= "/bookkeeper/accounting-record/list?idInvestmentRound=${id}"/>
	
	<acme:form-submit  test ="${command != 'create'}"  
		code="bookkeeper.investment-round.form.button.create.accountingRecord"
		method="get"
		action= "/bookkeeper/accounting-record/create?idInvestmentRound=${id}"/>
	

	
<acme:form>	
	
	<acme:form-return code="bookkeeper.investment-round.form.button.return"/>
</acme:form>



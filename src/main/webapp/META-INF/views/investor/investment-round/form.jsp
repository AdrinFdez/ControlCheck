<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form readonly="true">
	
	<acme:form-textbox code="investor.investmentRound.form.label.kind" path="kind"/>	
	<acme:form-textbox code="investor.investmentRound.form.label.title" path="title"/>
	<acme:form-textarea code="investor.investmentRound.form.label.description" path="description"/>
	<acme:form-double code="investor.investmentRound.form.label.money" path="money"/>
	<acme:form-url code="investor.investmentRound.form.label.link" path="link"/>
	<acme:form-textarea code="investor.investmentRound.form.label.yomp" path="yomp"/>
	
	<acme:form-submit test="${command == 'show' }"
		method = "get"
		code="investor.investmentRoundApplication.form.button.apply"
		action="/investor/investment-round-application/create?idInvestmentRound=${id}"/>
	
	<!-- Accounting REcord List -->
   <acme:form-submit method="get" code="investor.investment-round.message.accounting-record" action= "/investor/accounting-record/list?idInvestmentRound=${id}"/>
	
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
	
</acme:form>
	
	<jstl:if test="${command != 'create' }" > 
		<a href = /acme-incubator/investor/activity/list?idInvestmentRound=<jstl:out value="${id}"></jstl:out>>
			<acme:message code="investor.investmentRound.message.activity"/>
		</a>
	</jstl:if>
	



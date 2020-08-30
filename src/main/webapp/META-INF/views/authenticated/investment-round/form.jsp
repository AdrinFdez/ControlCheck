<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investmentRound.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investmentRound.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.kind" path="kind"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.investmentRound.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.investmentRound.form.label.money" path="money"/>
	<acme:form-url code="authenticated.investmentRound.form.label.link" path="link"/>
	
	<!-- List Forum -->
	<acme:form-submit method="get" code="authenticated.investmentRound.list.message.discussionforum" action="/authenticated/discussion-forum/list?idInvestment=${id}"/>
	<!-- List Accounting -->
	<acme:form-submit method="get" code="authenticated.investment-round.message.accounting-record" action= "/authenticated/accounting-record/list?idInvestmentRound=${id}"/>
	<!-- Return -->
	<acme:form-return code="authenticated.investmentRound.form.button.return"/>
	<br>
	<jstl:if test="${command != 'create' }" > 
        <a href = /acme-incubator/authenticated/activity/list?idInvestmentRound=<jstl:out value="${id}"></jstl:out>>
    		<acme:message code="authenticated.investmentRound.message.activity"/>
        </a>
    </jstl:if>

</acme:form>

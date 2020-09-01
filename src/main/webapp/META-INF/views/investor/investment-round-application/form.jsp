<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${ command == 'create' }">
		<acme:form-textbox code="investor.investmentRoundApplication.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN (SSS- SECTOR / YY- YEAR / NNNNNN - NUMBER)"/>
	</jstl:if>
	
	<jstl:if test="${ command == 'create' && investmentRound.text != null}">
		<acme:form-textbox code="investor.investmentRoundApplication.form.label.link" path="link"/>
		<acme:form-textbox code="investor.investmentRoundApplication.form.label.pass" path="pass"/>
	</jstl:if>
	
	<jstl:if test="${ command != 'create' }">
		<acme:form-textbox readonly="true" code="investor.investmentRoundApplication.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN (SSS- SECTOR / YY- YEAR / NNNNNN - NUMBER)"/>
		<acme:form-moment readonly="true" code="investor.investmentRoundApplication.form.label.creationMoment" path="creationMoment"/>
		<acme:form-textbox readonly="true" code="investor.investmentRoundApplication.form.label.statement" path="statement"/>
		<acme:form-textbox readonly="true" code="investor.investmentRoundApplication.form.label.link" path="link"/>
		<acme:form-textbox readonly="true" code="investor.investmentRoundApplication.form.label.pass" path="pass"/>
	</jstl:if>
	
	<jstl:if test="${ statement == 'rejected' }">
		<acme:form-textarea readonly="true" code="investor.investmentRoundApplication.form.label.justification" path="justification"/>
	</jstl:if>
	
	<acme:form-money code="investor.investmentRoundApplication.form.label.offer" path="offer"/>
	<acme:form-hidden path="idInvestmentRound"/>
	
	<acme:form-submit code="investor.investmentRoundApplication.form.button.create" 
	action="/investor/investment-round-application/create"
	test="${command == 'create'}"/>
	
	<jstl:if test="${statement == 'pending' }">
		<acme:form-submit test="${command == 'show' }"
		code="investor.investmentRoundApplication.form.button.update"
		action="/investor/investment-round-application/update"/>
		<acme:form-submit test="${command == 'show' }"
		code="investor.investmentRoundApplication.form.button.delete"
		action="/investor/investment-round-application/delete"/>
		<acme:form-submit test="${command == 'update' }"
		code="investor.investmentRoundApplication.form.button.update"
		action="/investor/investment-round-application/update"/>
		<acme:form-submit test="${command == 'delete' }"
		code="investor.investmentRoundApplication.form.button.delete"
		action="/investor/investment-round-application/delete"/>
	</jstl:if>
	
	<jstl:if test="${ statement == 'accepted' }">
		<!-- Discussion Forum List -->
		<acme:form-submit
			method="get"
			code="authenticated.investmentRound.list.message.discussionforum"
			action="/authenticated/discussion-forum/list?idInvestment=${investmentRound.id}"/>
		
		<!-- Discussion Forum Create -->
		<acme:form-submit
			method="get"
			code="entrepreneur.discussionforum.investmentRound.form.button.create"
			action= "/authenticated/discussion-forum/create?idInvestmentRound=${investmentRound.id}"/>
			
	</jstl:if>
	
	<acme:form-return code="investor.investmentRoundApplication.form.button.return"/>
	<br>
	<jstl:if test="${command != 'create' }">
		<a href = /acme-incubator/investor/investment-round/show?id=<jstl:out value="${investmentRound.id}"></jstl:out>>
			<acme:message code="investor.investmentRoundApplication.message.investmentRound"/>
		</a>
	</jstl:if>

</acme:form>

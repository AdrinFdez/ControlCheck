<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="id"/>
	<acme:form-hidden path="link"/>
	<acme:form-hidden path="pass"/>
	<acme:form-hidden path="checked"/>
	<acme:form-textbox readonly="true" code="entrepreneur.investmentRoundApplication.form.label.ticker" path="ticker"/>
	<acme:form-moment readonly="true" code="entrepreneur.investmentRoundApplication.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox readonly="true" code="entrepreneur.investmentRoundApplication.form.label.statement" path="statement"/>
	<acme:form-money readonly="true" code="entrepreneur.investmentRoundApplication.form.label.offer" path="offer"/>
	
	<jstl:if test="${(pass == null || pass == '') && link != null}">
		<acme:form-url readonly="true" code="entrepreneur.investmentRoundApplication.form.label.link" path="link" placeholder=""/>
	</jstl:if>
	
	<jstl:if test="${(pass != null && pass != '') && link != null && checked == true}">
		<acme:form-url readonly="true" code="entrepreneur.investmentRoundApplication.form.label.link" path="link" placeholder=""/>
	</jstl:if>
	
	<jstl:if test="${pass != null && pass != '' && link != null && checked == false}">
		<acme:form-password code="entrepreneur.investmentRoundApplication.form.label.password" path="check"/>
	</jstl:if>
		
	<jstl:if test="${statement == 'rejected' }">
		<acme:form-textarea readonly="true" code="entrepreneur.investmentRoundApplication.form.label.justification" path="justification"/>
	</jstl:if>
	
	<acme:form-submit test="${(command == 'show' || command == 'check') && checked == false}"
		code="entrepreneur.investmentRoundApplication.form.button.check"
		action="/entrepreneur/investment-round-application/check?id=${id}"/>
	
	
	<jstl:if test="${statement == 'pending' }">
		<acme:form-textarea code="entrepreneur.investmentRoundApplication.form.label.justification" path="justification"/>
		<acme:form-submit test="${command == 'show' }"
		code="entrepreneur.investmentRoundApplication.form.button.accept"
		action="/entrepreneur/investment-round-application/accept?id=${id}"/>
		<acme:form-submit test="${command == 'accept' }"
		code="entrepreneur.investmentRoundApplication.form.button.accept"
		action="/entrepreneur/investment-round-application/accept?id=${id}"/>
		<acme:form-submit test="${command == 'reject' }"
		code="entrepreneur.investmentRoundApplication.form.button.accept"
		action="/entrepreneur/investment-round-application/accept?id=${id}"/>
		<acme:form-submit test="${command == 'show' }"
		code="entrepreneur.investmentRoundApplication.form.button.reject"
		action="/entrepreneur/investment-round-application/reject?id=${id}"/>
		<acme:form-submit test="${command == 'reject' }"
		code="entrepreneur.investmentRoundApplication.form.button.reject"
		action="/entrepreneur/investment-round-application/reject?id=${id}"/>
	</jstl:if>

	<acme:form-return code="entrepreneur.investmentRoundApplication.form.button.return"/>
	
	<br>
	<jstl:if test="${command != 'reject' }">
		<a href = /acme-incubator/authenticated/investment-round/show?id=<jstl:out value="${investmentRound.id}"></jstl:out>>
			<acme:message code="entrepreneur.investmentRoundApplication.message.investmentRound"/>
		</a>
	</jstl:if>

</acme:form>

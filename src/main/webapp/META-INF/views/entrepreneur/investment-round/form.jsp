<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>

	<jstl:if test="${command != 'create' }">
	<acme:form-textbox readonly="true" placeholder="SSS-YY-NNNNNN (SSS- SECTOR / YY- YEAR / NNNNNN - NUMBER)"  code="entrepreneur.investmentRound.form.label.ticker" path="ticker"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
		<acme:form-textbox placeholder="SSS-YY-NNNNNN (SSS- SECTOR / YY- YEAR / NNNNNN - NUMBER)"  code="entrepreneur.investmentRound.form.label.ticker" path="ticker"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create' }">
			<acme:form-textbox  readonly="true" code="entrepreneur.investmentRound.form.label.status" path="status"/>
	</jstl:if>	
	

	 <jstl:if test="${status == 'published'}">
	<acme:form-textbox readonly="true" code="entrepreneur.investmentRound.form.label.kind" path="kind"/>
 	</jstl:if>	
 	<jstl:if test="${command == 'create' || status== 'draft' }">
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.kind" path="kind"/>
 	</jstl:if>	
 	
 	 <jstl:if test="${status == 'published'}">
	<acme:form-textbox readonly="true" code="entrepreneur.investmentRound.form.label.title" path="title"/>
	</jstl:if>	
	<jstl:if test="${command == 'create' || status== 'draft' }">
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.title" path="title"/>
	</jstl:if>
	
	<jstl:if test="${status == 'published'}">
		<acme:form-textarea readonly="true" code="entrepreneur.investmentRound.form.label.description" path="description"/>
	</jstl:if>	
	<jstl:if test="${command == 'create' || status== 'draft' }">
		<acme:form-textarea code="entrepreneur.investmentRound.form.label.description" path="description"/>
	</jstl:if>
	
	<jstl:if test="${status == 'published'}">
		<acme:form-money readonly="true" code="entrepreneur.investmentRound.form.label.money" path="money"/>
	</jstl:if>	
	<jstl:if test="${command == 'create' || status== 'draft' }">
		<acme:form-money code="entrepreneur.investmentRound.form.label.money" path="money"/>
	</jstl:if>	
	
	<jstl:if test="${status == 'published'}">
		<acme:form-url readonly="true" code="entrepreneur.investmentRound.form.label.link" path="link"/>
	</jstl:if>
	<jstl:if test="${command == 'create' || status== 'draft' }">
		<acme:form-url code="entrepreneur.investmentRound.form.label.link" path="link"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create' }">
	<acme:form-textbox readonly = "true" code="entrepreneur.investmentRound.form.label.text" path="text"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create' }">
	<acme:form-textbox code="entrepreneur.investmentRound.form.label.text" path="text"/>
	</jstl:if>
 
	
	
   <acme:form-submit code="entrepreneur.investmentRound.form.button.create" 
	action="/entrepreneur/investment-round/create"
	test="${command == 'create'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.update" 
	action="/entrepreneur/investment-round/update"
	test="${command == 'update' && status == 'draft'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.update" 
	action="/entrepreneur/investment-round/update"
	test="${command == 'show' && status == 'draft'}"/>
	
	<acme:form-submit code="entrepreneur.investmentRound.form.button.update" 
	action="/entrepreneur/investment-round/update"
	test="${command == 'publish' && status == 'draft'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.delete" 
	action="/entrepreneur/investment-round/delete"
	test="${command == 'delete'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.delete" 
	action="/entrepreneur/investment-round/delete"
	test="${command == 'show'}"/>
	
		<acme:form-submit code="entrepreneur.investmentRound.form.button.delete" 
	action="/entrepreneur/investment-round/delete"
	test="${command == 'publish'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.publish" 
	action="/entrepreneur/investment-round/publish"
	test="${command == 'publish'  && status == 'draft'}"/>

	<acme:form-submit code="entrepreneur.investmentRound.form.button.publish" 
	action="/entrepreneur/investment-round/publish"
	test="${command == 'show'  && status == 'draft'}"/>

	</acme:form>
	<br>
	<acme:form-submit code="entrepreneur.activity.form.button.addActivity" 
     action="/entrepreneur/activity/add?idInvestmentRound=${id}"
     test="${command != 'create'  && status == 'draft'}"
     method = "get"/>

  <!-- Accounting REcord List -->
  <acme:form-submit method="get" code="entrepreneur.investment-round.message.accounting-record" action= "/entrepreneur/accounting-record/list?idInvestmentRound=${id}"/>
  <!-- Discussion Forum List -->
	<acme:form-submit method="get" code="entrepreneur.discussionforum.investmentRound.form.button.list" action="/authenticated/discussion-forum/list?idInvestment=${id}"/>
  <!-- Discussion Forum Create -->
	<acme:form-submit test ="${command != 'create'}" method="get" code="entrepreneur.discussionforum.investmentRound.form.button.create" action= "/authenticated/discussion-forum/create?idInvestmentRound=${id}"/>
  <!-- Return -->
	<acme:form-return code="entrepreneur.investmentRound.form.button.return"/>
	
	<br>
		<jstl:if test="${command != 'create' }" > 
		<a href = /acme-incubator/entrepreneur/activity/list?idInvestmentRound=<jstl:out value="${id}"></jstl:out>>
	<acme:message code="entrepreneur.investmentRound.message.activity"/>
		</a>
	</jstl:if>
<br>
	<a href = /acme-incubator/entrepreneur/investment-round-application/list?id=<jstl:out value="${id}"></jstl:out>>
		<acme:message code="entrepreneur.investmentRound.message.investmentRoundApplication"/>
	</a>



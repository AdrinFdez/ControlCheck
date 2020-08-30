<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="patron.creditCard.form.label.cardNumber" path="cardNumber"/>
	<acme:form-textbox code="patron.creditCard.form.label.holder" path="holder"/>
	<acme:form-textbox code="patron.creditCard.form.label.cvv" path="cvv"/>
	<acme:form-textbox code="patron.creditCard.form.label.brand" path="brand"/>
	<acme:form-textbox code="patron.creditCard.form.label.expirationMonth" path="expirationMonth"/>
	<acme:form-textbox code="patron.creditCard.form.label.expirationYear" path="expirationYear"/>
	
	<acme:form-submit test="${command == 'create' }" code="patron.creditCard.form.button.create" action="/patron/credit-card/create"/>
	<acme:form-submit test="${command == 'show' }" code="patron.creditCard.form.button.update" action="/patron/credit-card/update"/>
	<acme:form-submit test="${command == 'update' }" code="patron.creditCard.form.button.update" action="/patron/credit-card/update"/>
	
	<acme:form-return code="patron.creditCard.form.button.return"/>
	
</acme:form>

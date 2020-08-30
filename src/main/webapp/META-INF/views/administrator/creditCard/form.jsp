<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.creditCard.form.label.cardNumber" path="cardNumber"/>
	<acme:form-textbox code="administrator.creditCard.form.label.holder" path="holder"/>
	<acme:form-textbox code="administrator.creditCard.form.label.cvv" path="cvv"/>
	<acme:form-textbox code="administrator.creditCard.form.label.brand" path="brand"/>
	<acme:form-textbox code="administrator.creditCard.form.label.expirationMonth" path="expirationMonth"/>
	<acme:form-textbox code="administrator.creditCard.form.label.expirationYear" path="expirationYear"/>
	
	<acme:form-submit test="${command == 'create' }" code="administrator.creditCard.form.button.create" action="/administrator/credit-card/create"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.creditCard.form.button.update" action="/administrator/credit-card/update"/>
	<acme:form-submit test="${command == 'update' }" code="administrator.creditCard.form.button.update" action="/administrator/credit-card/update"/>
	
	<acme:form-return code="administrator.creditCard.form.button.return"/>
	
</acme:form>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="patron.banner.form.label.imageurl" path="imageurl"/>
	<acme:form-url code="patron.banner.form.label.targeturl" path="targeturl"/>
	<%-- <acme:form-select code="administrator.banner.form.select.creditCard" path="creditCardId">
		<jstl:forEach items="${creditCard}" var="item">
	<acme:form-option   code="${item.cardNumber}" value="${item.id}"/>
	</jstl:forEach>
	</acme:form-select>
 --%>
 	<jstl:if test="${command != 'create'}"> 
	<acme:form-textbox code="patron.banner.form.label.creditCard" path="creditCard.cardNumber" readonly="true"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create' }" code="patron.banner.form.button.create" action="/patron/banner/create"/>
	<acme:form-submit test="${command == 'show' }" code="patron.banner.form.button.update" action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'update' }" code="patron.banner.form.button.update" action="/patron/banner/update"/>
	<acme:form-submit test="${command == 'show' }" code="patron.banner.form.button.delete" action="/patron/banner/delete"/>
	<acme:form-submit test="${command == 'delete' }" code="patron.banner.form.button.delete" action="/patron/banner/delete"/>

	<acme:form-return code="patron.banner.form.button.return"/>
</acme:form>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.banner.form.label.imageurl" path="imageurl"/>
	<acme:form-url code="administrator.banner.form.label.targeturl" path="targeturl"/>
	<acme:form-select code="administrator.banner.form.select.creditCard" path="creditCardId">
		<jstl:forEach items="${creditCard}" var="item">
	<acme:form-option   code="${item.cardNumber}" value="${item.id}"/>
	</jstl:forEach>
	</acme:form-select>

	<acme:form-submit test="${command == 'create' }" code="administrator.banner.form.button.create" action="/administrator/banner/create"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.banner.form.button.update" action="/administrator/banner/update"/>
	<acme:form-submit test="${command == 'update' }" code="administrator.banner.form.button.update" action="/administrator/banner/update"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.banner.form.button.delete" action="/administrator/banner/delete"/>
	<acme:form-submit test="${command == 'delete' }" code="administrator.banner.form.button.delete" action="/administrator/banner/delete"/>

	<acme:form-return code="administrator.banner.form.button.return"/>
</acme:form>

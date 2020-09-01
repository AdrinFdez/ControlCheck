<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.overture.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.overture.form.label.creation" path="creation" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="administrator.overture.form.label.description" path="description"/>
	<acme:form-textbox code="administrator.overture.form.label.moneymin" path="moneyMin"/>
	<acme:form-textbox code="administrator.overture.form.label.moneymax" path="moneyMax"/>
	<acme:form-textbox code="administrator.overture.form.label.mail" path="mail"/>

	<acme:form-return code="administrator.overture.form.button.return"/>
	
	<acme:form-submit code="administrator.overture.form.button.update" 
	action="/administrator/overture/update"
	test="${command == 'show'}"/>

	<acme:form-submit code="administrator.overture.form.button.delete" 
	action="/administrator/overture/delete"
	test="${command == 'show'}"/>

	<acme:form-submit code="administrator.overture.form.button.create" 
	action="/administrator/overture/create"
	test="${command == 'create'}"/>

	<acme:form-submit code="administrator.overture.form.button.update" 
	action="/administrator/overture/update"
	test="${command == 'update'}"/>

	<acme:form-submit code="administrator.overture.form.button.delete" 
	action="/administrator/overture/delete"
	test="${command == 'delete'}"/>
</acme:form>

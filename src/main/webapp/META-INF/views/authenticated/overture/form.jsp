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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.overture.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.overture.form.label.creation" path="creation"/>
	<acme:form-moment code="authenticated.overture.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.overture.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.overture.form.label.moneymin" path="moneyMin"/>
	<acme:form-textbox code="authenticated.overture.form.label.moneymax" path="moneyMax"/>
	<acme:form-textbox code="authenticated.overture.form.label.mail" path="mail"/>

	<acme:form-return code="authenticated.overture.form.button.return"/>
</acme:form>

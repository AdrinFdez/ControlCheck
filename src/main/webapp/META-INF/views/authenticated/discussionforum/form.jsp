<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>
	<jstl:if test="${command == 'create' }">
		<acme:form-textbox code="authenticated.discussionforum.form.label.title" path="title" />
	
	</jstl:if>	

	<jstl:if test="${command != 'create'}">

	<acme:form-textbox code="authenticated.discussionforum.form.label.title" path="title" readonly="true" />
	<acme:form-moment  readonly="true" code="authenticated.discussionforum.list.label.creationMoment" path="creationMoment"/>
	
	<!-- DISCUSSION FORUM -->
	
	<!-- Show Delete -->
	<acme:form-submit test="${command == 'show'}" code="authenticated.discussionforum.form.button.delete" action="/authenticated/discussion-forum/delete?idForum=${id}"/>
	<!-- Delete -->
	<acme:form-submit test="${command == 'delete'}" code="authenticated.discussionforum.form.button.delete" action="/authenticated/discussion-forum/delete"/>
	
	<!-- MESSAGES -->
  	<!-- List -->
	<acme:form-submit method="get" code="authenticated.discussionforum.message.form.button.list" action="/authenticated/message/list?idForum=${id}"/>
  	<!-- Create -->
	<acme:form-submit method="get" code="authenticated.discussionforum.message.form.button.create" action="/authenticated/message/create?idForum=${id}"/>
	
	<!-- USERS -->
	<!-- List -->
	<acme:form-submit method="get" code="authenticated.discussionforum.user.form.button.list" action="/authenticated/users/list?idForum=${id}"/>
	<!-- Add -->
	<acme:form-submit method="get" code="authenticated.discussionforum.user.form.button.add" action="/authenticated/authenticated-discussion-forum/add?idForum=${id}" />
	<!-- Delete -->
	<acme:form-submit method="get" code="authenticated.discussionforum.user.form.button.delete" action="/authenticated/authenticated-discussion-forum/delete?idForum=${id}" />

	</jstl:if>
	
	<!-- Create -->
	<acme:form-submit test="${command == 'create'}"  method = "post" code = "authenticated.discussionforum.form.button.create" action="/authenticated/discussion-forum/create"/>
	
	<acme:form-hidden path="idInvestmentRound"/>
	<!-- Return -->
	<acme:form-return code="autheticated.discussionforum.form.button.return"/>
	
</acme:form>
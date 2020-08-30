<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form>

	<jstl:if test="${command == 'add' }">
		<jstl:choose>
			<jstl:when test="${empty usersToAdd}">
				<acme:message code="autheticated.discussionforum.form.noMoreUsers"/>
			</jstl:when>
	
			<jstl:otherwise>
				<acme:form-select code="authenticated.discussionforum.form.usersToAdd" path="userAdded">
					<jstl:forEach items="${usersToAdd}" var = "item">
						<acme:form-option code="${item.userAccount.username}" value="${item.id}"/>
					</jstl:forEach>
	
				</acme:form-select>
				<acme:form-hidden path="idForum"/>
				<acme:form-submit code="autheticated.discussionforum.form.button.addUser" action="/authenticated/authenticated-discussion-forum/add"/>

			</jstl:otherwise>
		</jstl:choose>
	
	</jstl:if>
	
	<jstl:if test="${command == 'delete'}">
		<jstl:choose>
			<jstl:when test="${empty usersToRemove}">
				<acme:message code="autheticated.discussionforum.form.noMoreUsersToRemove"/>
			</jstl:when>
	
			<jstl:otherwise>
				<acme:form-select code="authenticated.discussionforum.form.usersToRemove" path="userRemoved">
					<jstl:forEach items="${usersToRemove}" var = "item">
						<acme:form-option code="${item.userAccount.username}" value="${item.id}"/>
					</jstl:forEach>
	
				</acme:form-select>
				<acme:form-hidden path="idForum"/>
			
				<acme:form-submit code="autheticated.discussionforum.form.button.deleteUser" action="/authenticated/authenticated-discussion-forum/delete"/>

			</jstl:otherwise>
		</jstl:choose>
	
	</jstl:if>
	
	<acme:form-return code="autheticated.discussionforum.form.button.return"/>
	
</acme:form>
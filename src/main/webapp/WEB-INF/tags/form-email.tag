<%@tag
	language="java"
	body-content="empty"
	import="acme.framework.helpers.MessageHelper"
%>

<%@taglib
	prefix ="jstl"
	uri="http://java.sun.com/jsp/jstl/core"
%>

<%@taglib
	prefix="acme"
	tagdir="/WEB-INF/tags"
%>

<%@attribute name="path" required="true"%>
<%@attribute name="code" required="true"%>
<%@attribute name="placeholder"%>
<%@attribute name="readonly" type="java.lang.Boolean"%>

<jstl:if test="${placeholder == null}">
	<jstl:set
		var="placeholder"
		value="${MessageHelper.getMessage('default.placeholder.email')}"
		/>
</jstl:if>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false"/>
</jstl:if>

<div class="form-group">
	<label for="${path}">
		<acme:message code="${code}"/>
	</label>
	<input
		id="${path}" name="${path}"
		value="<acme:print value="${requestScope[path]}"/>"
		type="text" class="form-control"
		placeholder="${placeholder }"
		<jstl:if test="${readonly}">
			readonly
		</jstl:if>
	/>
	<acme:form-errors path="${path}"/>
	<acme:form-errors path="${path}.displayName"/>
	<acme:form-errors path="${path}.email"/>	
</div> 
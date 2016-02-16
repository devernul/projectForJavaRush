<%@ include file="header.jsp" %>
<spring:htmlEscape defaultHtmlEscape="true" />
<table >
    <tr>
        <td align="right" colspan="4">
            <h1 class="choseLang"><span><spring:message code="ChoseLang"/>:</span>
                <a href="?locale=en">en</a> / <a href="?locale=ru">ru</a>
            </h1>
        </td>
    </tr>
    <tr>
        <td align="center" colspan="4"><h1><spring:message code="Show"/> </h1></td>
    </tr>

</table>
            <form:form commandName="task" method="POST" id="formCreateUpdateTask" cssClass="fullForm">
                <form:hidden path="taskId"/>

                <table class="formTable">
                    <tr>
                        <td><spring:message code="Name"/>:</td>
                        <td><form:input path="name"/></td>
                        <td class="noBorder"><form:errors path="name"/></td>

                    </tr>

                    <tr>
                        <td><spring:message code="Date"/>:</td>
                        <td><form:input path="date" placeholder="MM/dd/yyyy"/></td>
                        <td class="noBorder"><form:errors path="date"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="Description"/>:</td>
                        <td><form:textarea path="description" cols="40" rows="10"/></td>
                        <td class="noBorder"><form:errors path="description"/></td>
                    </tr>

                    <tr>
                        <td><spring:message code="Responsible"/>:</td>
                        <td><form:select path="users" items="${users}" multiple="multiple" size="5"  itemLabel="name" itemValue="userId"/></td>
                        <td class="noBorder"><form:errors path="users"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="Status"/>:</td>
                        <td><form:select path="status" items="${statuses}" itemLabel="name" itemValue="statusId"/></td>
                        <td class="noBorder"><form:errors path="status"/></td>
                    </tr>
                    <tr>

                        <td align="right" colspan="3"><a href ="/tasks" class="buttonSteklo"><spring:message code="Back"/></a>&nbsp;&nbsp;<input type="submit" class="buttonSteklo" value='<spring:message code="SubmitDetail"/>'></td>

                    </tr>


                </table>

            </form:form>

</body>
</html>

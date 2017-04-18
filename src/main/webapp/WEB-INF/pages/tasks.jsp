<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
    <head>
        <title>ToDoList</title>
    </head>
    <body>
        <a href="../../index.jsp">Back to main menu</a>
        <br/>

        <h2>ToDoList</h2>
        <form action="/tasks" method="get">
            <button name="type" value='all'>Show All</button>
            <button name="type" value='done'>Show Done</button>
            <button name="type" value='undone'>Show Undone</button>
        </form>

        <c:if test="${empty listTasks}">
            No Tasks
        </c:if>
        <c:if test="${!empty listTasks}">
            <div class="pagination">
                <ul>
                    <label>Page:</label>
                    <c:forEach begin="${firstPage}" end="${lastPage}" var="p">
                        <a href="<c:url value="/tasks" ><c:param name="page" value="${p}"/>${p}</c:url>"> ${p} </a>
                    </c:forEach>
                </ul>
            </div>
        </c:if>


        <c:if test="${!empty listTasks}">
            <table>
                <tr>
                    <th width="540">Task</th>
                    <th width="60">Mark</th>
                    <th width="60">Edit</th>
                    <th width="60">Delete</th>
                </tr>
                <c:forEach items="${listTasks}" var="task">
                    <c:if test="${task.done == 'true'}"><tr bgcolor="#90ee90"></c:if>
                    <c:if test="${task.done == 'false'}"><tr bgcolor="#ffa07a"></c:if>
                        <td width="540">${task.taskData}</td>
                        <td align="center" width="60">
                            <c:if test="${task.done == 'true'}">
                                <a href="<c:url value='/changemark/${task.id}'/>">undone</a>
                            </c:if>
                            <c:if test="${task.done == 'false'}">
                                <a href="<c:url value='/changemark/${task.id}'/>">done</a>
                            </c:if>
                        </td>
                        <td align="center" width="60"><a href="<c:url value = '/edit/${task.id}'/>">edit</a></td>
                        <td align="center" width="60"><a href="<c:url value = '/remove/${task.id}'/>">delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:url var="addAction" value="/tasks/add"/>
        <form:form action="${addAction}" commandName="task">
            <table>
                <c:if test="${!empty task.taskData}">
                    <h2>Edit Task</h2>
                    <form:hidden path="id"/>
                    <form:hidden path="done"/>
                </c:if>
                <c:if test="${empty task.taskData}">
                    <h2>Add Task</h2>
                </c:if>
                <tr>
                    <td>
                        <form:label path="taskData"><spring:message text="Task"/></form:label>
                    </td>
                    <td>
                        <form:input path="taskData"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <c:if test="${!empty task.taskData}">
                            <input type="submit" value="<spring:message text="Edit Task"/>"/>
                        </c:if>
                        <c:if test="${empty task.taskData}">
                            <input type="submit" value="<spring:message text="Add Task"/>"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>

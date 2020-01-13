<%--

  Created by IntelliJ IDEA.
  User: Vusat
  Date: 18/11/2019
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Task</title>
</head>
<body>

            <h2>Update Page!</h2>
  <form:form  modelAttribute="task" action="update" method="POST">
      <form:hidden path="id"/>

        <form:errors path="taskName"/>
        <form:input path="taskName" id="taskName"  placeholder="Task Name"/> <br/>

        <form:errors path="description"/> <br/>
        <form:input path="description" id="description"  placeholder="Description"/> <br/>

        <form:errors path="startDate"/> <br/>
        <form:input path="startDate" id="start_date" type="date" placeholder="Start date"/>

        <form:errors path="endDate"/>
        <form:input path="endDate" id="end_date" type="date" placeholder="End date"/> <br/>

        <form:errors path="user_name"/> <br/>
        <form:input path="user_name" id="user_name" type="text" placeholder="User Name"/> <br/>

    <br/>
    <input type="submit" name="submit" value="submit"/> <br/>

   </form:form>
</body>
</html>

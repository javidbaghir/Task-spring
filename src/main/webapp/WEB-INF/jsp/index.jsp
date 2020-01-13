<%--
  Created by IntelliJ IDEA.
  User: Vusat
  Date: 13/11/2019
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Person Management</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"/>
</head>
<body>

<button>

    <a href="task_form">
        Add Task
    </a>
</button>

<hr/>

<table id="task-table">
    <thead>
    <tr>
        <td>ID</td>
        <td>Task Name</td>
        <td>Description</td>
        <td>Start Date</td>
        <td>End Date</td>
        <td>User Name</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#task-table').DataTable({
            "lengthMenu": [[5, 10, 20, 50, 100, -1], [5, 10, 20, 50, 100, 'Hamisi']],
            "processing": true,
            "serverSide": true,
            "ajax": "getPersonListAjax"
        });
    });
</script>
</html>

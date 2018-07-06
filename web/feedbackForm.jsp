<%-- 
    Document   : feedBack
    Created on : Jul 1, 2018, 8:48:16 PM
    Author     : Saisam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Form</title>
        <style>
            td{
                width: 100px
            }
        </style>
    </head>
    <body>
        <h1>Welcome,
            ${sessionScope.STUDENTNAME}<br/>
            <c:url var="logOutLink" value="logOut">
                <c:param name="username" value="${sessionScope.USERNAME}"/>
            </c:url>
            <font color="green"><a href="logOut">Log Out</a></font><br/><br/>    
            <c:url var="displayMarkTable" value="displayMarkTable">
                <c:param name="searchValue" value="${sessionScope.USERNAME}"/>
            </c:url>
            <a href=${displayMarkTable}>Back to Mark Table</a>
        </h1><br/>
        <h1>FeedBack Form</h1>
        Student ID: ${sessionScope.USERNAME}<br/>
        Student Name: ${sessionScope.STUDENTNAME}<br/><br/>
        Some marks of courses is not correct. Please, explain them for me
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Avg</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="feedBackAction" method="POST">
                <c:set var="result" value="${sessionScope.SUBJECTFEEDBACK}"/>
                <c:if test="${not empty result}" >
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.subjectID}</td>
                            <td>${dto.subjectName}</td>
                            <td>${dto.mark}</td>
                            <td>${dto.status}</td>
                            <td><input type="checkbox" name="chkSubjectRemoveFromFBForm" value="${counter.index}"/></td>
                        </tr>  
                    </c:forEach>
                </c:if>
                <tr>
                    <td colspan="5">
                        <input type="submit" value="Send" name="btAction" style="width: 600px"/> 
                        <input type="hidden" name="StudentID" value="${sessionScope.USERNAME}" />
                    </td>
                    <td>
                        <input type="submit" value="Remove" name="btAction" style="width: 100px"/>                                                
                    </td>
                </tr>  
            </form>    
        </tbody>
    </table>

</body>
</html>

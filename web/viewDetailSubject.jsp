<%-- 
    Document   : viewDetailSubject
    Created on : Jun 30, 2018, 7:19:27 PM
    Author     : Saisam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subjects' mark details</title>
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

        <c:set var="result" value="${requestScope.SUBJECTDETAIL}"/>
        <h1>Subject's mark details</h1>
        <h1>Subject ID:
            <c:if test="${not empty result}">
                ${result.get(1).subjectID}
            </c:if>
        </h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Subject Name</th>
                    <th>Block</th>
                    <th>Semester</th>
                    <th>Year</th>
                    <th>Status</th>
                    <th>Mark</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" varStatus="counter" items="${result}">
                    <tr>
                        <td>${counter.count} 
                            <c:set var="total" value="${counter.count} "/>
                        </td>
                        <td>${dto.subjectName}</td>
                        <td>${dto.block}</td>
                        <td>
                            <c:choose>
                                <c:when test="${dto.semester eq 3}">Fall</c:when>
                                <c:when test="${dto.semester eq 2}">Summer</c:when>
                                <c:otherwise>Spring</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${dto.year}</td>
                        <td>${dto.status}</td>
                        <td>${dto.mark}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="5">Number of Studying: ${total}</td>                        
                </tr>                    
            </tbody>
        </table>
    </body>
</html>

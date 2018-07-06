<%-- 
    Document   : showMarkTable
    Created on : Jun 29, 2018, 7:13:13 PM
    Author     : Saisam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mark Table</title>
        <style>
            td{
                width: 100px
            }
        </style>
    </head>
    <body>
        <h1>
            Welcome,
            ${sessionScope.STUDENTNAME}<br/>            
            <c:url var="logOutLink" value="logOut">
                <c:param name="username" value="${sessionScope.USERNAME}"/>
            </c:url>
            <font color="green"><a href="logOut">Log Out</a></font>            
        </h1><br/>
        <h2>MARK TABLE</h2>
        <h1>Subjects' mark details</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Subject Name</th>
                    <th>Block</th>
                    <th>Semester</th>
                    <th>Year</th>
                    <th>Avg</th>
                    <th>Status</th>
                    <th>Action</th>
                    <th>Action</th>                    
                </tr>
            </thead>
            <tbody>
            <form action="feedBack" method="POST">
                <c:set var="result" value="${sessionScope.MARKTABLESHOWDETAIL}"/>
                <c:if test="${not empty result}">
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>
                                ${counter.count} 
                                <c:set var="credits" value="${dto.credits + credits}"/>
                                <c:set var="count" value="${counter.count}"/>
                            </td>
                            <td>
                                ${dto.subjectName}
                            </td>
                            <td>
                                ${dto.block}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${dto.semester eq 3}">Fall</c:when>
                                    <c:when test="${dto.semester eq 2}">Summer</c:when>
                                    <c:otherwise>Spring</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                ${dto.year}
                            </td>
                            <td>
                                ${dto.mark}
                                <c:set var="GPA" value="${dto.mark + GPA}"/>
                            </td>
                            <td>
                                ${dto.status}
                            </td>
                            <td>
                                <c:url var="viewDetailSubject" value="viewDetailSubject">
                                    <c:param name="subjectID" value="${dto.subjectID}"/>
                                </c:url>
                                <a href=${viewDetailSubject}>View Detail</a>
                            </td>
                            <td>
                                <input type="checkbox" name="chkSubjectIDToFB" value="${dto.subjectID}"/>                                     
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <tr>
                    <td colspan="4">
                        Pass Credits: ${credits}
                    </td>
                    <td colspan="3">
                        GPA: ${GPA div count}
                    </td>
                    <td colspan="2">
                        <input type="submit" value="Send Feed Back"/>
                    </td>
                </tr>     
            </form>            
        </tbody>
    </table>
</body>
</html>

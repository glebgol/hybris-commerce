<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

<div style="font-size: ${fontSize}" class="content">
    <h2>Questions:</h2>

    <c:if test="${empty productQuestions}">
        <p>No questions were asked about this product</p>
    </c:if>

    <c:forEach items="${productQuestions}" var="question">
        <hr/>
        <h3>Question by ${question.questionCustomer}:</h3>
        <p>${question.question}</p>
        <c:if test="${not empty question.answer}">
            <h3>Answer by ${question.answerCustomer}: ${question.answer}</h3>
        </c:if>
        <hr/>
    </c:forEach>
</div>

</body>
</html>

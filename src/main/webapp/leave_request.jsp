<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Leave Request</title>
    </head>
    <body>
        <style>
            .required {
                color: crimson;
            }
        </style>
        <div align="right" style="float: right; display: inline-block;">
            <hr/>
            <c:out value="Hello, ${user}!"/>
            <hr/>
        </div>

        <!-- to be changed -->
        <div align="right" style="float: right; display: inline-block;">
            <hr/>
                <a href ="Controller?command=logout">Logout</a>
            <hr/>
        </div>
        <div align="center">
            <form name=registrationForm method = "POST" action="Controller">
                <input type = "hidden" name = "command" value = "submit_application"/>
                Name of product that needs repairing<span class="required">*</span>
                <input type="text" name="product_name" required/><br/>
                Additional information:<br/>
                <input type="text" name="product_comment"/><br/>
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </body>
</html>

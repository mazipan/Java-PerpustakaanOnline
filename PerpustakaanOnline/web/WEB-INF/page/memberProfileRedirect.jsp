<%-- 
    Created on : May 21, 2014, 9:54:52 AM
    Author     : Anonymous
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Perpustakaan online</title>
        <link rel="icon" type="image/ico" href="./images/favicon.ico" />    
        <link rel="stylesheet" href="css/style-loginchoice.css"  type="text/css" />

    </head>

    <body>
        <div id="contents">
            <h1 style="color:red; text-align: center; vertical-align: central; font-size: 20px; padding-top: 250px;">${msg}</h1>
            <p style="text-align: center;">please re-login when you're finish update your profile</p>
            <p style="text-align: center;"><a href="controler?/action=logout">click here</a> if your browser does not automatically redirect.</p>
        </div><!--div contents-->

        <script src="js/jquery.js"></script>
        <script>
            $(function() {
                setTimeout(function() {
                    window.location.href = "controler?/action=logout";
                }, 3000);
            });
        </script>

    </body>
</html>

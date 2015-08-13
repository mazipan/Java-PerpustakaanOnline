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
            <h1 style="color:red; text-align: center; vertical-align: central; font-size: 30px; padding-top: 250px;">${msg}</h1>
            <c:if test="${isAdmin!=null}">
                <p style="text-align: center;"><a href="controler?/action=adminuserman&is=${isAdmin}">click here</a> if your browser does not automatically redirect.</p>
            </c:if>
            <c:if test="${isMember!=null}">
                <p style="text-align: center;"><a href="controler?/action=adminuserman&is=${isMember}">click here</a> if your browser does not automatically redirect.</p>
            </c:if>
        </div><!--div contents-->

        <script src="js/jquery.js"></script>
        <script>
            $(function() {
                //setTimeout(redirectPage(), 5000);
                redirectPage();
                function redirectPage() {
                    var member = "${isMember}";
                    var admin = "${isAdmin}";
                    if (member !== "") {
                        setTimeout(function() {
                            window.location.href = "controler?/action=adminuserman&is=" + member + "";
                        }, 5000);
                        //window.location.href = "controler?/action=adminuserman&is=" + member + "";
                    } else if (admin !== "") {
                        setTimeout(function() {
                            window.location.href = "controler?/action=adminuserman&is=" + admin + "";
                        }, 5000);
                        //window.location.href = "controler?/action=adminuserman&is=" + admin + "";
                    }
                }
            });
        </script>

    </body>
</html>

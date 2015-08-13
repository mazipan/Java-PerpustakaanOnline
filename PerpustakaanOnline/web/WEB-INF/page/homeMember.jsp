<%-- 
    Created on : May 21, 2014, 9:54:52 AM
    Author     : Anonymous
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Perpustakaan online</title>
        <link rel="icon" type="image/ico" href="./images/favicon.ico" />    
        <link rel="stylesheet" href="css/style-loginchoice.css"  type="text/css" />	
        <link rel="stylesheet" href="css/style7.css" type="text/css"/>
        <link rel="stylesheet" href="css/login-reset.css" type="text/css" />
    </head>

    <body>

        <div> <!-- .wrapper -->

            <div id="header">
                <div id="header-logout" >
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;" ><a href="controler?/action=logout" class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white;">Q</a>Logout</p>
                </div>

                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasmember.memberName}</p>
                </div>
            </div>

            <div id="contents">

                <ul class="ca-menu" style="padding-left:18%; width: 70%;">
                    <li id="NewsUpdate">
                        <a href="controler?/action=membernewsupdate">
                            <span class="ca-icon">B</span>
                            <div class="ca-content">
                                <h2 class="ca-main">News Update</h2>
                                <h3 class="ca-sub">Information from Administrator</h3>
                            </div>
                        </a>
                    </li>

                    <li id="BookStore">
                        <a href="controler?/action=memberbookstore">
                            <span class="ca-icon">D</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Book Store</h2>
                                <h3 class="ca-sub">Search Your Book</h3>
                            </div>
                        </a>
                    </li>

                    <li id="My Transaction">
                        <a href="controler?/action=membertransaction">
                            <span class="ca-icon">J</span>
                            <div class="ca-content">
                                <h2 class="ca-main">My Transaction</h2>
                                <h3 class="ca-sub">History of My Transaction</h3>
                            </div>
                        </a>
                    </li>

                    <li id="My Profile">
                        <a href="controler?/action=memberprofil">
                            <span class="ca-icon">U</span>
                            <div class="ca-content">
                                <h2 class="ca-main">My Profile</h2>
                                <h3 class="ca-sub">Look My Identity</h3>
                            </div>
                        </a>
                    </li>

                </ul>

            </div><!--div container-->

        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script>
            $(function() {

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });


            });
        </script>


    </body>
</html>

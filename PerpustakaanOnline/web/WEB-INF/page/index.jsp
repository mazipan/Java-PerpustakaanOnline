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
        <link href="css/style-loginchoice.css" rel="stylesheet" type="text/css" />	

        <link rel="stylesheet" href="css/animate.css" type="text/css" />
        <link rel="stylesheet" href="css/login-reset.css" type="text/css" />
        <link rel="stylesheet" href="css/login-styles.css" type="text/css" />

    </head>
    <body>
        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white;">Login as Member</p>
                </div>
                <div id="header-col2">
                    <p style="text-align:center; padding-top:15px; color:white;" >Administrator</p>
                </div>
            </div>

            <div id="contents">
                <h1 style="text-align: center; vertical-align: central; font-size: 20px; padding-top: 250px;">${msg}</h1>
            </div><!--div contents-->

        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script>
            $(function() {

                // action login as member
                $("#header-col1").click(function() {
                    $("#contents").html('' +
                            '<div id="container">' +
                            '	<form id="form1" name="form1">' +
                            '		<label for="name">Username</label>' +
                            '		<input type="name" name="nama" />' +
                            '		<label for="username">Password</label>' +
                            '		<input type="password" name="nik" />' +
                            '		<div id="lower" style="	background: #ecf2f5; width: 100%; height: 58px; margin-top: 31px; box-shadow: inset 0 1px 1px #fff;' +
                            '		border-top: 1px solid #ccc;	border-bottom-right-radius: 3px; border-bottom-left-radius: 3px;">' +
                            '			<input type="submit" value="Login" id="login-member" formmethod="post" formaction="controler?/action=loginmember" />' +
                            '		</div>' +
                            '	</form>' +
                            '</div>');
                });

                // action login as admin
                $("#header-col2").click(function() {
                    $("#contents").html('' +
                            '<div id="container">' +
                            '	<form id="form2" name="form2">' +
                            '		<label for="name">Username</label>' +
                            '		<input type="name" name="username" />' +
                            '		<label for="username">Password</label>' +
                            '		<input type="password" name="password" />' +
                            '		<div id="lower" style="	background: #ecf2f5; width: 100%; height: 58px; margin-top: 31px; box-shadow: inset 0 1px 1px #fff;' +
                            '		border-top: 1px solid #ccc;	border-bottom-right-radius: 3px; border-bottom-left-radius: 3px;">' +
                            '			<input type="submit" value="Login" id="login-admin" formmethod="post" formaction="controler?/action=loginadmin" />' +
                            '		</div>' +
                            '	</form>' +
                            '</div>');
                });

            });
        </script>

    </body>
</html>

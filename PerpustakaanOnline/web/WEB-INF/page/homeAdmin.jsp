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
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;"><a href="controler?/action=logout" class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white;">Q</a>Logout</p>
                </div>
                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasadmin.truename}</p>
                </div>
            </div>

            <div id="contents">

                <ul class="ca-menu">
                    <li id="CategoryManagement">
                        <a href="controler?/action=admincategory">
                            <span class="ca-icon">F</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Book Category</h2>
                                <h3 class="ca-sub">Managing Book Category</h3>
                            </div>
                        </a>
                    </li>
                    <li id="BookManagement">
                        <a href="controler?/action=adminbook">
                            <span class="ca-icon">D</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Book Management</h2>
                                <h3 class="ca-sub">Managing Book Data</h3>
                            </div>
                        </a>
                    </li>                  
                    <li id="UserManagement">
                        <a href="controler?/action=adminuserman">
                            <span class="ca-icon">U</span>
                            <div class="ca-content">
                                <h2 class="ca-main">User Management</h2>
                                <h3 class="ca-sub">Managing User and Member Data</h3>
                            </div>
                        </a>
                    </li>
                    <li id="TransactionData">
                        <a href="controler?/action=admintransaction">
                            <span class="ca-icon">J</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Transaction Data</h2>
                                <h3 class="ca-sub">History of Transaction</h3>
                            </div>
                        </a>
                    </li>
                    <li id="Denda">
                        <a href="controler?/action=adminnewsupdate">
                            <span class="ca-icon">B</span>
                            <div class="ca-content">
                                <h2 class="ca-main">News Update</h2>
                                <h3 class="ca-sub">Check And Make News Update</h3>
                            </div>
                        </a>
                    </li>
                    <li id="Reporting">
                        <a href="controler?/action=adminreport">
                            <span class="ca-icon">M</span>
                            <div class="ca-content">
                                <h2 class="ca-main">Reporting</h2>
                                <h3 class="ca-sub">Data Analyzing</h3>
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

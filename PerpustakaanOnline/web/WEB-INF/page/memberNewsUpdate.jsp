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
        <link rel="stylesheet" href="css/login-reset.css" type="text/css" />	

    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>Home</p>
                </div>

                <div id="header-logout" >
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;" ><a href="controler?/action=logout" class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white;">Q</a>Logout</p>
                </div>

                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasmember.memberName}</p>
                </div>
            </div>

            <div id="contents">

                <div class="content-module ">

                    <div class="content-module-heading cf">
                        <h3 class="fl">Last Update Book by Administrator</h3>
                        <span class="fr expand-collapse-text" style="cursor: pointer; ">Click to collapse</span>
                        <span class="fr expand-collapse-text initial-expand" style="cursor: pointer; ">Click to expand</span>
                    </div>

                    <div class="content-module-main">
                        <table style="font-size: 20px; width: 100%;">
                            <tr>
                                <td colspan="3"><label style="color: red;">Administrator just added new book</label></td>
                            </tr>  
                            <tr>
                                <td style="width: 100px;"><label>Book Tittle</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${bookupdate.bookTitle}</td>
                            </tr>    
                            <tr>
                                <td style="width: 100px;"><label>Category</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${bookupdate.category.categoryName}</td>
                            </tr>    
                            <tr>
                                <td style="width: 100px;"><label>Author</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${bookupdate.pengarang}</td>
                            </tr>                                
                        </table>
                    </div> <!--div content-module-main-->

                    <div class="content-module-heading cf">
                        <h3 class="fl">Activities</h3>
                        <span class="fr expand-collapse-text" style="cursor: pointer; ">Click to collapse</span>
                        <span class="fr expand-collapse-text initial-expand" style="cursor: pointer; ">Click to expand</span>
                    </div>

                    <div class="content-module-main">
                        <table style="font-size: 20px; width: 100%;">
                            <tr>
                                <td colspan="3"><label style="color: red;">Administrator just make new activities</label></td>
                            </tr>  
                            <tr>
                                <td style="width: 100px;"><label>Activities</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${newsupdate.newsName}</td>
                            </tr>    
                            <tr>
                                <td style="width: 100px;"><label>Place</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${newsupdate.newsPlace}</td>
                            </tr>    
                            <tr>
                                <td style="width: 100px;"><label>Datetime</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${newsupdate.newsDateTimeStrItalic}</td>
                            </tr>    
                            <tr>
                                <td style="width: 100px;"><label>Description</label></td><td style="width: 10px;">:</td><td style="text-align: left;">${newsupdate.newsDesc}</td>
                            </tr> 
                            <tr>
                                <td colspan="3"><label style="font-size: 12pt; color: red;">*) Please participate if you have a time</label></td>
                            </tr>                             
                        </table>
                    </div> <!--div content-module-main-->

                </div> <!--div content-module-->
            </div><!--div container-->

        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script src="js/expandFunction.js"></script>
        <script src="js/custom-function.js"></script>
        <script>
            $(function() {
                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=homemember";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

            });
        </script>


    </body>
</html>

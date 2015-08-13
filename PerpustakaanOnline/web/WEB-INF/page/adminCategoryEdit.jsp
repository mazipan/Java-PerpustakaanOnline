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
        <link rel="stylesheet" href="css/form.css" type="text/css">	

    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>Category</p>
                </div>

                <div id="header-logout" >
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;" ><a href="controler?/action=logout" class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white;">Q</a>Logout</p>
                </div>

                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasadmin.truename}</p>
                </div>
            </div>

            <div id="contents">
                <div class="content-module ">
                    <div class="content-module-heading cf">
                        <h3 class="fl">Add Or Edit Category Form</h3>
                    </div>
                    <div class="content-module-main">    
                        <form name="myform" id="myform">
                            <table class="fl">
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>Book Category Name : </span></td><td><input type="text" name="categoryName" value="${categoryforedit.gmCategory}" /></td> 
                                </tr> 
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td>
                                        <div id="myform_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                        </div>
                                    </td>  
                                </tr> 
                                <tr style="text-align: left; padding-left: 0px;">

                                    <c:if test="${categoryforedit!=null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=admincategorysave&id=${categoryforedit.idCategory}" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">Update Category</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${categoryforedit==null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=admincategorysave" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">SAVE NEW CATEGORY</button>
                                        </td>
                                    </c:if>

                                </tr>

                            </table>
                        </form>
                    </div> <!--div content-module-main-->
                </div> <!--div content-module-->
            </div><!--div contents-->
        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script src="js/gen_validatorv4.js"></script>
        <script>
            $(function() {
                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=admincategory";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });


                var frmvalidator = new Validator("myform");
                frmvalidator.EnableOnPageErrorDisplaySingleBox();
                frmvalidator.EnableMsgsTogether();

                frmvalidator.addValidation("categoryName", "req", "*) Categoty name can not be empty");
                frmvalidator.addValidation("categoryName", "maxlen=50", "*) Max length for category name is 50");

            });
        </script>


    </body>
</html>

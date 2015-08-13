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
        <link  rel="stylesheet" href="css/bootstrap-combined.min.css" type="text/css">
        <link rel="stylesheet"  media="screen" href="css/bootstrap-datetimepicker.min.css" type="text/css">
        <link rel="stylesheet" href="css/form.css" type="text/css">

    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><span class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</span>News Update</p>
                </div>

                <div id="header-logout" >
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;" ><span class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white; text-decoration: none;">Q</span>Logout</p>
                </div>

                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasadmin.truename}</p>
                </div>
            </div>

            <div id="contents">
                <div class="content-module ">
                    <div class="content-module-heading cf">
                        <span class="fl" style="font-family: 'BebasNeueRegular', 'Arial Narrow', Arial, sans-serif; color: white; font-size: 16px;">Add Or Edit News Update Form</span>
                    </div>
                    <div class="content-module-main">    
                        <form name="myform" id="myform">
                            <table class="fl">
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>News Name : </span></td><td><input type="text" name="newsName" value="${newsforedit.newsName}" /></td> 
                                </tr>  
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>News Place : </span></td><td><textarea name="newsPlace" value="${newsforedit.newsPlace}">${newsforedit.newsPlace}</textarea></td> 
                                </tr>     
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>News Datetime : </span></td>   
                                    <td>
                                        <div id="datetimepicker" class="input-append date" data-date-format="YYYY/MM/DD">
                                            <input style="margin-top: 0px; margin-bottom: 0px;" type="text" name="newsDateTime" value="${newsforedit.newsDateTimeStrItalic}"></input>
                                            <span class="add-on">
                                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                            </span>
                                        </div>
                                    </td>
                                </tr>     
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>News Description : </span></td><td><textarea name="newsDesc" value="${newsforedit.newsDesc}">${newsforedit.newsDesc}</textarea></td> 
                                </tr>     

                                <tr style="text-align: left; padding-left: 0px;">
                                    <td>
                                        <div id="myform_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                        </div>
                                    </td>  
                                </tr> 

                                <tr style="text-align: left; padding-left: 0px;">

                                    <c:if test="${newsforedit!=null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminnewsupdatesave&id=${newsforedit.idNews}" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">Update Category</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${newsforedit==null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminnewsupdatesave" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">SAVE NEW CATEGORY</button>
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
        <script src="js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/gen_validatorv4.js"></script>		        
        <script>
            $(function() {
                $('#datetimepicker').datetimepicker({
                    format: 'dd/MM/yyyy hh:mm:ss',
                    language: 'en'
                });

                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=adminnewsupdate";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                var frmvalidator = new Validator("myform");
                frmvalidator.EnableOnPageErrorDisplaySingleBox();
                frmvalidator.EnableMsgsTogether();

                frmvalidator.addValidation("newsName", "req", "*) Name can not be empty");
                frmvalidator.addValidation("newsName", "maxlen=50", "*) Max length for name is 50");

                frmvalidator.addValidation("newsPlace", "req", "*) Place can not be empty");
                frmvalidator.addValidation("newsPlace", "maxlen=50", "*) Max length for place is 50");

                frmvalidator.addValidation("newsDateTime", "req", "*) Datetime can not be empty");

                frmvalidator.addValidation("newsDesc", "req", "*) Description can not be empty");
                frmvalidator.addValidation("newsDesc", "maxlen=1000", "*) Max length for description is 1000");
            });
        </script>


    </body>
</html>

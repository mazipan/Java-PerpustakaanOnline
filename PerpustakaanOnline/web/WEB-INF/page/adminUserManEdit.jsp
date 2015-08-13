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
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>Admin User</p>
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
                        <h3 class="fl">Add Or Edit Member Form</h3>
                    </div>
                    <div class="content-module-main">    
                        <form name="myform" id="myform">
                            <table class="fl">
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>NIK : </span></td><td><input type="text" name="memberNik" value="${userforedit.memberNik}" /></td> 
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>NAMA : </span></td><td><input type="text" name="memberName" value="${userforedit.memberName}" /></td> 
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>ALAMAT : </span></td><td><input type="text" name="memberAlamat" value="${userforedit.memberAlamat}" /></td>  
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>TELEPON : </span></td><td><input type="text" name="memberTelp" value="${userforedit.memberTelp}" /></td>   
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>EXT : </span></td><td><input type="text" name="memberExt" value="${userforedit.memberExt}" /></td>   
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>BAGIAN : </span></td><td><input type="text" name="memberBagian" value="${userforedit.memberBagian}" /></td>   
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>LOKASI : </span></td><td><input type="text" name="memberLokasi" value="${userforedit.memberLokasi}" /></td>   
                                </tr>

                                <tr style="text-align: left; padding-left: 0px;">
                                    <td>
                                        <div id="myform_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                        </div>
                                    </td>  
                                </tr> 

                                <tr style="text-align: left; padding-left: 0px;">
                                    <c:if test="${userforedit!=null}">
                                        <td></td><td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminusermansave&id=${userforedit.memberId}" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">UPDATE MEMBER</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${userforedit==null}">
                                        <td></td><td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminusermansave" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">SAVE NEW MEMBER</button>
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
                    window.location.href = "controler?/action=adminuserman";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                var frmvalidator = new Validator("myform");
                frmvalidator.EnableOnPageErrorDisplaySingleBox();
                frmvalidator.EnableMsgsTogether();

                frmvalidator.addValidation("memberNik", "req", "*) NIK can not be empty");
                frmvalidator.addValidation("memberNik", "maxlen=10", "*) Max length for NIK is 10");

                frmvalidator.addValidation("memberName", "req", "*) Member name can not be empty");
                frmvalidator.addValidation("memberName", "maxlen=20", "*) Max length for member name is 20");

                frmvalidator.addValidation("memberAlamat", "req", "*) Alamat can not be empty");
                frmvalidator.addValidation("memberAlamat", "maxlen=50", "*) Max length for alamat is 50");

                frmvalidator.addValidation("memberTelp", "maxlen=15", "*) Max length for telepon is 15");

                frmvalidator.addValidation("memberExt", "maxlen=15", "*) Max length for Ext is 15");

                frmvalidator.addValidation("memberBagian", "req", "*) Bagian can not be empty");
                frmvalidator.addValidation("memberBagian", "maxlen=20", "*) Max length for bagian is 20");

                frmvalidator.addValidation("memberLokasi", "req", "*) Lokasi can not be empty");
                frmvalidator.addValidation("memberLokasi", "maxlen=20", "*) Max length for lokasi is 20");

            });
        </script>


    </body>
</html>

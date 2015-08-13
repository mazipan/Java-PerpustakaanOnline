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
        <link rel="stylesheet" href="css/style7.css" type="text/css"/>	
        <link rel="stylesheet" href="css/form.css" type="text/css">	

    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a href="controler?/action=homeadmin" class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>HOME</p>
                </div>

                <div id="header-logout" >
                    <p style="text-align:center; padding-top:20px; color:white; cursor:pointer;" ><a href="controler?/action=logout" class="font-icon" style="text-align:center; vertical-align: central; padding-right:15px; color:white;">Q</a>Logout</p>
                </div>

                <div style="float:right; height:50px;">
                    <p style="text-align:center; padding-top:20px; padding-right:15px; color:white; " >Login as ${loginasadmin.truename}</p>
                </div>
            </div>

            <div id="contents">

                <div class="content-module" id="content-module">

                    <div id="headerLineMember" class="content-module-heading cf">
                        <h3 class="fl">Reporting</h3>
                        <span class="fr expand-collapse-text" style="cursor: pointer; ">Click to collapse</span>
                        <span class="fr expand-collapse-text initial-expand" style="cursor: pointer; ">Click to expand</span>
                    </div>

                    <div id="bodyLineMember" class="content-module-main">                       
                        <ul class="ca-menu" style="padding-left:25%; width: 70%;" id="menuReport">                            
                            <li>
                                <div id="BookReport">
                                    <span class="ca-icon">D</span>
                                    <div class="ca-content">
                                        <h2 class="ca-main">Book Report</h2>
                                        <h3 class="ca-sub">Reporting Book Data</h3>
                                    </div>
                                </div>
                            </li>                  
                            <li>
                                <div id="UserReport">
                                    <span class="ca-icon">U</span>
                                    <div class="ca-content">
                                        <h2 class="ca-main">User Report</h2>
                                        <h3 class="ca-sub">Reporting User Data</h3>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div id="TransactionReport">
                                    <span class="ca-icon">J</span>
                                    <div class="ca-content">
                                        <h2 class="ca-main">Transaction Report</h2>
                                        <h3 class="ca-sub">Reporting Transaction Data</h3>
                                    </div>
                                </div>
                            </li>                            
                        </ul>

                        <div id="book">
                            <form name="myform" id="myform">                                
                                <input type="radio" name="option" id="1" value="1"/><label for="1">List all books</label></br>
                                <input type="radio" name="option" id="2" value="2"/><label for="2">List all book that borrowed</label></br>
                                <input type="radio" name="option" id="3" value="3"/><label for="3">List all book that not borrowed</label></br>
                                <div id="myform_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                </div></br>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xls" class="button-bevel cyan">Excel 2003 (*.xls)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xlsx" class="button-bevel cyan">Excel 2007 and above (*.xlsx)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=pdf" class="button-bevel cyan">Pdf (*.pdf)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=csv" class="button-bevel cyan">CSV (*.csv)</button>
                            </form>
                        </div>
                        <div id="user">
                            <form name="myform1" id="myform1">
                                <input type="radio" name="option" id="11" value="11"/><label for="11">List all administrator</label></br>
                                <input type="radio" name="option" id="12" value="12"/><label for="12">List all member</label></br>
                                <div id="myform1_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                </div></br>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xls" class="button-bevel cyan">Excel 2003 (*.xls)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xlsx" class="button-bevel cyan">Excel 2007 and above (*.xlsx)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=pdf" class="button-bevel cyan">Pdf (*.pdf)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=csv" class="button-bevel cyan">CSV (*.csv)</button>
                            </form>
                        </div>
                        <div id="transaction">
                            <form name="myform2" id="myform2">
                                <input type="radio" name="option" id="21" value="21"/><label for="21">List all transaction</label></br>
                                <input type="radio" name="option" id="22" value="22"/><label for="22">List all transaction that have not return</label></br>
                                <input type="radio" name="option" id="23" value="23"/><label for="23">List all transaction that have return</label></br>
                                <input type="radio" name="option" id="24" value="24"/><label for="24">List all transaction that get penalty</label></br>
                                <input type="radio" name="option" id="25" value="25"/><label for="25">List all transaction that not get penalty</label></br>
                                <!--<input type="radio" name="option" id="26" value="26"/><label for="26">List all transaction by member</label></br>-->
                                <div id="myform2_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                </div></br>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xls" class="button-bevel cyan">Excel 2003 (*.xls)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=xlsx" class="button-bevel cyan">Excel 2007 and above (*.xlsx)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=pdf" class="button-bevel cyan">Pdf (*.pdf)</button>
                                <button type="submit" formmethod="POST" formaction="controler?/action=admingeneratereport&type=csv" class="button-bevel cyan">CSV (*.csv)</button>
                            </form>
                        </div>

                    </div> <!--div content-module-main-->


                </div> <!--div content-module-->


            </div><!--div contents-->

        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script src="js/expandFunction.js"></script>
        <script src="js/gen_validatorv4.js"></script>

        <script>
            $(function() {
                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=homeadmin";
                });

                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                var chktestValidator = new Validator("myform");
                chktestValidator.EnableOnPageErrorDisplaySingleBox();
                chktestValidator.EnableMsgsTogether();
                chktestValidator.addValidation("option", "selone", "*) Please select one");


                var chktestValidator1 = new Validator("myform1");
                chktestValidator1.EnableOnPageErrorDisplaySingleBox();
                chktestValidator1.EnableMsgsTogether();
                chktestValidator1.addValidation("option", "selone", "*) Please select one");


                var chktestValidator2 = new Validator("myform2");
                chktestValidator2.EnableOnPageErrorDisplaySingleBox();
                chktestValidator2.EnableMsgsTogether();
                chktestValidator2.addValidation("option", "selone", "*) Please select one");

                $("#BookReport").click(function() {
                    showBook(true);
                });

                $("#UserReport").click(function() {
                    showUser(true);
                });

                $("#TransactionReport").click(function() {
                    showTrans(true);
                });



                $(window).load(function() {
                    showMain();
                });
                function showMain() {
                    $("#menuReport").show();
                    $("#book").hide();
                    $("#transaction").hide();
                    $("#user").hide();
                }

                function showBook(isShow) {
                    if (isShow === true) {
                        $("#book").show();
                        $("#menuReport").hide();
                        $("#transaction").hide();
                        $("#user").hide();
                    } else {
                        showMain();
                    }
                }

                function showUser(isShow) {
                    if (isShow === true) {
                        $("#book").hide();
                        $("#menuReport").hide();
                        $("#transaction").hide();
                        $("#user").show();
                    } else {
                        showMain();
                    }
                }

                function showTrans(isShow) {
                    if (isShow === true) {
                        $("#book").hide();
                        $("#menuReport").hide();
                        $("#user").hide();
                        $("#transaction").show();
                    } else {
                        showMain();
                    }
                }

            });
        </script>

    </body>
</html>

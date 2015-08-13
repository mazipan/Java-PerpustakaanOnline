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
        <link rel="stylesheet" href="css/dropdown.css" type="text/css" />	
        <link rel="stylesheet" href="css/form.css" type="text/css">

    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>Admin Book</p>
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
                        <h3 class="fl">Add Or Edit Book Form</h3>
                    </div>
                    <div class="content-module-main">    
                        <form name="myform" id="myform">
                            <table class="fl">
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td style="vertical-align: central"><span style="vertical-align: central">Book Category : </span></td>
                                    <td>
                                        <div style="padding-left: 20px;">
                                            <div class="dropdown dropdown-dark">
                                                <select id="filtercategory" class="dropdown-select" name="bookCategory">
                                                    <c:forEach items="${categoryforedit}" var="cat">  
                                                        <option value=${cat.idCategory}>${cat.categoryName}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <!--<input type="text" name="bookCategory" value="${bookforedit.category.gmCategory}" /> -->
                                    </td> 
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>Book Tittle : </span></td><td><input type="text" name="bookTitle" value="${bookforedit.bookTitle}" /></td> 
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td><span>Book Author : </span></td><td><input type="text" name="bookPengarang" value="${bookforedit.pengarang}" /></td>  
                                </tr>
                                <tr style="text-align: left; padding-left: 0px;">
                                    <td style="vertical-align: central"><span style="vertical-align: central">Book Year : </span></td>
                                    <td>
                                        <div style="padding-left: 20px;">
                                            <div class="dropdown dropdown-dark">
                                                <select id="filteryear" class="dropdown-select" name="bookYear">
                                                    <c:forEach items="${yearforedit}" var="year">  
                                                        <option value=${year}>${year}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <!--<input type="text" name="bookYear" value="${bookforedit.bookYear}" /></td> -->
                                    </td> 

                                </tr>

                                <tr style="text-align: left; padding-left: 0px;">
                                    <td>
                                        <div id="myform_errorloc" class="error_strings" style="color: red; font-size: 13px;">
                                        </div>
                                    </td>  
                                </tr> 

                                <tr style="text-align: left; padding-left: 0px;">

                                    <c:if test="${bookforedit!=null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminbooksave&id=${bookforedit.idBook}" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">UPDATE BOOK</button>
                                        </td>
                                    </c:if>
                                    <c:if test="${bookforedit==null}">
                                        <td></td>
                                        <td>
                                            <button type="submit" formmethod="POST" formaction="controler?/action=adminbooksave" class="button-bevel cyan" style="padding-left: 10px; margin: 10px; margin-top: 12px; margin-left: 18px;">SAVE NEW BOOK</button>
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
        <script src="js/custom-function.js"></script>
        <script src="js/gen_validatorv4.js"></script>
        <script>
            $(function() {
                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=adminbook";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                var frmvalidator = new Validator("myform");
                frmvalidator.EnableOnPageErrorDisplaySingleBox();
                frmvalidator.EnableMsgsTogether();

                frmvalidator.addValidation("bookTitle", "req", "*) Book tittle can not be empty");
                frmvalidator.addValidation("bookTitle", "maxlen=50", "*) Max length for book tittle is 50");

                frmvalidator.addValidation("bookPengarang", "req", "*) Book author can not be empty");
                frmvalidator.addValidation("bookPengarang", "maxlen=50", "*) Max length for book author is 20");

                $(window).load(function() {
                    setTimeout(setValueDropdown(), 1000);
                });

                function setValueDropdown() {
                    var dropCat = document.getElementById('filtercategory');
                    var catName = "${bookforedit.category.categoryName}";
                    setSelectedValue(dropCat, catName);

                    var dropYear = document.getElementById('filteryear');
                    var yearSelect = "${bookforedit.bookYear}";
                    setSelectedValue(dropYear, yearSelect);
                }

            });
        </script>

    </body>
</html>

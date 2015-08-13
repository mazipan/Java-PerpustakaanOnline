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
        <link rel="stylesheet" href="css/table-style.css" type="text/css" />	
    </head>

    <body>

        <div> <!-- .wrapper -->
            <div id="header">
                <div id="header-col1">
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a href="controler?/action=homemember" class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>HOME</p>
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
                        <h3 class="fl">My Transaction List</h3>
                        <span class="fr expand-collapse-text" style="cursor: pointer; ">Click to collapse</span>
                        <span class="fr expand-collapse-text initial-expand" style="cursor: pointer; ">Click to expand</span>
                    </div>
                    <div class="content-module-main">
                        <table>
                            <thead>						
                                <tr>
                                    <th>NO</th>
                                    <th>BOOK TITLE</th>
                                    <th>TANGGAL PINJAM</th>
                                    <th>TANGGAL KEMBALI</th>
                                    <th>TANGGAL PENGEMBALIAN</th>
                                    <th>STATUS PINJAM</th>
                                    <th>STATUS DENDA</th>

                                </tr>							
                            </thead>

                            <tbody>
                                <%int i = 1;%>
                                <c:forEach items="${peminjamans}" var="peminjaman">    
                                    <tr>
                                        <td>
                                            <%=i%>
                                        </td>
                                        <td>
                                            ${peminjaman.book.bookTitle}
                                        </td>
                                        <td>
                                            ${peminjaman.tglPinjam}
                                        </td>
                                        <td>
                                            ${peminjaman.tglKembali}
                                        </td>
                                        <td>
                                            ${peminjaman.tglPengembalian}
                                        </td>

                                        <c:choose>
                                            <c:when test="${peminjaman.isKembali==false}">
                                                <td style="color: red">
                                                    ${peminjaman.status}
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    ${peminjaman.status}
                                                </td>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${peminjaman.isDendaFake==true}">
                                                <td style="color: blue">
                                                    ${peminjaman.statusDendaFake}
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    ${peminjaman.statusDendaFake}
                                                </td>
                                            </c:otherwise>
                                        </c:choose>

                                    </tr>
                                    <%i++;%>
                                </c:forEach>
                            </tbody>


                            <tfoot>
                            <table>
                                <tr>
                                    <td colspan="5" class="table-footer">
                                        <label style="padding-left: 5px;">Page : </label>    
                                        <%--For displaying Previous link except for the 1st page --%>
                                        <c:if test="${currentPage != 1}">
                                            <a href="controler?/action=membertransaction&page=${currentPage - 1}">Previous </a>
                                        </c:if>
                                        <%--For displaying Page numbers. 
                                        The when condition does not display a link for the current page--%>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    ${i} 
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="controler?/action=membertransaction&page=${i}">${i} </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <%--For displaying Next link --%>
                                        <c:if test="${currentPage lt noOfPages}">
                                            <a href="controler?/action=membertransaction&page=${currentPage + 1}"> Next</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                            </tfoot>

                        </table>
                        <label style="color: red; font-size: 12pt">*) Show all history of your transaction</label></br>
                        <label style="color: red; font-size: 12pt">*) Please be aware with Status Pinjam column, text with red color means you have not return the book</label></br>
                        <label style="color: red; font-size: 12pt">*) Please return the book before 2 weeks from date you borrowed, If you return the book over 2 weeks you will get a penalty</label></br>
                        <label style="color: red; font-size: 12pt">*) Call your administrator when you have return the book</label></br>

                    </div> <!--div content-module-main-->

                </div> <!--div content-module-->


            </div><!--div contents-->

        </div><!-- .wrapper -->


        <script src="js/jquery.js"></script>
        <script src="js/expandFunction.js"></script>

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

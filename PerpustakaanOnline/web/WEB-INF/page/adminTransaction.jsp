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
        <link rel="stylesheet" href="css/dropdown.css" type="text/css" />	
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
                <div class="content-module ">
                    <div class="content-module-heading cf">
                        <h3 class="fl">Transaction List</h3>
                        <span class="fr expand-collapse-text" style="cursor: pointer; ">Click to collapse</span>
                        <span class="fr expand-collapse-text initial-expand" style="cursor: pointer; ">Click to expand</span>
                    </div>
                    <div class="content-module-main">
                        <table>
                            
                            <div style="float: left; width: 100%; height: 60px;">
                                <div style="float: left; padding-left: 20px; width: 12%; height: 60px;">
                                    <div class="dropdown dropdown-dark">
                                        <select id="filterby" class="dropdown-select">
                                            <option value="member">Member Name</option>
                                            <option value="book">Book Tittle</option>
                                        </select>
                                    </div>
                                </div>

                                <div style="float: left; width: 50%; height: 60px;" id="filterbyValue">
                                    <div style="float: left; width: 45%; height: 60px;" >
                                        <input type="text" id="textMember" name="textMember" value="${memberSearch}"></input>
                                        <input type="text" id="textBook" name="textBook" value="${memberBook}"></input>
                                    </div>

                                    <div style="float: left; width: 20%; height: 60px;" >
                                        <button id="btnFilterByMember" class="button-bevel cyan">Search</button>                                        
                                        <button id="btnFilterByBook" class="button-bevel cyan">Search</button>
                                    </div>

                                </div>
                            </div>    
                            
                            <thead>						
                                <tr>

                                    <th>NO</th>
                                    <th>MEMBER NAME</th>
                                    <th>BOOK TITLE</th>
                                    <th>TANGGAL PINJAM</th>
                                    <th>TANGGAL KEMBALI</th>
                                    <th>TANGGAL PENGEMBALIAN</th>
                                    <th>STATUS</th>
                                    <th>DENDA</th>
                                    <th>ACTION</th>

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
                                            ${peminjaman.member.memberName}
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
                                        <td>
                                            <c:if test="${peminjaman.isKembali}">
                                                <a href="controler?/action=admintransactionapply&id=${peminjaman.idPinjam}" class="table-actions-button ic-table-cancel" title="Tandai Belum dikembalikan"></a>
                                            </c:if>
                                            <c:if test="${!peminjaman.isKembali}">
                                                <a href="controler?/action=admintransactionapply&id=${peminjaman.idPinjam}" class="table-actions-button ic-table-apply" title="Tandai Sudah dikembalikan"></a>
                                            </c:if>                                                
                                            <a href="controler?/action=admintransactiondelete&id=${peminjaman.idPinjam}" class="table-actions-button ic-table-delete" title="Delete transaction"></a>
                                        </td>
                                    </tr>
                                    <%i++;%>
                                </c:forEach>
                            </tbody>

                            <tfoot>
                            <table>
                                <tr>
                                    <td colspan="5" class="table-footer">
                                        <span style="padding-left: 5px;">Page : </span>    
                                        <%--For displaying Previous link except for the 1st page --%>
                                        <c:if test="${currentPage != 1}">
                                            <a href="controler?/action=admintransaction&page=${currentPage - 1}">Previous </a>
                                        </c:if>
                                        <%--For displaying Page numbers. 
                                        The when condition does not display a link for the current page--%>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    ${i} 
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="controler?/action=admintransaction&page=${i}">${i} </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <%--For displaying Next link --%>
                                        <c:if test="${currentPage lt noOfPages}">
                                            <a href="controler?/action=admintransaction&page=${currentPage + 1}"> Next</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                            </tfoot>

                        </table>

                    </div> <!--div content-module-main-->

                </div> <!--div content-module-->


            </div><!--div contents-->

        </div><!-- .wrapper -->

        <script src="js/jquery.js"></script>
        <script src="js/expandFunction.js"></script>
        <script src="js/custom-function.js"></script>

        <script>
            $(function() {

                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=homeadmin";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                $(window).load(function() {
                    setValueDropdown();                    
                });

                function setValueDropdown() {
                    var filterby = document.getElementById('filterby');

                    var memberSearch = "${memberSearch}";
                    var bookSearch = "${bookSearch}";

                    if (memberSearch === "" && bookSearch === "") {
                        setSelectedIndex(filterby, 0);
                        showHide();
                    } else if (memberSearch !== "") {
                        setSelectedIndex(filterby, 0);
                        showHide();
                    } else if (bookSearch !== "") {
                        setSelectedIndex(filterby, 1);
                        showHide();
                    }
                }

                document.getElementById('filterby').onchange = function() {
                    showHide();
                };

                function showHide() {
                    var filter = $("#filterby").val();
                    
                    if (filter === 'member') {
                        $("#textBook").hide();
                        $("#btnFilterByBook").hide();

                        $("#textMember").show();
                        //$("#textMember").val("");
                        $("#btnFilterByMember").show();

                    } else if (filter === 'book') {
                        $("#textMember").hide();
                        $("#btnFilterByMember").hide();

                        $("#textBook").show();
                        //$("#textBook").val("");
                        $("#btnFilterByBook").show();

                    }
                }

                $("#btnFilterByBook").click(function() {
                    filterbyBook();
                });

                $("#btnFilterByMember").click(function() {
                    filterbyMember();
                });


                function filterbyBook() {
                    var book = $("#textBook").val();
                    window.location.href = "controler?/action=admintransactionbybook&filter=" + book + "";
                }

                function filterbyMember() {
                    var member = $("#textMember").val();
                    window.location.href = "controler?/action=admintransactionbymember&filter=" + member + "";
                }


            });
        </script>


    </body>
</html>

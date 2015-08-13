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
                        <h3 class="fl">Book List</h3>
                        <a href="controler?/action=adminbookedit" class="fr link" style="padding-right: 20px; padding-top: 20px;">Add New Book<font class="fr font-icon" style="padding-left: 10px;">+</font></a>
                    </div>
                    <div class="content-module-main">
                        <table>
                            <div style="float: left; width: 100%; height: 60px;">
                                <div style="float: left; padding-left: 20px; width: 12%; height: 60px;">
                                    <div class="dropdown dropdown-dark">
                                        <select id="filterby" class="dropdown-select">
                                            <option value="category">Category</option>
                                            <option value="year">Year</option>
                                        </select>
                                    </div>
                                </div>

                                <div style="float: left; width: 30%; height: 60px;" id="filterbyValue">
                                    <div style="float: left; width: 39%; height: 60px;" >
                                        <div class="dropdown dropdown-dark">
                                            <select id="filtercategory" class="dropdown-select">
                                                <c:forEach items="${categories}" var="cat">  
                                                    <option name="categoryselect" value=${cat.idCategory}>${cat.gmCategory}</option>
                                                </c:forEach>
                                            </select>  
                                            <select id="filteryear" class="dropdown-select">
                                                <c:forEach items="${listyear}" var="year">  
                                                    <option name="yearselect" value=${year}>${year}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                    </div>

                                    <div style="float: left; width: 20%; height: 60px;" >
                                        <button id="btnFilterByCategory" class="button-bevel cyan">Apply</button>                                        
                                        <button id="btnFilterByYear" class="button-bevel cyan">Apply</button>
                                    </div>

                                </div>
                            </div>    


                            <thead>						
                                <tr>

                                    <th>NO</th>
                                    <th>CATEGORY</th>
                                    <th>BOOK TITLE</th>
                                    <th>AUTHOR</th>
                                    <th>YEAR</th>
                                    <th>STOCK</th>
                                    <th>ACTION</th>

                                </tr>							
                            </thead>

                            <tbody>
                                <%int i = 1;%>
                                <c:forEach items="${booksadmin}" var="book">    
                                    <tr>
                                        <td>
                                            <%=i%>
                                        </td>
                                        <td>
                                            ${book.category.categoryName}
                                        </td>
                                        <td>
                                            ${book.bookTitle}
                                        </td>
                                        <td>
                                            ${book.pengarang}
                                        </td>
                                        <td>
                                            ${book.bookYear}
                                        </td>
                                        <td>
                                            ${book.stock}
                                        </td>
                                        <td>
                                            <a href="controler?/action=adminbookedit&id=${book.idBook}" class="table-actions-button ic-table-edit" title="Edit Book"></a>
                                            <a href="controler?/action=adminbookdelete&id=${book.idBook}" class="table-actions-button ic-table-delete" title="Delete Book"></a>
                                        </td>
                                    </tr>
                                    <%i++;%>
                                </c:forEach>
                            </tbody>

                            <tfoot>
                            <table >   
                                <tr><td colspan="5" class="table-footer" id="bookfooter"></td></tr>
                            </table>
                            </tfoot>

                        </table>

                    </div> <!--div content-module-main-->

                </div> <!--div content-module-->


            </div><!--div contents-->

        </div><!-- .wrapper -->

        <script src="js/jquery.js"></script>
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

                document.getElementById('filterby').onchange = function() {
                    showHide();
                };

                function showHide() {
                    var filter = $("#filterby").val();

                    if (filter === 'category') {
                        $("#filteryear").hide();
                        $("#btnFilterByYear").hide();

                        $("#filtercategory").show();
                        $("#btnFilterByCategory").show();

                    } else if (filter === 'year') {
                        $("#filtercategory").hide();
                        $("#btnFilterByCategory").hide();

                        $("#filteryear").show();
                        $("#btnFilterByYear").show();

                    }
                }


                $("#btnFilterByCategory").click(function() {
                    filterbyCategory();
                });

                $("#btnFilterByYear").click(function() {
                    filterbyYear();
                });


                function filterbyYear() {
                    var idYear = $("#filteryear").val();
                    window.location.href = "controler?/action=adminbookfilterbyyear&id=" + idYear + "";
                }

                function filterbyCategory() {
                    var idCat = $("#filtercategory").val();
                    window.location.href = "controler?/action=adminbookfilterbycategory&id=" + idCat + "";
                }

                $(window).load(function() {
                    setValueDropdown();
                    setPaging();
                });

                function setValueDropdown() {
                    var filterby = document.getElementById('filterby');
                    var filterYear = document.getElementById('filteryear');

                    var catName = "${categoriSelected}";
                    var yearSelect = "${yearSelected}";

                    if (catName === "" && yearSelect === "") {
                        setSelectedIndex(filterby, 0);
                        showHide();
                        setSelectedIndex(filterYear, 0);
                    } else if (catName !== "") {
                        setSelectedIndex(filterby, 0);
                        showHide();
                        getIdCat(catName);
                    } else if (yearSelect !== "") {
                        setSelectedIndex(filterby, 1);
                        showHide();
                        getIdYear(yearSelect);
                    }
                }

                function getIdCat(catName) {
                    var dropCat = document.getElementById('filtercategory');
                    setSelectedbyIdValue(dropCat, catName);
                }

                function getIdYear(yearSelect) {
                    var dropYear = document.getElementById('filteryear');
                    setSelectedValue(dropYear, yearSelect);
                }

                function setPaging() {
                    var idYear = $("#filteryear").val();
                    var currentPage = "${currentPage}";
                    var currentPageMin = (parseInt(currentPage) - 1);
                    var currentPagePlus = (parseInt(currentPage) + 1);
                    var noOfPages = "${noOfPages}";

                    var urlMin = 'controler?/action=adminbookfilterbyyear&id=' + idYear + '&page=' + currentPageMin;
                    var url = 'controler?/action=adminbookfilterbyyear&id=' + idYear + '&page=';
                    var urlPlus = 'controler?/action=adminbookfilterbyyear&id=' + idYear + '&page=' + currentPagePlus;

                    $("#bookfooter").html('');
                    $("#bookfooter").append('<label style="padding-left: 5px;">Page : </label>');

                    if (parseInt(currentPage) !== 1) {
                        $("#bookfooter").append('<a href="' + urlMin + '">Previous </a>');
                    }

                    for (var i = 1; i <= noOfPages; i++) {
                        if (parseInt(currentPage) === i) {
                            $("#bookfooter").append(i + ' ');
                        } else {
                            var urls = url + i;
                            $("#bookfooter").append('<a href="' + urls + '">' + i + ' </a>');
                        }
                    }

                    if (parseInt(currentPage) < parseInt(noOfPages)) {
                        $("#bookfooter").append('<a href="' + urlPlus + '"> Next</a>');
                    }
                }

            });
        </script>

    </body>
</html>

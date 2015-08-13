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
                    <p style="text-align:center; padding-top:15px; color:white; cursor:pointer;" ><a class="font-icon" style="text-align:center; padding-top:15px; padding-right:15px; font-size:20px; color:white;">i</a>Home</p>
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
                        <h3 class="fl">Category List</h3>
                        <a href="controler?/action=admincategoryedit" class="fr link" style="padding-right: 20px; padding-top: 20px;">Add New Category<font class="fr font-icon" style="padding-left: 10px;">+</font></a>
                    </div>
                    <div class="content-module-main">
                        <table>
                            <thead>						
                                <tr>
                                    <th>NO</th>
                                    <th>CATEGORY</th>
                                    <th>ACTION</th>
                                </tr>							
                            </thead>

                            <tbody>
                                <%int i = 1;%>
                                <c:forEach items="${categories}" var="cat">    
                                    <tr>
                                        <td>
                                            <%=i%>
                                        </td>
                                        <td>
                                            ${cat.categoryName}
                                        </td>
                                        <td>
                                            <a href="controler?/action=admincategoryedit&id=${cat.idCategory}" class="table-actions-button ic-table-edit"></a>
                                            <a href="controler?/action=admincategorydelete&id=${cat.idCategory}" class="table-actions-button ic-table-delete"></a>
                                        </td>
                                    </tr>
                                    <%i++;%>
                                </c:forEach>
                            </tbody>                            


                            <tfoot>
                            <table>
                                <tr>
                                    <td colspan="5" class="table-footer" id="bookfooter">
                                        <label style="padding-left: 5px;">Page :  </label>    
                                        <%--For displaying Previous link except for the 1st page --%>                                        
                                        <c:if test="${currentPage != 1}">
                                            <a href="controler?/action=admincategory&page=${currentPage - 1}">Previous </a>
                                        </c:if>
                                        <%--For displaying Page numbers. 
                                        The when condition does not display a link for the current page--%>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    ${i} 
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="controler?/action=admincategory&page=${i}">${i} </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <%--For displaying Next link --%>
                                        <c:if test="${currentPage lt noOfPages}">                                           
                                            <a href="controler?/action=admincategory&page=${currentPage + 1}"> Next</a>
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
        <script>
            $(function() {

                $("#header-col1").on("click", function() {
                    window.location.href = "controler?/action=homeadmin";
                });

                // action login as member
                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

            });
        </script>

    </body>
</html>

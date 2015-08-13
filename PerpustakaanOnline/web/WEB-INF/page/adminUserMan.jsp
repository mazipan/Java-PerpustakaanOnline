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
                <div style="padding-top: 10px; padding-left:2px; width: 99%; height: 60px;">
                    <div class="dropdown dropdown-dark" style="display: inline-block">
                        <select id="userFilter" class="dropdown-select">
                            <option value="member">Member</option>
                            <option value="admin">Admin</option>
                        </select>
                    </div>
                    <button id="btnFilterTable" class="button-bevel cyan">Apply</button>  
                </div>

                <div class="content-module" id="content-module">

                    <div id="headerLineMember" class="content-module-heading cf">
                        <h3 class="fl">Member List</h3>
                        <a href="controler?/action=adminusermanedit" class="fr link" style="padding-right: 20px; padding-top: 20px;">Add New Member<font class="fr font-icon" style="padding-left: 10px;">+</font></a>
                    </div>
                    <div id="bodyLineMember" class="content-module-main">
                        <table>
                            <thead>						
                                <tr>
                                    <th>NO</th>
                                    <th>NIK</th>
                                    <th>NAMA</th>
                                    <th>ALAMAT</th>
                                    <th>TELEPON</th>
                                    <th>EXT</th>
                                    <th>BAGIAN</th>
                                    <th>LOKASI</th>
                                    <th>ACTION</th>
                                </tr>							
                            </thead>

                            <tbody>
                                <%int i = 1;%>
                                <c:forEach items="${members}" var="member">    
                                    <tr>
                                        <td>
                                            <%=i%>
                                        </td>
                                        <td>
                                            ${member.memberNik}
                                        </td>
                                        <td>
                                            ${member.memberName}
                                        </td>
                                        <td>
                                            ${member.memberAlamat}
                                        </td>
                                        <td>
                                            ${member.memberTelp}
                                        </td>
                                        <td>
                                            ${member.memberExt}
                                        </td>
                                        <td>
                                            ${member.memberBagian}
                                        </td>
                                        <td>
                                            ${member.memberLokasi}
                                        </td>
                                        <td>
                                            <a href="controler?/action=adminusermanedit&id=${member.memberId}" class="table-actions-button ic-table-edit" title="Edit Member"></a>
                                            <a href="controler?/action=adminusermandelete&id=${member.memberId}" class="table-actions-button ic-table-delete" title="Delete Member"></a>
                                            <a href="controler?/action=adminusermanreset&id=${member.memberId}" class="table-actions-button ic-table-refresh" title="Reset Password"></a>
                                        </td>
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
                                            <a href="controler?/action=adminuserman&page=${currentPage - 1}">Previous </a>
                                        </c:if>
                                        <%--For displaying Page numbers. 
                                        The when condition does not display a link for the current page--%>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    ${i} 
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="controler?/action=adminuserman&page=${i}">${i} </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <%--For displaying Next link --%>
                                        <c:if test="${currentPage lt noOfPages}">
                                            <a href="controler?/action=adminuserman&page=${currentPage + 1}"> Next</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                            </tfoot>

                        </table>

                    </div> <!--div content-module-main-->

                    <div id="headerLineAdmin" class="content-module-heading cf">
                        <h3 class="fl">Administrator List</h3>
                        <a href="controler?/action=adminusermanadminedit" class="fr link" style="padding-right: 20px; padding-top: 20px;">Add New Admin<font class="fr font-icon" style="padding-left: 10px;">+</font></a>
                    </div>
                    <div id="bodyLineAdmin" class="content-module-main">
                        <table>
                            <thead>					
                                <tr>
                                    <th>NO</th>
                                    <th>NAME</th>
                                    <th>USERNAME</th>
                                    <th>ACTION</th>
                                </tr>						
                            </thead>
                            <tbody>
                                <%int x = 1;%>
                                <c:forEach items="${administrators}" var="administrator">   
                                    <tr>
                                        <td>
                                            <%=x%>
                                        </td>
                                        <td>
                                            ${administrator.truename} 
                                        </td>
                                        <td>
                                            ${administrator.username} 
                                        </td>
                                        <td>
                                            <a href="controler?/action=adminusermanadminedit&id=${administrator.idAdministrator}" class="table-actions-button ic-table-edit" title="Edit Admin"></a>
                                            <a href="controler?/action=adminusermanadmindelete&id=${administrator.idAdministrator}" class="table-actions-button ic-table-delete" title="Delete Admin"></a>
                                        </td>
                                    </tr>
                                    <%x++;%>
                                </c:forEach>
                            </tbody>

                            <tfoot>
                            <table>
                                <tr>
                                    <td colspan="5" class="table-footer">
                                        <label style="padding-left: 5px;">Page : </label>    
                                        <%--For displaying Previous link except for the 1st page --%>
                                        <c:if test="${currentPage != 1}">
                                            <a href="controler?/action=adminuserman&page=${currentPage - 1}">Previous </a>
                                        </c:if>
                                        <%--For displaying Page numbers. 
                                        The when condition does not display a link for the current page--%>
                                        <c:forEach begin="1" end="${noOfPagesAdmin}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    ${i} 
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="controler?/action=adminuserman&page=${i}">${i} </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <%--For displaying Next link --%>
                                        <c:if test="${currentPage lt noOfPagesAdmin}">
                                            <a href="controler?/action=adminuserman&page=${currentPage + 1}"> Next</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                            </tfoot>

                        </table>
                        <label style="color: red; font-size: 12pt">*) You can't change password other administrator except your account</label>                 
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

                $("#header-logout").click(function() {
                    window.location.href = "controler?/action=logout";
                });

                $("#btnFilterTable").click(function() {
                    showHideBody();
                });

                function showHideBody() {
                    var filter = $("#userFilter").val();
                    if (filter === "member") {
                        $("#headerLineAdmin").hide();
                        $("#bodyLineAdmin").hide();

                        $("#headerLineMember").show();
                        $("#bodyLineMember").show();
                    } else if (filter === "admin") {
                        $("#headerLineMember").hide();
                        $("#bodyLineMember").hide();

                        $("#headerLineAdmin").show();
                        $("#bodyLineAdmin").show();

                    }
                }

                $(window).load(function() {
                    setValueDropdown();
                });

                function setValueDropdown() {
                    var userFilter = document.getElementById('userFilter');

                    var isMember = "${isMember}";
                    var isAdmin = "${isAdmin}";

                    if (isMember === "" && isAdmin === "") {
                        setSelectedIndex(userFilter, 0);
                        showHideBody();
                    } else if (isMember !== "") {
                        setSelectedIndex(userFilter, 0);
                        showHideBody();
                    } else if (isAdmin !== "") {
                        setSelectedIndex(userFilter, 1);
                        showHideBody();
                    }
                }
            });
        </script>

    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<style>
    .nav-item{
        margin-right: 25px;
        font-size: 18px;
    }
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="border-bottom: 2px solid lightslategray;">
    <a class="navbar-brand" href="/">
        <img src="../static/img/logo.png" alt="" style="width: 246px; border-radius: 5px;">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse nav-right" id="navbarNavDropdown" style="border-radius: 8px; background-color: #FCB694; margin-right: ">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Anasayfa ${homeActive}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Ayrıntılı Arama</a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.isLoggedIn}">
                    <c:choose>
                        <c:when test="${sessionScope.groupId == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="#">Haber</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Kategori</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Editör</a>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.groupId == 2}">
                            <li class="nav-item">
                                <a class="nav-link" href="#">Haber</a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Profil</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/giris">Giriş Yap</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/kayit-ol">Kayıt Ol</a>
                    </li>
                </c:otherwise>
            </c:choose>





<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
<%--                    Dropdown link--%>
<%--                </a>--%>
<%--                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">--%>
<%--                    <a class="dropdown-item" href="#">Action</a>--%>
<%--                    <a class="dropdown-item" href="#">Another action</a>--%>
<%--                    <a class="dropdown-item" href="#">Something else here</a>--%>
<%--                </div>--%>
<%--            </li>--%>
        </ul>
    </div>
</nav>

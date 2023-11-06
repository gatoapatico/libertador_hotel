<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    HttpSession ses = request.getSession();
    if (ses.getAttribute("Nombre") == null && ses.getAttribute("tipo") == null) {
        response.sendRedirect("Login.jsp");
    }
%>
<html lang="en" dir="ltr">
    <head>
        <meta charset="UTF-8">
        <title> Administrador </title>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="../css/HeaderStyle.css">
        <link rel="stylesheet" href="../css/TablaStyle.css">
        <link rel="stylesheet" href="../css/Formulario.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <div class="navbar">
                <i class='bx bx-menu'></i>
                <div class="logo"><a href="#">Hotel El Libertador</a></div>
                <div class="nav-links">
                    <div class="sidebar-logo">
                        <span class="logo-name">Administracion</span>
                        <i class='bx bx-x'></i>
                    </div>
                    <ul class="links">
                        <li>
                            <a href="#">Habitaciones</a>
                            <i class='bx bxs-chevron-down habitacion-arrow arrow  '></i>
                            <ul class="habitacion-sub-menu sub-menu">
                                <li><a href="#">Crear habitacion</a></li>
                                <li><a href="#">Modificar Habitacion</a></li>
                                <li><a href="#">Habitacion</a></li>

                            </ul>
                        </li>
                        <li>
                            <a href="#">Salones</a>
                            <i class='bx bxs-chevron-down salon-arrow arrow '></i>
                            <ul class="salon-sub-menu sub-menu">
                                <li><a href="#">Dynamic Clock</a></li>
                                <li><a href="#">Form Validation</a></li>
                                <li><a href="#">Card Slider</a></li>
                                <li><a href="#">Complete Website</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="../SvCategorias?Op=Listar">Categorias</a>
                        </li>
                        <li>
                            <a href="../SvServicios?Op=Listar">Servicios</a>
                        </li>
                        <li>
                            <a href="../SvUsuarios?Op=Listar">Usuarios</a>
                        </li>
                        <li>
                            <a href="#">Log Out</a>
                            <i class='bx bxs-user logout-arrow arrow '></i>
                            <ul class="logout-sub-menu sub-menu">
                                <li><a href="#">Bienvenido <%=ses.getAttribute("Nombre")%></a></li>
                                <li><a class="dropdown-item" href="../login.jsp?cerrar=true">
                                    Cerrar Sesion
                                </a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
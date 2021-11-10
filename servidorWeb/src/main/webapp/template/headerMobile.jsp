<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/home" id="brand">
        <img id="img-EUY" src="<%=request.getContextPath()%>/assets/images/misc/iconoEntrenamos-uy.png" alt="" class="">
        Entrenamos.uy
    </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul id="acs" class="navbar-nav me-auto mt-2 mb-1 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/search?actividades=yes"><i class="fas fa-volleyball-ball"></i> Actividades Deportivas</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/search?actividades=yes&clases=yes"><i class="fas fa-book"></i> Clases</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/api/logout"><i class="fas fa-arrow-alt-circle-left"></i> Salir</a>
          </li>
        </ul>
      </div>
    </div>
</nav>
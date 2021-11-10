<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <script src="https://kit.fontawesome.com/ba45a4bf02.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="./css/actsDepsMovil.css">

    <title>Entrenamos.uy</title>
  </head>
  <body>
    
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="homeMovil.html">
              <img id="img-EUY" src="../img/iconoEntrenamos-uy.png" alt="" class="">
              Entrenamos.uy
          </a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul id="acs" class="navbar-nav me-auto mt-2 mb-1 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="actDepsMovil.html"><i class="fas fa-volleyball-ball"></i> Actividades Deportivas</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="clasesMovil.html"><i class="fas fa-book"></i> Clases</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="./inicioSesionMovil.html"><i class="fas fa-arrow-alt-circle-left"></i> Salir</a>
              </li>
            </ul>
          </div>
        </div>
    </nav>

    <div id="section-socio" class="row mt-4 mb-3">
        <p class="bienvenida-socio mt-3"><i class="fas fa-user-circle"></i> Bienvenido <b>Usuario</b></p>
    </div>

    <div id="section-verActsDeps" class="row pt-4 pb-4">
        <div class="row">
            <h1 id="title">Ver Actividades Deportivas</h1>
        </div>
        <div>
          <div id="filtrarInstitucion" class="row filtrar pt-3">
            <div class="btn-group">
              <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="fas fa-school"></i> Instituciones
              </button>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Telon</a></li>
                <li><a class="dropdown-item" href="#">Fuerza Bruta</a></li>
                <li><a class="dropdown-item" href="#">Olympic</a></li>
                <li><a class="dropdown-item" href="#">Instituto Natural</a></li>
              </ul>
            </div>
        </div>
        <div id="filtrarCategoria" class="row filtrar pt-2 pb-4">
          <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              <i class="fas fa-align-justify"></i> Categorias
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Gimnasia</a></li>
              <li><a class="dropdown-item" href="#">Al aire libre</a></li>
              <li><a class="dropdown-item" href="#">Balon</a></li>
              <li><a class="dropdown-item" href="#">En sala</a></li>
            </ul>
          </div>
        </div>
        <div id="infoActDep" class="row">
            <p><i class="fas fa-info-circle"></i> Presione una actividad para ver mas informacion</p>
        </div>
        </div>
        
        <hr style="width:88%; height: 2px;">

        <div id="actsDeps" class="row">
            <a href="infoActDepMovil.html">
                <div class="row pt-2">
                    <img src="../img/crossFit.jpg" alt="">
                </div>
                <div class="row pt-2 pb-2">
                    <h3>CrossFit</h3>
                </div>
            </a>
            <a href="">
              <div class="row pt-2">
                <img src="../img/futbol11.jpg" alt="">
            </div>
            <div class="row pt-2 pb-2">
                <h3>Futbol</h3>
            </div>
            </a>
            <a href="">
              <div class="row pt-2">
                <img src="../img/yogaImg.jpg" alt="">
            </div>
            <div class="row pt-2 pb-2">
                <h3>Yoga</h3>
            </div>
            </a>
        </div>
    </div>

    <div id="section-sponsors" class="row pt-5 pb-5">
      <div class="row text-center">
          <h1>Sponsors</h1>
      </div>
      <div class="row mt-4">
          <div class="col-6 text-center">
              <img src="../img/logoPowerade.jpg" alt="">
          </div>
          <div class="col-6 text-center">
              <img src="../img/logoGatorade.jpg" alt="">
          </div>
      </div>
      <div class="row mt-4">
          <div class="col-6 text-center">
              <img src="../img/logoUniversal.png" alt="">
          </div>
          <div class="col-6 text-center">
              <img src="../img/logoAdidas.jpg" alt="">
          </div>
      </div>
  </div>
    
  <footer class="footer pb-2">
    <div class="footer-row row">
        Vacilator Team - Todos los derechos reservados. Sitio web para el proyecto del Taller de Programación de Facultad de ingeniería - UDELAR
    </div>
    <hr style="width: 88%; height: 2px; margin: auto;" class="mb-4 mt-2">
    <div class="row pb-3">
         <h3>Desarrolladores</h3> 
    </div>
    <div class="row fs-6">
          <p>Guillermo Toyos</p>
    </div>
    <div class="row fs-6">
          <p>Miguel Machado</p>
    </div>
    <div class="row fs-6">
          <p>Alexis Baladon</p>
    </div>
    <div class="row fs-6">
          <p>Mathias Ramilo</p>
    </div>
    <div class="row fs-6">
          <p>Juan Jose Mangado</p>
    </div>
  </footer>

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/head.jsp"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/styles/home.css">
	    <style>
    #ohno {
    background-image: url(<%=request.getContextPath()%>/assets/images/misc/zipper.png);
    background-size: contain;
    height: 39rem;
    }
    </style>
</head>
<body>
	
		<div id="ohno" class="page-wrap d-flex flex-row align-items-center">
		    <div class="container">
		        <div class="row justify-content-center">
		            <div class="col-md-12 text-center">
		                <span class="display-1 d-block">403</span>
		                <div class="mb-4 lead">La pagina que desea acceder no se encuentra disponible para usted.</div>
		                 <a href="<%=request.getContextPath()%>/home" class="link-dark">Volver al inicio</a>
		            </div>
		        </div>
		    </div>
		</div>
		
		<audio id="audio2" src="<%=request.getContextPath()%>/assets/audio/404.mp3"></audio>
		

	<jsp:include page="/template/footer.jsp"/>
					<script>
					function play(){
									var audio=document.getElementById("audio2");
									audio.play()
					}
					var x = document.getElementById("ohno");
					ohno.addEventListener("click", play);
				</script>
	<script>
		function play(){
						var audio=document.getElementById("audio2");
						audio.play()
		}
		var x = document.getElementById("ohno");
		ohno.addEventListener("click", play);
	</script>
</body>
</html>
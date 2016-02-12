<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
	<title>Jump Framework | Contact page</title>
	
	<link href="<c:url value="/Theme/ColorFull/css/bootstrap.min.css" />"
		rel="stylesheet">
	<link href="<c:url value="/Theme/ColorFull/css/app.css" />"
		rel="stylesheet">
	
</head>
<body>
	<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value='/' />">JUMP FRAMEWORK</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<c:url value='/about' />">About</a>
                    </li>
                    <li>
                        <a href="<c:url value='/contact' />">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">

        <div class="row">

            <div class="col-md-3">
              
            </div>

            <div class="col-md-9">

                <div class="thumbnail">
                    <div class="caption-full">
                        <h3>Contact Page
                        </h3>
                        <p>This is Contact page.</p>
                        <p>Contact us.</p>
                        
                    </div>
                </div>

                <div class="well">

                   
                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->

    <div class="container">

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->	
	
	
	
	<script src="<c:url value="/Theme/ColorFull/js/jquery.js" />"></script>
	<script src="<c:url value="/Theme/ColorFull/js/bootstrap.min.js" />"></script>
	<script type="text/javascript">
	//alert("hello11");
	</script>
</body>
</html>
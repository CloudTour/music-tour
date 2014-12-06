<!DOCTYPE html>
<html lang="en">
<head>
<!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
<meta charset="utf-8">
<title>Music Tour</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="css/charisma-app.css" rel="stylesheet">
<link href='bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link href='bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
<link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
<link href='bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='css/jquery.noty.css' rel='stylesheet'>
<link href='css/noty_theme_default.css' rel='stylesheet'>
<link href='css/elfinder.min.css' rel='stylesheet'>
<link href='css/elfinder.theme.css' rel='stylesheet'>
<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='css/uploadify.css' rel='stylesheet'>
<link href='css/animate.min.css' rel='stylesheet'>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<!-- jQuery -->
<script src="bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
	<div id="Layer1"
		style="position: absolute; width: 100%; height: 100%; z-index: -1">
		<img src="images/singer.jpg" height="100%" width="100%" />
	</div>
	<div class="ch-container">
		<div class="row">
			<div class="row">
				<div class="col-md-12 center login-header">
					<h2>Welcome to Music Tour</h2>
				</div>
				<!--/span-->
			</div>
			<!--/row-->

			<div class="row">
				<div class="well col-md-5 center login-box">
					<div>
						<div id="fan-reg-panel">
							<fieldset>
								<!-- Type -->
								<div class="input-group input-group-lg">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-music red"></i></span> <select
										class="form-control">
										<option>I'm a music fan</option>
										<option>I'm a singer</option>
									</select>
								</div>
								<div class="clearfix"></div>
								<br>

								<!-- Username -->
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-user red"></i>
									</span> <input type="text" class="form-control" placeholder="Username">
								</div>
								<div class="clearfix"></div>
								<br>

								<!-- Email -->
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-envelope red"></i>
									</span> <input type="text" class="form-control" placeholder="Email">
								</div>
								<div class="clearfix"></div>
								<br>

								<!-- Password -->
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-lock red"></i>
									</span> <input type="password" class="form-control" placeholder="Password">
								</div>
								<div class="clearfix"></div>
								<br>

								<!-- Repeat Password -->
								<div class="input-group input-group-lg">
									<span class="input-group-addon"> <i
										class="glyphicon glyphicon-lock red"></i>
									</span> <input type="password" class="form-control"
										placeholder="Repeat Password">
								</div>
								<div class="clearfix"></div>
								<br>

								<!-- Buttons -->
								<div style="float: left; width: 50%" class="input-group input-group-lg">
								</div>

								<div style="float: right; width: 50%" class="input-group input-group-lg">
									<p class="center col-md-10">
										<button type="submit" class="btn btn-success">Create Account</button>
									</p>
								</div>

							</fieldset>
						</div>
						<div id="singer-reg-panel"></div>
					</div>


				</div>
				<!-- /span -->
			</div>
			<!--/row-->
		</div>
		<!--/fluid-row-->

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='bower_components/moment/min/moment.min.js'></script>
	<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script src="bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>


</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if (session.getAttribute("username") == null) {
		String username = request.getParameter("username");
		session.setAttribute("username", username);
	}

	if (session.getAttribute("usertype") == null) {
		String usertype = request.getParameter("type");
		session.setAttribute("usertype", usertype);
	}

	String username = (String) session.getAttribute("username");
	String usertype = (String) session.getAttribute("usertype");
%>

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
<title><%=username%>'s Home</title>
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
	<p style="display: none;" id="username"><%=username%></p>
	<p style="display: none;" id="usertype"><%=usertype%></p>

	<!-- Add Table -->
	<div class="row" id="popup"
		style="display: none; width: 100%; height: 100%; position: absolute; z-index: 100;">
		<div class="well col-md-5 center login-box">
			<form class="form-horizontal">
				<fieldset>

					<!-- concert name -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="cname-input" type="text" class="form-control"
							placeholder="Concert Name">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- band name -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="bname-input" type="text" class="form-control"
							placeholder="Band Name">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- datetime -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="cdatetime-input" type="text" class="form-control"
							placeholder="Datetime">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- price -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="cprice-input" type="text" class="form-control"
							placeholder="Price">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- website -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="cwebsite-input" type="text" class="form-control"
							placeholder="Website">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- Venue -->
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <i
							class="glyphicon glyphicon-user red"></i>
						</span> <input id="vname-input" type="text" class="form-control"
							placeholder="Venue">
					</div>
					<div class="clearfix"></div>
					<br>

					<!-- Add button -->
					<div style="float: left; width: 50%" class="input-group input-group-lg">
						<p class="center col-md-10">
							<button type="button" class="btn btn-primary" onclick="complete()">Complete</button>
						</p>
					</div>
					<!-- Cancel button -->
					<div style="float: right; width: 50%" class="input-group input-group-lg">
						<p class="center col-md-10">
							<button type="button" class="btn btn-default" onclick="cancel()">Cancel</button>
						</p>
					</div>

				</fieldset>
			</form>
		</div>
		<!--/span-->
	</div>
	<!--/row-->



	<!-- topbar starts -->
	<div class="navbar navbar-default" role="navigation">

		<div class="navbar-inner">
			<button type="button" class="navbar-toggle pull-left animated flip">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"> <span>Music Tour</span></a>

			<!-- user dropdown starts -->
			<div class="btn-group pull-right">
				<button id="profile-btn" class="btn btn-default" onclick="profile()">
					<i class="glyphicon glyphicon-user"></i> Profile
				</button>
				<button id="logout-btn" class="btn btn-default" onclick="logout()">
					<i class="glyphicon glyphicon-log-out"></i> Logout
				</button>
			</div>
			<!-- user dropdown ends -->

		</div>
	</div>
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">

			<!-- left menu starts -->
			<div class="col-sm-2 col-lg-2">
				<div class="sidebar-nav">
					<div class="nav-canvas">
						<div class="nav-sm nav nav-stacked"></div>
						<ul class="nav nav-pills nav-stacked main-menu">
							<li class="nav-header">Main</li>
							<li><a class="ajax-link" href="user.jsp"><i
									class="glyphicon glyphicon-home"></i><span> Dashboard</span></a></li>
							<li><a class="ajax-link" href="Concerts.jsp"><i
									class="glyphicon glyphicon-home"></i><span> Concerts</span></a></li>
							<li><a class="ajax-link" href="MyPost.jsp"><i
									class="glyphicon glyphicon-home"></i><span> MyPost</span></a></li>
							<li><a class="ajax-link" href="attend.jsp"><i
									class="glyphicon glyphicon-home"></i><span> Attend</span></a></li>
							<li><a class="ajax-link" href="recommend.jsp"><i
									class="glyphicon glyphicon-home"></i><span> Recommend</span></a></li>
							<li><a class="ajax-link" href="follow-band.jsp"><i
									class="glyphicon glyphicon-home"></i><span> Bands</span></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!--/span-->
			<!-- left menu ends -->

			<noscript>
				<div class="alert alert-block col-md-12">
					<h4 class="alert-heading">Warning!</h4>

					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->


				<div>
					<ul class="breadcrumb">
						<li><a href="#">Home</a></li>
						<li><a href="#">Dashboard</a></li>
					</ul>
				</div>

				<!-- Concerts added by user -->
				<div class="row">
					<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-star-empty"></i> Concerts
								</h2>

							</div>
							<div class="box-content">
								<!-- put your content here -->
								<table id="concert-table"
									class="table table-striped table-bordered bootstrap-datatable datatable responsive">
									<thead>
										<tr>
											<th>Name</th>
											<th>Band</th>
											<th>Date</th>
											<th>Price</th>
											<th>Website</th>
											<th>Venue</th>
											<th>Active</th>
										</tr>
									</thead>
									<tbody id="concert-tbody">
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
				<!--/row-->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<!--/fluid-row-->


		<hr>

		<footer class="row">
			<p class="col-md-9 col-sm-9 col-xs-12 copyright">
				&copy; <a href="http://www.google.com" target="_blank">Music Tour</a> 2012 -
				2014
			</p>

			<p class="col-md-3 col-sm-3 col-xs-12 powered-by">
				Powered by: <a href="http://usman.it/free-responsive-admin-template">Music
					Tour</a>
			</p>
		</footer>

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
	<!-- 	<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script> -->
	<script>
		$(document).ready(function() {
			$('#concert-table').DataTable();
		});
		function init() {
			debugger;
			var attends = {};
			$.ajax({
				url : "GetConcertByAttend",
				type : "POST",
				data : {
					uname : $("#username").html(),
				},
				asyc : false,
				success : function(data) {
					debugger;
					var result = JSON.parse(data);
					for (i = 0; i < result.length; ++i) {
						attends[result[i].cid] = true;
					}
				}
			});

			$
					.ajax({
						url : "GetConcertByFan",
						type : "POST",
						data : {
							uname : $("#username").html(),
						}
					})
					.done(
							function(data) {
								debugger;
								var result = JSON.parse(data);
								if ($("#concert-tbody tr").length > 0)
									$("#concert-tbody tr").remove();
								for (var i = 0; i < result.length; ++i) {
									if (result[i].cwebsite == null)
										result[i].cwebsite = "";
									var attendBtn = "";
									if (!(result[i].cid in attends)) {
										attendBtn = ('<a class="btn btn-success" onclick="attend(\''
												+ result[i].cid
												+ '\')" href="#">'
												+ '<i class="glyphicon glyphicon-heart icon-white"></i>'
												+ 'Attend' + '</a>');
									}

									var row = '<tr>' + '<td>'
											+ result[i].cname
											+ '</td>'
											+ '<td class="center">'
											+ result[i].bname
											+ '</td>'
											+ '<td class="center">'
											+ result[i].cdatetime
											+ '</td>'
											+ '<td class="center">'
											+ result[i].cprice
											+ '</td>'
											+ '<td class="center">'
											+ result[i].cwebsite
											+ '</td>'
											+ '<td class="center">'
											+ result[i].vname
											+ '</td>'
											+ '<td class="center" > '
											+ '<a class="btn btn-default" href="review.jsp?concertid='
											+ result[i].cid
											+ '">'
											+ '<i class="glyphicon glyphicon-zoom-in icon-white"></i>'
											+ 'View' + '</a>' 
											+ attendBtn
											+ '</td> '
											+ '</tr>';
									$("#concert-tbody").append(row);
								}

							});
		}

		function profile() {
			window.location.href = "singer-profile.jsp";
		}
		function logout() {
			window.location.href = "index.jsp";
		}

		function showWin() {
			var winNode = $("#popup");
			winNode.fadeIn("slow");
		}

		function cancel() {
			var winNode = $("#popup");
			winNode.fadeOut();
		}

		function complete() {
			debugger;
			if ($("#cname-input").val() == "") {
				alert("Concert name cannot be empty.");
				$("#cname-input").focus();
				return;
			}
			if ($("#bname-input").val() == "") {
				alert("Band name cannot be empty.");
				$("#bname-input").focus();
				return;
			}
			if ($("#cdatetime-input").val() == "") {
				alert("Time cannot be empty.");
				$("#cdatetime-input").focus();
				return;
			}
			if ($("#cprice-input").val() == "") {
				alert("Price cannot be empty.");
				$("#cprice-input").focus();
				return;
			}
			if ($("#vname-input").val() == "") {
				alert("Venue cannot be empty.");
				$("#vname-input").focus();
				return;
			}

			$.ajax({
				url : "AddConcertByUser",
				type : "POST",
				data : {
					cname : $("#cname-input").val(),
					bname : $("#bname-input").val(),
					cdatetime : $("#cdatetime-input").val(),
					cprice : $("#cprice-input").val(),
					cwebsite : $("cwebsite-input").val(),
					vname : $("#vname-input").val(),
					uname : $("#username").html(),
					confirmed : "1",
				}
			}).done(function(data) {
				var result = JSON.parse(data);
				alert(result.status);
				$("#popup").fadeOut();
				init();
			});
		}

		function attend(cid) {
			debugger;
			$.ajax({
				url : "AddAttend",
				type : "POST",
				data : {
					cid: cid,
					uname : $("#username").html(),
					attended:'0'
				}
			}).done(function(data) {
				var result = JSON.parse(data);
				alert(result.status);
				init();
			});
		}

		init();
	</script>


</body>
</html>

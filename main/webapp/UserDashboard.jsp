<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.usermanagement.model.User"%>
<%@page import="com.usermanagement.model.Address"%>

<!DOCTYPE html>

<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>  

<style>
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 120px;
   border-radius:20%;
   height:150px;
}
</style>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta Http-Equiv="Cache-Control" Content="no-cache">
<meta Http-Equiv="Pragma" Content="no-cache">
<meta Http-Equiv="Expires" Content="0"> 
<title>User Dashboard</title>


<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" href="assets/css/tailwind.output.css" />
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="assets/js/init-alpine.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/tw-elements/dist/css/index.min.css" />

<link rel="stylesheet" href="custom/custom.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script src="https://cdn.tailwindcss.com"></script>

</head>

<body>

	<div class="flex flex-row w-full h-screen bg-gray-50 dark:bg-gray-900" style="height:100vh"
		:class="{ 'overflow-hidden': isSideMenuOpen}">
		<!-- Desktop sidebar -->
		
		<aside
			class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
			<div class="py-4 text-gray-500 dark:text-gray-400">
				<ul>
					<li class="relative px-6 py-3">
			 			<a
						class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						href="UserDashboard.jsp"> <svg class="w-5 h-5" aria-hidden="true" fill="none"
								stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
								viewBox="0 0 24 24" stroke="currentColor">
                  <path
									d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                </svg> <span class="ml-4">Home</span>
					</a>
					</li>
				</ul>
				<ul>
					<li class="relative px-6 py-3"><a
						class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						href="Register.jsp?user=userEdit"> <svg class="w-5 h-5"
								aria-hidden="true" fill="none" stroke-linecap="round"
								stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
								stroke="currentColor">
                  <path
									d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
                </svg> <span class="ml-4">Update Profile</span>
					</a></li>


				</ul>
				<ul>
				<li class="relative px-6 py-3"><a
										class="inline-flex items-center text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
										href="Logout"> <svg class="w-5 h-5 mr-3"
												aria-hidden="true" fill="none" stroke-linecap="round"
												stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
												stroke="currentColor">
                          <path
													d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                        </svg> <span class="ml-2">Log out</span>
									</a></li>
				</ul>

			</div>
		</aside>
		<!-- Mobile sidebar -->
		<!-- Backdrop -->
		<div x-show="isSideMenuOpen"
			x-transition:enter="transition ease-in-out duration-150"
			x-transition:enter-start="opacity-0"
			x-transition:enter-end="opacity-100"
			x-transition:leave="transition ease-in-out duration-150"
			x-transition:leave-start="opacity-100"
			x-transition:leave-end="opacity-0"
			class="fixed inset-0 z-10 flex items-end bg-black bg-opacity-50 sm:items-center sm:justify-center"></div>
		<aside
			class="fixed inset-y-0 z-20 flex-shrink-0 w-64 mt-16 overflow-y-auto bg-white dark:bg-gray-800 md:hidden"
			x-show="isSideMenuOpen"
			x-transition:enter="transition ease-in-out duration-150"
			x-transition:enter-start="opacity-0 transform -translate-x-20"
			x-transition:enter-end="opacity-100"
			x-transition:leave="transition ease-in-out duration-150"
			x-transition:leave-start="opacity-100"
			x-transition:leave-end="opacity-0 transform -translate-x-20"
			@click.away="closeSideMenu" @keydown.escape="closeSideMenu">
			<div class="py-4 text-gray-500 dark:text-gray-400">	
				<ul class="mt-6">
					<li class="relative px-6 py-3">
					 <a
						class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						href="UserDashboard.jsp"> <svg class="w-5 h-5" aria-hidden="true"
								fill="none" stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                  <path
									d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
                </svg> <span class="ml-4">Home</span>
					</a>
					</li>
				</ul>
			<ul>
					<li class="relative px-6 py-3"><a
						class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						href="Register.jsp?user=userEdit"> <svg class="w-5 h-5" aria-hidden="true"
								fill="none" stroke-linecap="round" stroke-linejoin="round"
								stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                  <path
									d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
                </svg> <span class="ml-4">Update Profile</span>
					</a></li>
					
				</ul>
				<ul>
					
					<li class="relative px-6 py-3"><a
						class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
						href="Logout"> <svg class="w-5 h-5 mr-3"
												aria-hidden="true" fill="none" stroke-linecap="round"
												stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
												stroke="currentColor">
                          <path
													d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                        </svg>  <span class="ml-1">Logout</span>
					</a></li>
					
			</ul>
		
			</div>
		</aside>
	<div class="flex flex-col w-full h-screen">
	<jsp:include page="Header.jsp" /> 
			<header class="">
				<div
					class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300">
					<!-- Mobile hamburger -->
					<button
						class="p-1 mr-5 -ml-1 rounded-md md:hidden focus:outline-none focus:shadow-outline-purple"
						@click="toggleSideMenu" aria-label="Menu">
						<svg class="w-6 h-6" aria-hidden="true" fill="currentColor"
							viewBox="0 0 20 20">
                <path fill-rule="evenodd"
								d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
								clip-rule="evenodd"></path>
              </svg>
					</button>
					
				</div>
			</header>
						<main class="h-screen  w-4/5  mx-auto  ">
						<div class="w-2/3 mx-auto mt-10 text-center">
					
						<h1 class="font-large my-2 text-4xl">Your Profile</h1>
					<table class="table">						
							<c:forEach items="${profileData}" var="profileData">
								<tr  class="border-2">
									<td class="font-bold border-2">Name</td><td class="border-2">${profileData.firstName}     ${profileData.lastName}</td>
								<tr  class="border-2">
									<td class="font-bold border-2">Email</td><td class="border-2">${profileData.email}</td>
								<tr  class="border-2">
									<td class="font-bold border-2">Phone Number</td><td class="border-2">${profileData.contactNo}</td>
								<tr  class="border-2">
									<td class="font-bold border-2">Gender</td><td class="border-2">${profileData.gender}</td>
								<tr  class="border-2">
									<td class="font-bold border-2">Birth Date</td><td class="border-2">${profileData.birthDate}</td>
									
								<tr  class="border-2">
									<td class="font-bold border-2">Known Languages</td><td class="border-2">${profileData.languages}</td>
									
								<tr  class="border-2">
									<td class="font-bold border-2">Profile Image</td><td class="border-2"><img src="data:image/jpg;base64,${profileData.base64Image}"  class="center"> </td>
							
								<tr  class="border-2">
									<td class="font-bold border-2">Address
										<label class="switch">
 										 <input type="checkbox" class="getAddress" id="${profileData.userId}">
 											 <span class="slider round"></span>
									</label>
									</td>
									
									<td class="border-2">
										<c:forEach items="${addressDetails}" var="addressDetails">          							
            				
								 ${addressDetails.addressLine},<br/>
							
							     ${addressDetails.city} - ${addressDetails.pin}<br/>
            					
            					 ${addressDetails.state}<br/> 
            				   <br/>            						
              						
              			</c:forEach>			
							</td>
							</c:forEach>		
					</table>
					</div>
						</main>
		<jsp:include page="Footer.jsp" /> 
						
						
			</div>
		
		</div>
<script>		
$(".getAddress").on("change", function() {
	alert("called");
   	var userId= +this.id;
   	alert(userId)
	$.ajax({
      url : "UserLogin",
      type : "get",
      data : ({
       		userId : userId,
      }),
      success : function(data) {
			alert("success");
      }
    })
  });

</script>
		
		

</body>
</html>
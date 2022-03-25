<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Registration</title>

<link href="https://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/minty/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.css">




<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="assets/css/tailwind.output.css" />


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"
	integrity="sha512-wnea99uKIC3TJF7v4eKk4Y+lMz2Mklv18+r4na2Gn1abDRPPOeef95xTzdwGD9e6zXJBteMIhZ1+68QC5byJZw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script
	src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"
	defer></script>
<script src="assets/js/init-alpine.js"></script>
</head>
<body>
	<form action="RegisterURL" method="POST" class="w-1/2 p-5 mx-auto bg-white ">
		<h1 class="text-xl font-bold text-gray-1200 dark:text-gray-200">
			Create account</h1>


		<div class="flex flex-row gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">First Name</span> <input
					type="text" name="firstname"
					class=" w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="Jane" />
				</label>

			</div>

			<div class="w-full">
				<label class="block mt-2  mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Last Name</span> <input
					type="text" name="lastname"
					class="w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="Doe" />
				</label>

			</div>
		</div>

		<div class="flex flex-row gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Email</span> <input
					type="text" name="email"
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="JaneDoeXX@gmail.com" />
				</label>
			</div>

			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Contact</span> <input
					type="number" name="contact"
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="xxxxxxxxxx" />
				</label>


			</div>
		</div>

		<div class="flex flex-row gap-8">
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Password</span> <input
					type="password" name="password"
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="***************" />
				</label>
			</div>

			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Confirm Password</span> <input
					type="password" name="confirmPassword"
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
					placeholder="***************" />
				</label>
			</div>
		</div>

		<div class="flex flex-row gap-8">
			<div class="w-full">
				<label for="" class="block mt-6 mb-2 text-md font-semibold">
					<span class="text-gray-700 mr-8 dark:text-gray-400">Gender</span><br />
					<input type="radio" id="gender" class="mt-3" name="gender"
					value="male"> <span
					class="text-gray-700 mr-4 dark:text-gray-400"> Male</span> <input
					type="radio" name="gender" value="Female"> <span
					class="text-gray-700 mr-2  dark:text-gray-400">Female</span> <span
					class="block italic text-red-500" id="genderSpan"></span>
				</label>
			</div>
			<div class="w-full">
				<label class="block mt-2 mt-4 text-md font-semibold"> <span
					class="text-gray-700 dark:text-gray-400">Birth Date</span> <input
					type="date" name="birthDate"
					class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" />
				</label>
			</div>
		</div>

		<div class="w-full">
			<label class="block mt-2 mt-4 mb-2 text-md font-semibold"> <span
				class="text-gray-700 dark:text-gray-400">Known Language</span><br />
				<input type="checkbox" class="mt-2 mr-3" name="language"
				value="Gujarati"><label
				class="text-gray-700 dark:text-gray-400">Gujarati</label><br /> <input
				type="checkbox" class="mt-2 mr-3" name="language" value="Hindi"><label
				class="text-gray-700 dark:text-gray-400">Hindi</label><br /> <input
				type="checkbox" class="mt-2 mr-3" name="language" value="English"><label
				class="text-gray-700 dark:text-gray-400">English</label><br />
			</label>

		</div>
		<!-- Address -->
		<div id="main-container">
			<span class="text-gray-700 dark:text-gray-400 text-lg font-semibold">Address</span>
			<div class="panel card container-item mb-2">
				<div class="panel-body">
					<div class="panel-body pl-2 pr-2 pb-2">

						<label class="block mt-2 text-md font-semibold"> <span
							class="text-gray-700 dark:text-gray-400">Street Address</span> <input
							type="text" name="Address[0][Street_Address]"
							class="w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input" />
						</label>
						<div class="flex flex-row gap-8">
							<div class="w-full">
								<label for=""
									class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">City</label>
								<select id="city" name="Address[0][City]"
									class="w-full text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"">
									<option value="">Select your city</option>
									<option value="Ahmedabad">Ahmedabad</option>
									<option value="Rajkot">Rajkot</option>
									<option value="Surat">Surat</option>
									<option value="Gandhinagar">Gandhinagar</option>
								</select> <span class="italic text-red-500" id="citySpan"></span>
							</div>

							<div class="w-full">
								<label for=""
									class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">State</label>
								<select id="state" name="Address[0][State]"
									class="w-full text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"">
									<option value="">Select your state</option>
									<option value="Ahmedabad">Gujarat</option>
									<option value="Rajkot">Rajsthan</option>
									<option value="Surat">Bihar</option>
									<option value="Gandhinagar">Punjab</option>
								</select> <span class="italic text-red-500" id="citySpan"></span>

							</div>
						</div>

						<div class="flex flex-row gap-8">
							<div class="w-full">
								<label class="block mt-2 mt-4 text-md font-semibold"> <span
									class="text-gray-700 dark:text-gray-400">Postal Code</span> <input
									type="number" name="Address[0][Pin]"
									class="block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input"
									placeholder="xxxxxx" />
								</label>
							</div>
							<div class="w-full">
								<div>
									<a href="javascript:void(0)"
										class="remove-item btn btn-sm btn-danger remove-social-media mt-12 h-10 w-1/2 text-lg font-semibold">Remove</a>
								</div>
							</div>

						</div>
					</div>

				</div>
				
			</div>
		</div>

		<div>
			<a class="btn btn-success btn-sm mt-3 text-lg font-semibold" id="add-more" href="javascript:;"
				role="button"><i class="fa fa-plus"></i> Add more address</a>

		</div>
		
		
		
		<div class="w-full">
			<label class="block mt-2 mt-4 mb-2 text-md font-semibold"> <span
				class="text-gray-700 dark:text-gray-400">Upload Image</span><br/>
				<input type="file" name="image" class="mt-2 mr-5" name="image">
			</label>

		</div>



	
		<input type="submit" value="Register"
								class="block w-full px-4 py-2 mt-4 text-sm font-medium leading-5 text-center text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-lg active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">
		

		<hr class="my-8" />

		<p class="mt-4">
			<a
				class="text-sm font-medium text-purple-600 dark:text-purple-400 hover:underline"
				href="UserLogin.jsp"> Already have an account? Login </a>
		</p>


	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
	<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
	<script src="custom/cloneData.js" type="text/javascript"></script>
	<script>
    $('a#add-more').cloneData({
        mainContainerId: 'main-container', // Main container Should be ID
        cloneContainer: 'container-item', // Which you want to clone
        removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
        removeConfirm: true, // default true confirm before delete clone item
        removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
        minLimit:1,
        defaultRender: 1
    });
</script>
</body>
</html>

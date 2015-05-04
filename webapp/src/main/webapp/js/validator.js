
checkName(document)
init();
document.getElementById("companyId").style.borderColor = "green";

function checkPostForm(document) {
	value = true;
	if (checkName(document) == false)
		value = false
	if (checkIntroduced(document) == false)
		value = false
	if (checkDiscontinued(document) == false)
		value = false
	return value;
}

function checkName(document) {
	element = document.getElementById("computerName")
	re = /^[ ]+$/
	if (re.test(element.value) || element.value == "" || element.value == null) {
		element.style.borderColor = "red";
		document.getElementById("nameError").style.display = "block";
		return false;
	} else {
		if (document.getElementById("serviceNameException") != null) {
			document.getElementById("serviceNameException").style.visibility = "hidden";
		}
		document.getElementById("nameError").style.display = "none";
		element.style.borderColor = "green";
		return true;
	}
}

function checkIntroduced(document) {
	element = document.getElementById("introduced")
	
	if (element.value == "" || element.value.match(date_regex)) {
		if (document.getElementById("serviceIntroducedException") != null) {
			document.getElementById("serviceIntroducedException").style.visibility = "hidden";
		}
		document.getElementById("introducedError").style.display = "none";
		element.style.borderColor = "green";
		return true;
	} else {
		document.getElementById("introducedError").style.display = "block";
		element.style.borderColor = "red";
		return false;
	}
}

function checkDiscontinued(document) {
	element = document.getElementById("discontinued")

	if (element.value == "" || element.value.match(date_regex)) {
		if (document.getElementById("serviceDiscontinuedException") != null) {
			document.getElementById("serviceDiscontinuedException").style.visibility = "hidden";
		}
		document.getElementById("discontinuedError").style.display = "none";
		element.style.borderColor = "green";
		return true;
	} else {
		document.getElementById("discontinuedError").style.display = "block";
		element.style.borderColor = "red";
		return false;
	}
}

function init() {
	element = document.getElementById("discontinued")
	if (element.value == "" || element.value == null) {
		if (document.getElementById("serviceDiscontinuedException") != null) {
			document.getElementById("serviceDiscontinuedException").style.visibility = "hidden";
		}
		document.getElementById("discontinuedError").style.display = "none";
		element.style.borderColor = "green";
	}
	
	element = document.getElementById("introduced")
	console.log(element)
	if (element.value == "" || element.value == null) {
		if (document.getElementById("serviceIntroducedException") != null) {
			document.getElementById("serviceIntroducedException").style.visibility = "hidden";
		}
		document.getElementById("introducedError").style.display = "none";
		element.style.borderColor = "green";
	}
}


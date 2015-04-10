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
	if (re.test(element.value)) {
		element.style.borderColor = "red";
		return false;
	} else if (element.value == "" || element.value == null) {
		element.style.borderColor = "red";
		return false;
	} else {
		if (document.getElementById("serviceNameException") != null) {
			document.getElementById("serviceNameException").style.visibility = "hidden";
		}
		element.style.borderColor = "#CCC";
		return true;
	}
}

function checkIntroduced(document) {
	element = document.getElementById("introduced")
	re = date_regex
	
	if (element.value == "" || element.value.match(re)) {
		if (document.getElementById("serviceIntroducedException") != null) {
			document.getElementById("serviceIntroducedException").style.visibility = "hidden";
		}
		element.style.borderColor = "#CCC";
		return true;
	} else {
		element.style.borderColor = "red";
		return false;
	}
}

function checkDiscontinued(document) {
	element = document.getElementById("discontinued")
	re = date_regex

	if (element.value == "" || element.value.match(re)) {
		if (document.getElementById("serviceDiscontinuedException") != null) {
			document.getElementById("serviceDiscontinuedException").style.visibility = "hidden";
		}
		element.style.borderColor = "#CCC";
		return true;
	} else {
		element.style.borderColor = "red";
		return false;
	}
}

function checkPostForm(document) {
	value = true
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
		element.style.borderColor = "#CCC";
		return true;
	}
}

function checkIntroduced(document) {
	element = document.getElementById("introduced")
	re = /^[1-2][0-9][0-9][0-9]-[1-2][0-9]-[0-3][0-9] [0-2][0-9]:[0-5][0-9]:[0-5][0-9]$/

	if (element.value == "") {
		return true;
	} else if (element.value.match(re)) {
		element.style.borderColor = "#CCC";
		return true;
	} else {
		element.style.borderColor = "red";
		return false;
	}
}

function checkDiscontinued(document) {
	element = document.getElementById("discontinued")
	re = /^[1-2][0-9][0-9][0-9]-[1-2][0-9]-[0-3][0-9] [0-2][0-9]:[0-5][0-9]:[0-5][0-9]$/

	if (element.value == "") {
		return true;
	} else if (element.value.match(re)) {
		element.style.borderColor = "#CCC";
		return true;
	} else {
		element.style.borderColor = "red";
		return false;
	}
}
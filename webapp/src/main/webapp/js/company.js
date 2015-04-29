function save(object){
	var name = object.value;
	document.getElementById("companyId").setAttribute("value", document.getElementById(name + "_companyId").value);
	document.getElementById("companyName").setAttribute("value", document.getElementById(name + "_companyName").value);
}
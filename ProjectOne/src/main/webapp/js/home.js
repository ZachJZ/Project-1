/*
 * 
 */

window.onload = function(){
	getSessUser();
	getReimbs();
	
//	document.getElementById("submitNewRequest").addEventListener('click', ()=> alert('Success'))
}

function getSessUser(){
	
	console.log("in getsessUser");
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (xhttp.readyState == 4 && xhttp.status==200){
			
			let user = JSON.parse(xhttp.responseText);
			console.log(user);
			
			//document.getElementById("welcomeHeading").innerText = `Welcome ${user.fName}! Your username is ${user.username}`;
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProjectOne/getsessionuser.json");
	xhttp.send();
	
}

function getReimbs(){
	
	console.log(">>in the start of getReimbs.js");
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function(){
		if (xhttp.readyState == 4 && xhttp.status==200){
			
			let myData = JSON.parse(xhttp.responseText);
			console.log("In the getsessUser thing");
			console.log(myData);
			
			 $('table').bootstrapTable
			(
				{
		        data: myData
		    	}
			);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProjectOne/getusersrequests.json");
	xhttp.send();
}










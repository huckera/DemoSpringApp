let utils = {};

utils.get = (url) => {

  return new Promise(function(resolve,reject){

  var req = new XMLHttpRequest();

  req.open('GET',url);

  req.onload = function() {
    // handle both remote 200 responses and local zero responses...
    if (req.status == 200)  {
        resolve(req.response);
    }
    else {
      reject(Error('promise error with ' + req.status));
    }
  };

  req.onerror = function(err) {
    reject(Error('Network Error with '+url+': ' + err));
  };

  // optional
  req.onreadystatechange = function(m) {
     console.log('m: ' + m);
  };

  req.send();
  });
}

utils.post = (url,data) => {

	var req = new XMLHttpRequest();
	
	req.open('POST',url);
	req.setRequestHeader('Content-type','application/json; charset=utf-8');
	
	req.onload = function() {
		// handle both remote 200 responses and local zero responses...
	    if (req.status == 200)  {
	        console.log('Update was successful')
	    }
	    else {
	      reject(Error('promise error with ' + req.status));
	    }
	};
	
	req.onerror = function(err) {
	    reject(Error('Network Error with '+url+': ' + err));
	};

  	// optional
  	req.onreadystatechange = function(m) {
     	console.log('m: ' + m);
  	};

	var json = JSON.stringify(data);
		req.send(json);
}

utils.delete = (url) => {

  var req = new XMLHttpRequest();

  req.open('DELETE',url);

  req.onload = function() {
    // handle both remote 200 responses and local zero responses...
    if (req.status == 200)  {
        console.log('Delete was successful')
    }
    else {
      reject(Error('promise error with ' + req.status));
    }
  };

  req.onerror = function(err) {
    reject(Error('Network Error with '+url+': ' + err));
  };

  // optional
  req.onreadystatechange = function(m) {
     console.log('m: ' + m);
  };

  req.send();
}


utils.getJSON = async function(url) {
  
  	var data = {};
  
  	var string = null;

  	try {
    	string = await utils.get(url);
  	}
  	catch (e) {
    	alert('error: ' + e);
  	}

	try { 
		data = JSON.parse(string);
      	success = true;
  	}
  	catch (e) {
    	alert('parse error');
  	}
  	return data;
}

async function init() {
  
  let root = document.querySelector('#data');
  let url = '/collab/list';
  //let url = 'https://jsonplaceholder.typicode.com/albums';
  let collabs = null;
  try {
     collabs = await utils.getJSON(url);
  }
  catch (e) {
     root.textContent = 'error: ' + e;
  }
  root.innerHTML = prepareHtml(collabs);
}

function prepareHtml(dataList) {
  
	let html = '<table border="1"><tr><th>Name</th><th>Email</th><th>Edit link</th><th>Delete link</th></tr>'
  
	for (data of dataList) {
   
    	// start a table row for each element
    	html += '<tr>';
  
	    // create a column for each field + edit link + delete link
    	html += `<td>${data.name}</td>`
		html += `<td>${data.email}</td>`
		html += `<td><a href="javascript:initUpdate('${data.id}')">Edit</a></td>`
		html += `<td><a href="javascript:utils.delete('/collab/delete/${data.id}')">Delete</a></td>`
		
    	// end a table row for each element
    	html += '</tr>';
    
	}
    
    // close off the table
    html += '</table>';
  
  	return html;
}

async function initUpdate(id) {
  
  let updateDiv = document.querySelector('#update');
  let url = `/collab/get/${id}`;
  //let url = 'https://jsonplaceholder.typicode.com/albums';
  let collab = null;
  try {
     collab = await utils.getJSON(url);
  }
  catch (e) {
     root.textContent = 'error: ' + e;
  }
  updateDiv.innerHTML = prepareUpdateHtml(collab);
}

function prepareUpdateHtml(data) {
  
	let html = `<form action="/collab/update/${data.id}" method="post" target="_top">`
	html += `<label for="name" class="col-form-label">Name</label>`
	html += `<input type="text" id="name" name="name" value="${data.name}"><br>`
	html += `<label for="email" class="col-form-label">Email</label>`
	html += `<input type="text" id="email" name="email" value="${data.email}"><br>`
	html += `<input type="submit" value="Update Collaborator">`
    html += `</form>`;
  
  	return html;
}

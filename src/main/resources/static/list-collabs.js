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
  
  let html = ''
  
  for (data of dataList) {
   
    // start a section element for each element
    html += '<section>';
    
    // create a <div> for each key-value pair
    for (key in data) {
      
      html += `<div><strong>${key}</strong>: ${data[key]}</div>`
    }
    // close off the section
    html += '</section>';
  }
  // return the html
  return html;
}

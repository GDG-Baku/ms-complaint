  function complaint(){
    event.preventDefault();
  
    let headers = new Headers();
  
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
  
    fetch('https://ms-complaint.herokuapp.com/complaint', {
          mode: 'cors',
          body: JSON.stringify({
            "name": "Ali",
            "surname": "Huseynov",
            "email": "alihsoff@gmail.com",
            "message": "test message",
            "phone": "+994503493571",
            "typeId": 1}),
          headers: {
            'mode': 'no-cors',
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*'
          },
          method: 'POST',
          headers: headers  
    })
      .then((response) => response.json())
      .then(json => {alert(json)})
      .catch((err) => console.log(err));
  }
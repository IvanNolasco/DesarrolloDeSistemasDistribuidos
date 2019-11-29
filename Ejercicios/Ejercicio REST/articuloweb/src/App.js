import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  handleClick = () =>{
    var data = {
      'claveArticulo': "A001",
      'descripcion': "X Box One X",
      'precio': 499.99,
      'existencias': 144
    }
    fetch('http://127.0.0.1:8000/articulos/A001/', {
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      console.log(dataResponse)
    });
  }

  render() {
    return (
      <>
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
        </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
        </a>
        <button onClick={this.handleClick}>Action</button>
        </header>
      </>
    )
  }
}

export default App;

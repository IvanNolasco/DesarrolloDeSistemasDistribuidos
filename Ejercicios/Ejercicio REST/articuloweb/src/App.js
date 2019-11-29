import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import Tabs from 'react-bootstrap/Tabs'
import Tab from 'react-bootstrap/Tab'
import List from './Pages/List';
import New from './Pages/New';
import Delete from './Pages/Delete';
import Update from './Pages/Update';

class App extends Component {
  state = {
    articulos : []
  }
  componentDidMount() {
    this.listArticulo()
  }
  

  listArticulo = () => {
    /*var data = {
      'claveArticulo': "A001",
      'descripcion': "X Box One X",
      'precio': 499.99,
      'existencias': 144
    }*/
    fetch('http://127.0.0.1:8000/articulos/', {
      method: 'GET',
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      this.setState({articulos: dataResponse.results})
    });
  }

  render() {
    return (
      <>
        <Tabs defaultActiveKey="list" justify>
          <Tab eventKey="list" title="Listar Articulo" className="container">
            <List articulos={this.state.articulos} />
          </Tab>
          <Tab eventKey="new" title="Nuevo Articulo" className="container">
            <New listArticulo = {this.listArticulo} />
          </Tab>
          <Tab eventKey="delete" title="Borrar Articulo" className="container">
            <Delete listArticulo = {this.listArticulo} />
          </Tab>
          <Tab eventKey="update" title="Actualizar Articulo" className="container">
          <Update articulos={this.state.articulos} listArticulo = {this.listArticulo} />
          </Tab>
        </Tabs>
      </>
    )
  }
}

export default App;

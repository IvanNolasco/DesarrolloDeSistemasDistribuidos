import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'

import 'bootstrap/dist/css/bootstrap.min.css'

class App extends Component{
  state = {
    alumnos: []
  }

  handleClick = () => {
    fetch('listAlumno', {
      method: 'POST',
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      this.setState({alumnos: dataResponse.alumnosList})
    });
    this.setState({ edit: true })
  }

  componentDidUpdate(prevProps, prevState) {
    if (prevState.alumnos != this.state.alumnos) {
      
    }
  }
  

  render() {
    return (
      <>
        <Table striped>
          <thead>
            <tr>
              <th>No de Boleta</th>
              <th>Nombre</th>
              <th>Apellido Paterno</th>
              <th>Apellido Materno</th>
              <th>E-Mail</th>
            </tr>
          </thead>
          <tbody>
            {
              this.state.alumnos.map(alumno =>{
                return(
                  <tr key={alumno.noBoleta}>
                    <td>{alumno.noBoleta}</td>
                    <td>{alumno.nombre}</td>
                    <td>{alumno.paterno}</td>
                    <td>{alumno.materno}</td>
                    <td>{alumno.email}</td>
                  </tr>
                )
              })
            }
          </tbody>
        </Table>
        <Button variant = "dark" onClick = {this.handleClick} block>Listar</Button>
      </>
    ) 
  }
}

const element = <BrowserRouter><App /></BrowserRouter>;
ReactDOM.render(
  element,
  document.getElementById('react')
);
import React, {Component} from 'react'
import ReactDOM from 'react-dom'
import Button from 'react-bootstrap/Button'
import Tabs from 'react-bootstrap/Tabs'
import Tab from 'react-bootstrap/Tab'
import List from './Components/List'
import ListM from './Components/ListM'
import New from './Components/New'
import 'bootstrap/dist/css/bootstrap.min.css'
import Delete from './Components/Delete'
import Update from './Components/Update'
import NewM from './Components/NewM'
import DeleteM from './Components/DeleteM'
import UpdateM from './Components/UpdateM'

class App extends Component{
  state = {
    alumnos: [],
    materias: []
  }

  handleClick = () => {
    this.listAlumno()
  }
  
  componentDidMount() {
    this.listAlumno()
    this.listMateria()
  }
  
  listAlumno = () => {
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
  }

  listMateria = () => {
    fetch('listMateria', {
      method: 'POST',
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      this.setState({materias: dataResponse.materiasList})
    });
  }

  render() {
    return (
      <>
        <Tabs defaultActiveKey="alumnos" justify>
          <Tab eventKey="alumnos" title="Alumnos" className="container">
            <Tabs defaultActiveKey="list" justify>
              <Tab eventKey="list" title="Listar Alumno" className="container">
                <List alumnos={this.state.alumnos} />
              </Tab>
              <Tab eventKey="new" title="Nuevo Alumno" className="container"> 
                <New listAlumno ={this.listAlumno} />
              </Tab>
              <Tab eventKey="delete" title="Borrar Alumno" className="container">
                <Delete listAlumno ={this.listAlumno}/>
              </Tab>
              <Tab eventKey="update" title="Actualizar Alumno" className="container">
                <Update alumnos = {this.state.alumnos} listAlumno ={this.listAlumno}/>
              </Tab>
            </Tabs>
          </Tab>
          <Tab eventKey="materias" title="Materias" className="container">
            <Tabs defaultActiveKey="list" justify>
              <Tab eventKey="list" title="Listar Materias" className="container">
                <ListM materias = {this.state.materias} />
              </Tab>
              <Tab eventKey="new" title="Nueva Materia" className="container">
                <NewM listMateria ={this.listMateria} />
              </Tab>
              <Tab eventKey="delete" title="Borrar Materia" className="container">
                <DeleteM listMateria ={this.listMateria}/>
              </Tab>
              <Tab eventKey="update" title="Actualizar Materia" className="container">
                <UpdateM materias = {this.state.materias} listMateria ={this.listMateria}/>
              </Tab>
            </Tabs>
          </Tab>
        </Tabs>
      </>
    ) 
  }
}

const element = <App />;
ReactDOM.render(
  element,
  document.getElementById('react')
);
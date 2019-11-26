import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class New extends Component {
  state = {
    noBoleta: "",
    nombre: "",
    paterno: "",
    materno: "",
    email: "",
    wasCreated: "",
    show: false
  }
  handleChange = (e) =>{
    const value = e.target.value
    const name = e.target.id
    this.setState({[name]:value})
  }
  
  handleClose = () => this.setState({show: false})

  handleSubmit = (e) =>{
    e.preventDefault()
    var data = {
      noBoleta: this.state.noBoleta,
      nombre: this.state.nombre,
      paterno: this.state.paterno,
      materno: this.state.materno,
      email: this.state.email,
    }
    fetch('createAlumno', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      if (dataResponse.wasCreated){
        this.setState({wasCreated: "El alumno se ha agregado con exito.",show: true})
        this.props.listAlumno()
     } else
        this.setState({wasCreated: "El alumno no se pudo agregar.", show: true})
    });
  }

  render() {
    return (
      <>
      <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId="noBoleta">
          <Form.Label>No de Boleta:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            pattern="(\d){10}" 
            placeholder="No de Boleta" 
            required 
            type="text"
          />
        </Form.Group>
        <Form.Group controlId="nombre">
          <Form.Label>Nombre(s):</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            placeholder="Nombre del alumno" 
            required 
            type="text"
          />
        </Form.Group>
        <Form.Group controlId="paterno">
          <Form.Label>Apellido Paterno:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            placeholder="Apellido Paterno del Alumno" 
            required 
            type="text"
          />
        </Form.Group>
        <Form.Group controlId="materno">
          <Form.Label>Apellido Materno:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            placeholder="Apellido Materno del Alumno" 
            required 
            type="text"
          />
        </Form.Group>
        <Form.Group controlId="email">
          <Form.Label>Direccion de correo:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" 
            placeholder="Direccion de correo del Alumno" 
            required 
            type="email"
          />
        </Form.Group>
        <Button variant="dark" block type="submit">Agregar Alumno</Button>
      </Form>
      <Modal show={this.state.show} onHide={this.handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Mensaje del Sistema</Modal.Title>
        </Modal.Header>
        <Modal.Body>{this.state.wasCreated}</Modal.Body>
        <Modal.Footer>
          <Button variant="primary" onClick={this.handleClose}>
            Aceptar
          </Button>
        </Modal.Footer>
      </Modal>
      </>
    )
  }
}


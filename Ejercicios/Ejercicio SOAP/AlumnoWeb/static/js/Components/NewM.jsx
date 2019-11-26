import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class NewM extends Component {
  state = {
    idMateria: "",
    nombreMateria: "",
    noBoleta: "",
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
        idMateria: this.state.idMateria,
        noBoleta: this.state.noBoleta,
        nombreMateria: this.state.nombreMateria
    }

    fetch('createMateria', {
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
        this.setState({wasCreated: "La materia se ha agregado con exito.",show: true})
        this.props.listMateria()
     } else
        this.setState({wasCreated: "La materia no se pudo agregar.", show: true})
    });
  }

  render() {
    return (
      <>
      <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId="idMateria">
          <Form.Label>Id de la Materia:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange}  
            placeholder="No de Boleta" 
            required 
            type="number"
          />
        </Form.Group>
        <Form.Group controlId="nombreMateria">
          <Form.Label>Nombre de la materia:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            placeholder="Nombre de la materia" 
            required 
            type="text"
          />
        </Form.Group>
        <Form.Group controlId="noBoleta">
          <Form.Label>Numero de boleta del alumno:</Form.Label>
          <Form.Control 
            onChange = {this.handleChange} 
            placeholder="Numero de boleta del alumno" 
            required 
            type="text"
          />
        </Form.Group>
        <Button variant="dark" block type="submit">Agregar Materia</Button>
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
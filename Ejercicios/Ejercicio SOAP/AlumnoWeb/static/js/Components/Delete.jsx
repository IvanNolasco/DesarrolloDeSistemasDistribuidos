import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class Delete extends Component {
  state = {
    noBoleta: "",
    wasDeleted: "",
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
    fetch('deleteAlumno', {
      method: 'POST',
      body: JSON.stringify(this.state.noBoleta),
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      if (dataResponse.wasDeleted){
        this.setState({wasDeleted: "El alumno se ha borrado con exito.",show: true})
        this.props.listAlumno()
      }else
        this.setState({wasDeleted: "El alumno no se pudo borrar.", show: true})
    });
    
  }

  render() {
    return (
      <>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="noBoleta">
            <Form.Label>No de Boleta:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              pattern="(\d){10}"
              placeholder="No de Boleta"
              required
              type="text"
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Borrar Alumno</Button>
        </Form>
        <Modal show={this.state.show} onHide={this.handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Mensaje del Sistema</Modal.Title>
          </Modal.Header>
          <Modal.Body>{this.state.wasDeleted}</Modal.Body>
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

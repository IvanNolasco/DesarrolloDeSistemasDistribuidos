import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class Delete extends Component {
  state = {
    claveArticulo: "",
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
    fetch(`http://127.0.0.1:8000/articulos/${this.state.claveArticulo}/`, {
      method: 'DELETE',
      body: JSON.stringify(this.state.claveArticulo),
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(response => {        
      if (response.ok){
        this.setState({wasDeleted: "El articulo se ha borrado con exito.",show: true})
        this.props.listArticulo()
      }else
        this.setState({wasDeleted: "El articulo no se pudo borrar.", show: true})
    });
    
  }

  render() {
    return (
      <>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="claveArticulo">
            <Form.Label>Clave del articulo:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              pattern="A(\d){3}"
              placeholder="Clave del articulo"
              required
              type="text"
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Borrar Articulo</Button>
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
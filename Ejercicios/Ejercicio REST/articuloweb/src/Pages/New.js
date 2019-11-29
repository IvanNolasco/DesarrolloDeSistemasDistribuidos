import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class New extends Component {
  state = {
    claveArticulo: "",
    descripcion: "",
    precio: 0.0,
    existencias: 0,
    wasCreated: "",
    show: false
  }
  handleChange = (e) => {
    const value = e.target.value
    const name = e.target.id
    this.setState({ [name]: value })
  }

  handleClose = () => this.setState({ show: false })

  handleSubmit = (e) => {
    e.preventDefault()
    var data = {
      claveArticulo: this.state.claveArticulo,
      descripcion: this.state.descripcion,
      precio: this.state.precio,
      existencias: this.state.existencias,
    }
    fetch('http://127.0.0.1:8000/articulos/', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(response => {
      if (response.ok){
        this.setState({wasCreated: "El articulo se ha agregado con exito.",show: true})
        this.props.listArticulo()
     } else
        this.setState({wasCreated: "El articulo no se pudo agregar.", show: true})
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
              placeholder="Clave del articulo"
              required
            />
          </Form.Group>
          <Form.Group controlId="descripcion">
            <Form.Label>Descripcion:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Descripcion"
              required
              type="text"
            />
          </Form.Group>
          <Form.Group controlId="precio">
            <Form.Label>Precio:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Precio"
              required
              type="text"
            />
          </Form.Group>
          <Form.Group controlId="existencias">
            <Form.Label>Existencias:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Existencias"
              required
              type="number"
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Agregar Articulo</Button>
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

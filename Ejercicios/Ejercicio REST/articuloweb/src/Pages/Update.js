import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class Update extends Component {
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
    fetch(`http://127.0.0.1:8000/articulos/${this.state.claveArticulo}/`, {
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(response => {
      if (response.ok){
        this.setState({wasCreated: "El articulo se ha modificado con exito.",show: true})
        this.props.listArticulo()
     } else
        this.setState({wasCreated: "El articulo no se pudo modificar.", show: true})
    });
  }

  handleSelect = (e) =>{
    this.setState({[e.target.id]:e.target.value})
    this.findArticulo(e.target.value)
  }

  findArticulo = (claveArticulo) =>{
    fetch(`http://127.0.0.1:8000/articulos/${claveArticulo}/`, {
      method: 'GET',
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(response => {
      return response.json();
    }).then(dataResponse => {
      if (dataResponse)
        this.setState({
          claveArticulo: dataResponse.claveArticulo,
          descripcion: dataResponse.descripcion,
          precio: dataResponse.precio,
          existencias: dataResponse.existencias
        })
    });
  }
  componentDidUpdate(prevProps, prevState) {
    if (prevProps.articulos !== this.props.articulos) {
      this.findArticulo(this.props.articulos[0].claveArticulo)
    }
  }

  render() {
    return (
      <>
        <Form onSubmit={this.handleSubmit}>
        <Form.Group controlId="claveArticulo">
            <Form.Label>Clave del Articulo</Form.Label>
            <Form.Control as="select" onChange={this.handleSelect}>
              {
                this.props.articulos.map(articulo => {
                return <option key={articulo.claveArticulo} value={articulo.claveArticulo}>{articulo.claveArticulo}</option> 
                })
              }
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="descripcion">
            <Form.Label>Descripcion:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Descripcion"
              required
              type="text"
              value = {this.state.descripcion}
            />
          </Form.Group>
          <Form.Group controlId="precio">
            <Form.Label>Precio:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Precio"
              required
              type="text"
              value = {this.state.precio}
            />
          </Form.Group>
          <Form.Group controlId="existencias">
            <Form.Label>Existencias:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Existencias"
              required
              type="number"
              value = {this.state.existencias}
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Modificar Articulo</Button>
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

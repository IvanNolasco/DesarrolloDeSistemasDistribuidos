import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class UpdateM extends Component {
  state = {
    idMateria: null,
    nombreMateria: "",
    noBoleta: "",
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
    var data = {
      idMateria: this.state.idMateria,
      noBoleta: this.state.noBoleta,
      nombreMateria: this.state.nombreMateria
    }
    e.preventDefault()
    fetch('updateMateria', {
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
      if (dataResponse.wasUpdated) {
        this.setState({ wasUpdated: "La materia se ha modificado con exito.", show: true })
        this.props.listMateria()
      } else
        this.setState({ wasUpdated: "La materia no se pudo modificar.", show: true })
    });
  }

  handleSelect = (e) => {
    this.setState({ [e.target.id]: e.target.value })
    this.findMateria(e.target.value)
  }

  findMateria = (idMateria) => {
    fetch('findMateria', {
      method: 'POST',
      body: JSON.stringify(idMateria),
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      if (dataResponse.materia)
        this.setState({
          nombreMateria: dataResponse.materia.nombreMateria,
          noBoleta: dataResponse.materia.noBoleta.noBoleta,
        })
    });
  }
  componentDidUpdate(prevProps, prevState) {
    if (prevProps.materias != this.props.materias) {
      this.findMateria(this.props.materias[0].idMateria)
    }
  }



  render() {
    var {
      nombreMateria,
      noBoleta
    } = this.state
    return (
      <>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="idMateria">
            <Form.Label>Id de la Materia:</Form.Label>
            <Form.Control as="select" onChange={this.handleSelect}>
              {
                this.props.materias.map(materia => {
                return <option key={materia.idMateria} value={materia.idMateria}>{materia.idMateria}</option> 
                })
              }
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="nombreMateria">
            <Form.Label>Nombre de la materia:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Nombre de la materia"
              required
              type="text"
              value={nombreMateria}
            />
          </Form.Group>
          <Form.Group controlId="noBoleta">
            <Form.Label>Numero de boleta del alumno:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Numero de boleta del alumno"
              required
              type="text"
              value={noBoleta}
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Agregar Materia</Button>
        </Form>
        <Modal show={this.state.show} onHide={this.handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Mensaje del Sistema</Modal.Title>
          </Modal.Header>
          <Modal.Body>{this.state.wasUpdated}</Modal.Body>
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

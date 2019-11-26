import React, { Component } from 'react'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Modal from 'react-bootstrap/Modal'

export default class Update extends Component {
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
    var data = {
      noBoleta: this.state.noBoleta,
      nombre: this.state.nombre,
      paterno: this.state.paterno,
      materno: this.state.materno,
      email: this.state.email,
    }

    e.preventDefault()
    fetch('updateAlumno', {
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
      if (dataResponse.wasUpdated){
        this.setState({wasUpdated: "El alumno se ha modificado con exito.",show: true})
        this.props.listAlumno()
      }else
        this.setState({wasUpdated: "El alumno no se pudo modificar.", show: true})
    });
  }

  handleSelect = (e) =>{
    this.setState({[e.target.id]:e.target.value})
    this.findAlumno(e.target.value)
  }

  findAlumno = (noBoleta) =>{
    fetch('findAlumno', {
      method: 'POST',
      body: JSON.stringify(noBoleta),
      headers: {
        "X-CSRFToken": csrftoken,
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
    }).then(function (response) {
      return response.json();
    }).then(dataResponse => {
      if (dataResponse.alumno)
        this.setState({
          nombre: dataResponse.alumno.nombre,
          paterno: dataResponse.alumno.paterno,
          materno: dataResponse.alumno.materno,
          email: dataResponse.alumno.email
        })
    });
  }
  componentDidUpdate(prevProps, prevState) {
    if (prevProps.alumnos != this.props.alumnos) {
      this.findAlumno(this.props.alumnos[0].noBoleta)
    }
  }
  
  

  render() {
  var {
    nombre,
    paterno,
    materno,
    email
  } = this.state

    return (
      <>
        <Form onSubmit={this.handleSubmit}>
          <Form.Group controlId="noBoleta">
            <Form.Label>No de Boleta</Form.Label>
            <Form.Control as="select" onChange={this.handleSelect}>
              {
                this.props.alumnos.map(alumno => {
                return <option key={alumno.noBoleta} value={alumno.noBoleta}>{alumno.noBoleta}</option> 
                })
              }
            </Form.Control>
          </Form.Group>
          <Form.Group controlId="nombre">
            <Form.Label>Nombre(s):</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Nombre del alumno"
              required
              type="text"
              value={nombre}
            />
          </Form.Group>
          <Form.Group controlId="paterno">
            <Form.Label>Apellido Paterno:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Apellido Paterno del Alumno"
              required
              type="text"
              value={paterno}
            />
          </Form.Group>
          <Form.Group controlId="materno">
            <Form.Label>Apellido Materno:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              placeholder="Apellido Materno del Alumno"
              required
              type="text"
              value={materno}
            />
          </Form.Group>
          <Form.Group controlId="email">
            <Form.Label>Direccion de correo:</Form.Label>
            <Form.Control
              onChange={this.handleChange}
              pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"
              placeholder="Direccion de correo del Alumno"
              required
              type="email"
              value={email}
            />
          </Form.Group>
          <Button variant="dark" block type="submit">Actualizar Alumno</Button>
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

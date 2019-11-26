import React from 'react'
import Table from 'react-bootstrap/Table'

export default function List(props) {
    return (
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
                  props.alumnos.map(alumno => {
                    return (
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
    )
}

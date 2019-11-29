import React from 'react'
import Table from 'react-bootstrap/Table'

export default function List(props) {
    return (
        <Table striped>
              <thead>
                <tr>
                  <th>Clave de Materia</th>
                  <th>Nombre de la Materia</th>
                  <th>Alumno</th>
                </tr>
              </thead>
              <tbody>
                {
                  props.materias.map(materia => {
                    return (
                      <tr key={materia.idMateria}>
                          <td>{materia.idMateria}</td>
                        <td>{materia.nombreMateria}</td>
                        <td>{materia.noBoleta.nombre}</td>
                      </tr>
                    )
                    })
                }
              </tbody>
            </Table>
    )
}

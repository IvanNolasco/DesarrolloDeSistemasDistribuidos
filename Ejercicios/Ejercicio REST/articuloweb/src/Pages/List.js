import React from 'react'
import Table from 'react-bootstrap/Table'

export default function List(props) {
    return (
        <Table striped>
              <thead>
                <tr>
                  <th>Clave del articulo</th>
                  <th>Descripcion</th>
                  <th>Precio</th>
                  <th>Existencias</th>
                </tr>
              </thead>
              <tbody>
                {
                  props.articulos.map(articulo => {
                    return (
                      <tr key={articulo.claveArticulo}>
                        <td>{articulo.claveArticulo}</td>
                        <td>{articulo.descripcion}</td>
                        <td>${articulo.precio}</td>
                        <td>{articulo.existencias}</td>
                      </tr>
                    )
                  })
                }
              </tbody>
            </Table>
    )
}

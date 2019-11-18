from zeep import Client
from .alumno import Alumno


class Controller:
    wsdl = 'http://localhost:8080/AlumnoWS/AlumnoWS?WSDL'
    client = Client(wsdl)

    def listAlumno(self):
        alumnos = []
        lista = self.client.service.listAlumno()
        for a in lista:
            alumno = Alumno(a['noBoleta'], a['nombre'], a['paterno'], a['materno'], a['email'])
            alumnos.append(alumno)
        return alumnos

    def createAlumno(self, alumno):
        return self.client.service.createAlumno(alumno.dic())

    def findAlumno(self, noBoleta):
        return self.client.service.findAlumno(noBoleta)

    def updateAlumno(self, alumno):
        return self.client.service.updateAlumno(alumno.dic())

    def deleteAlumno(self, noBoleta):
        return self.client.service.deleteAlumno(noBoleta)

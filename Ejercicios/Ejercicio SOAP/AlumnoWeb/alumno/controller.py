from zeep import Client
from lxml import etree
from .alumno import Alumno
from .materia import Materia


class Controller:

    def __init__(self):
        self.client = Client('http://localhost:8080/EjercicioAlumno/AlumnoWS?WSDL')

    def listAlumno(self):
        alumnos = []
        lista = self.client.service.listAlumno()
        for a in lista:
            alumno = Alumno(a['noBoleta'], a['nombre'], a['paterno'], a['materno'], a['email'])
            alumnos.append(alumno)
        return alumnos

    def listMateria(self):
        materias = []
        lista = self.client.service.listMateria()
        for m in lista:
            a = Alumno(
                m['noBoleta']['noBoleta'],
                m['noBoleta']['nombre'],
                m['noBoleta']['paterno'],
                m['noBoleta']['materno'],
                m['noBoleta']['email']
            )
            materia = Materia(m['idMateria'], a, m['nombreMateria'])
            materias.append(materia)
        return materias

    def createAlumno(self, alumno):
        """
            <?xml version='1.0' encoding='utf-8'?>
            <soap-env:Envelope xmlns:soap-env="http://schemas.xmlsoap.org/soap/envelope/">
            <soap-env:Header xmlns:wsa="http://www.w3.org/2005/08/addressing">
                <wsa:Action>http://alumno.me.org/AlumnoWS/createAlumnoRequest</wsa:Action>
                <wsa:MessageID>urn:uuid:a8c7025a-63c1-441a-ad67-b2d88be6488c</wsa:MessageID>
                <wsa:To>http://localhost:8080/AlumnoWS/AlumnoWS</wsa:To>
            </soap-env:Header>
            <soap-env:Body>
                <ns0:createAlumno xmlns:ns0="http://alumno.me.org/">
                <alumno>
                    <email>danicrush@gmail.com</email>
                    <materno>García</materno>
                    <noBoleta>2017630001</noBoleta>
                    <nombre>Daniela</nombre>
                    <paterno>Cruz</paterno>
                </alumno>
                </ns0:createAlumno>
            </soap-env:Body>
            </soap-env:Envelope>
        """
        return self.client.service.createAlumno(alumno)

    def createMateria(self, materia):
        return self.client.service.createMateria(**materia)

    def findAlumno(self, noBoleta):
        a = self.client.service.findAlumno(noBoleta)
        alumno = Alumno(a['noBoleta'], a['nombre'], a['paterno'], a['materno'], a['email'])
        return alumno

    def findMateria(self, idMateria):
        m = self.client.service.findMateria(idMateria)
        a = Alumno(
                m['noBoleta']['noBoleta'],
                m['noBoleta']['nombre'],
                m['noBoleta']['paterno'],
                m['noBoleta']['materno'],
                m['noBoleta']['email']
            )
        materia = Materia(m['idMateria'], a, m['nombreMateria'])
        return materia

    def updateAlumno(self, alumno):
        return self.client.service.updateAlumno(alumno)

    def updateMateria(self, materia):
        return self.client.service.updateMateria(materia)

    def deleteAlumno(self, noBoleta):
        return self.client.service.deleteAlumno(noBoleta)

    def deleteMateria(self, idMateria):
        return self.client.service.deleteMateria(idMateria)

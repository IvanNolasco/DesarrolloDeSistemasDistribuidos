from .alumno import Alumno


class Materia:
    def __init__(self, idMateria=None, noBoleta=Alumno(), nombreMateria=''):
        self.idMateria = idMateria
        self.noBoleta = noBoleta
        self.nombreMateria = nombreMateria

    def dic(self):
        return {
            'idMateria': self.idMateria,
            'noBoleta': self.noBoleta.dic(),
            'nombreMateria': self.nombreMateria
        }

    def listValues(materias):
        listDict = []
        for m in materias:
            listDict.append(m.dic())
        return listDict

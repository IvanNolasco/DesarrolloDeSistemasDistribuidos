class Alumno:

    def __init__(self, noBoleta='', nombre='', paterno='', materno='', email=''):
        self.noBoleta = noBoleta
        self.nombre = nombre
        self.paterno = paterno
        self.materno = materno
        self.email = email

    def __str__(self):
        return self.noBoleta+","+self.nombre+","+self.paterno+","+self.materno+","+self.email

    def dic(self):
        return{
            'noBoleta': self.noBoleta,
            'nombre': self.nombre,
            'paterno': self.paterno,
            'materno': self.materno,
            'email': self.email
        }

    def listValues(alumnos):
        listDict = []
        for a in alumnos:
            listDict.append(a.dic())
        return listDict

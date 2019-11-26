from django.shortcuts import render
from .controller import Controller
from .alumno import Alumno
from .materia import Materia
from django.http import JsonResponse
from requests.exceptions import ConnectionError
import json


# Create your views here.
def indexView(request):
    return render(request, "index.html")


def listAlumno(request):
    data = {}
    try:
        controller = Controller()
        alumnos = controller.listAlumno()
        alumnos = Alumno.listValues(alumnos)
    except ConnectionError:
        print("No se pudo conectar al web service")
        alumnos = {}
    data.update(alumnosList=alumnos)
    return JsonResponse(data)


def listMateria(request):
    data = {'listed': True}
    try:
        controller = Controller()
        materias = controller.listMateria()
        materias = Materia.listValues(materias)
    except ConnectionError:
        print("No se pudo conectar al web service")
        materias = {}
    data.update(materiasList=materias)
    return JsonResponse(data)


def createAlumno(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        wasCreated = controller.createAlumno(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasCreated = False
    data.update(wasCreated=wasCreated)
    return JsonResponse(data)


def createMateria(request):
    reqData = json.loads(request.body.decode('utf-8'))
    reqData['idMateria'] = int(reqData['idMateria'])
    data = {}
    try:
        controller = Controller()
        wasCreated = controller.createMateria(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasCreated = False
    data.update(wasCreated=wasCreated)
    return JsonResponse(data)


def deleteAlumno(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        wasDeleted = controller.deleteAlumno(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasDeleted = False
    data.update(wasDeleted=wasDeleted)
    return JsonResponse(data)


def deleteMateria(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        wasDeleted = controller.deleteMateria(int(reqData))
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasDeleted = False
    data.update(wasDeleted=wasDeleted)
    return JsonResponse(data)


def findAlumno(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        alumno = controller.findAlumno(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        alumno = None
    data.update(alumno=alumno.dic())
    return JsonResponse(data)


def findMateria(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        materia = controller.findMateria(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        materia = None
    data.update(materia=materia.dic())
    return JsonResponse(data)


def updateAlumno(request):
    reqData = json.loads(request.body.decode('utf-8'))
    data = {}
    try:
        controller = Controller()
        wasUpdated = controller.updateAlumno(reqData)
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasUpdated = False
    data.update(wasUpdated=wasUpdated)
    return JsonResponse(data)


def updateMateria(request):
    reqData = json.loads(request.body.decode('utf-8'))
    reqData['idMateria'] = int(reqData['idMateria'])
    data = {}
    try:
        controller = Controller()
        alumno = controller.findAlumno(reqData['noBoleta'])
        materia = Materia(reqData['idMateria'], alumno, reqData['nombreMateria'])
        wasUpdated = controller.updateMateria(materia.dic())
    except ConnectionError:
        print("No se pudo conectar al web service")
        wasUpdated = False
    data.update(wasUpdated=wasUpdated)
    return JsonResponse(data)
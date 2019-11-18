from django.shortcuts import render
from .controller import Controller
from .alumno import Alumno
from django.http import JsonResponse


# Create your views here.
def indexView(request):
    return render(request, "index.html")


def listAlumno(request):
    data = {}
    controller = Controller()
    alumnos = controller.listAlumno()
    print(alumnos)
    alumnos = Alumno.listValues(alumnos)
    data.update(alumnosList=alumnos)
    print(alumnos)
    return JsonResponse(data)

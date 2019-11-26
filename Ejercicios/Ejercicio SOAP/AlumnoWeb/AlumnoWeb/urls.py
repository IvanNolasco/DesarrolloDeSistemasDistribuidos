"""AlumnoWeb URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url
from alumno import views

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^$', views.indexView, name='index'),
    url('listAlumno', views.listAlumno, name='listAlumno'),
    url('listMateria', views.listMateria, name='listMateria'),
    url('createAlumno', views.createAlumno, name='createAlumno'),
    url('createMateria', views.createMateria, name='createMateria'),
    url('deleteAlumno', views.deleteAlumno, name='deleteAlumno'),
    url('deleteMateria', views.deleteMateria, name='deleteMateria'),
    url('updateAlumno', views.updateAlumno, name='updateAlumno'),
    url('updateMateria', views.updateMateria, name='updateMateria'),
    url('findAlumno', views.findAlumno, name='findAlumno'),
    url('findMateria', views.findMateria, name='findMateria')
]

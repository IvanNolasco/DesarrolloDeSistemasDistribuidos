from django.db import models


# Create your models here.
class Articulo(models.Model):
    claveArticulo = models.CharField(max_length=40, primary_key=True)
    descripcion = models.CharField(max_length=30)
    precio = models.DecimalField(max_digits=5, decimal_places=2)
    existencias = models.IntegerField()

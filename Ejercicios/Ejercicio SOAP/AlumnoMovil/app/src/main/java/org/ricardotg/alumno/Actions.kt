package org.ricardotg.alumno

import android.os.StrictMode
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE
import java.util.*
import kotlin.collections.ArrayList

val NAMESPACE = "http://alumno.com/"
val URL = "http://10.100.76.97:8080/EjercicioAlumno/AlumnoWS?WSDL"
val METHOD_NAME = "createAlumno"

private fun getSOAPResponse(request: SoapObject): Any {
    val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
    envelope.setOutputSoapObject(request)
    val ht = HttpTransportSE(URL)
    try {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        ht.call("http://alumno.com/${request.name}",envelope)
    }catch (e:Exception){
            e.printStackTrace()
    }
    return envelope.response

}

fun getAlumnoList(): List<Alumno>{
    val request = SoapObject(NAMESPACE, "listAlumno")
    val soapAlumnos: Vector<SoapObject> = getSOAPResponse(request) as Vector<SoapObject>
    val countAlumnos = soapAlumnos.size
    var alumnos:ArrayList<Alumno> = ArrayList()

    for (i in 0 until countAlumnos){
        val soapAlumno = soapAlumnos.get(i) as SoapObject
        var a = Alumno()
        a.noBoleta = soapAlumno.getPrimitivePropertyAsString("noBoleta")
        a.nombre = soapAlumno.getPrimitivePropertyAsString("nombre")
        a.paterno = soapAlumno.getPrimitivePropertyAsString("paterno")
        a.materno = soapAlumno.getPrimitivePropertyAsString("materno")
        a.email = soapAlumno.getPrimitivePropertyAsString("email")
        alumnos.add(a)
    }
    return alumnos
}

fun insertAlumno(a: Alumno): Boolean{
    val request = SoapObject(NAMESPACE, "createAlumno")

    val alumnoInfo = SoapObject("","alumno")
    alumnoInfo.addProperty("email",a.email)
    alumnoInfo.addProperty("materno",a.materno)
    alumnoInfo.addProperty("noBoleta",a.noBoleta)
    alumnoInfo.addProperty("nombre",a.nombre)
    alumnoInfo.addProperty("paterno",a.paterno)
    request.addSoapObject(alumnoInfo)

    val soapIsInserted: SoapPrimitive = getSOAPResponse(request) as SoapPrimitive
    return soapIsInserted.toString().toBoolean()
}

fun deleteAlumno(noBoleta: String): Boolean{
    val request = SoapObject(NAMESPACE, "deleteAlumno")
    request.addProperty("noBoleta",noBoleta)
    val soapIsDeleted: SoapPrimitive = getSOAPResponse(request) as SoapPrimitive
    return soapIsDeleted.toString().toBoolean()
}

fun updateAlumno(a: Alumno): Boolean{
    val request = SoapObject(NAMESPACE, "updateAlumno")

    val alumnoInfo = SoapObject("","alumno")
    alumnoInfo.addProperty("email",a.email)
    alumnoInfo.addProperty("materno",a.materno)
    alumnoInfo.addProperty("noBoleta",a.noBoleta)
    alumnoInfo.addProperty("nombre",a.nombre)
    alumnoInfo.addProperty("paterno",a.paterno)
    request.addSoapObject(alumnoInfo)

    val soapIsInserted: SoapPrimitive = getSOAPResponse(request) as SoapPrimitive
    return soapIsInserted.toString().toBoolean()
}

fun findAlumno(noBoleta: String): Alumno{
    val request = SoapObject(NAMESPACE, "findAlumno")
    request.addProperty("noBoleta",noBoleta)
    val soapAlumno: SoapObject = getSOAPResponse(request) as SoapObject
    var a = Alumno()
    a.noBoleta = soapAlumno.getPrimitivePropertyAsString("noBoleta")
    a.nombre = soapAlumno.getPrimitivePropertyAsString("nombre")
    a.paterno = soapAlumno.getPrimitivePropertyAsString("paterno")
    a.materno = soapAlumno.getPrimitivePropertyAsString("materno")
    a.email = soapAlumno.getPrimitivePropertyAsString("email")
    return a
}
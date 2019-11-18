package org.ricardotg.alumno

import android.os.StrictMode
import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.PropertyInfo
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapPrimitive
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

private fun getSoapSerializationEnvelope(request: SoapObject): SoapSerializationEnvelope {
    val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
    // Setted to true for compatibility with what seems to be the default encoding for .Net-Services
    envelope.dotNet = true
    // Setted to true to not add type definitions in the XML-Request
    envelope.implicitTypes = true
    // Setted to false to not add and ID and ROOT label to the envelope
    envelope.isAddAdornments = false
    envelope.setOutputSoapObject(request)
    return envelope
}
private fun getHttpTransportSE(): HttpTransportSE {
    val ht = HttpTransportSE("http://192.168.0.6:8080/AlumnoWS/AlumnoWS?WSDL")
    ht.debug = true
    return ht
}
private fun getSOAPResponse(request: SoapObject): Any {
    val envelope = getSoapSerializationEnvelope(request)
    val ht = getHttpTransportSE()
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    ht.call("http://alumno.me.org/${request.name}",envelope)
    System.out.println("SOAP Request " + ht.requestDump)
    return envelope.bodyIn
}

fun getAlumnoList(): List<Alumno>{
    val request = SoapObject("http://alumno.me.org/", "listAlumno")
    val soapAlumnos: SoapObject = getSOAPResponse(request) as SoapObject
    val countAlumnos = soapAlumnos.propertyCount
    var alumnos:ArrayList<Alumno> = ArrayList()

    for (i in 0 until countAlumnos){
        val soapAlumno = soapAlumnos.getProperty(i) as SoapObject
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

fun insertAlumno(a: String): Boolean{
    val request = SoapObject("http://alumno.me.org/", "findAlumno")
    /*val alumnoInfo = SoapObject("","alumno")
    alumnoInfo.addProperty("email",a.email)
    alumnoInfo.addProperty("materno",a.materno)
    alumnoInfo.addProperty("noBoleta",a.noBoleta)
    alumnoInfo.addProperty("nombre",a.nombre)
    alumnoInfo.addProperty("paterno",a.paterno)
    request.addSoapObject(alumnoInfo)*/
    request.addProperty("noBoleta",a)
    val soapIsInserted: SoapObject = getSOAPResponse(request) as SoapObject
    return soapIsInserted.getProperty(0).toString().toBoolean()
}
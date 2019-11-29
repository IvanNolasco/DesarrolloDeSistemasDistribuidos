package com.ricardotg.alumnomovil.ui.update

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.ricardotg.alumnomovil.R
import org.ricardotg.alumno.Alumno
import org.ricardotg.alumno.findAlumno
import org.ricardotg.alumno.getAlumnoList
import org.ricardotg.alumno.updateAlumno


class UpdateFragment : Fragment() {
    lateinit var boletaTxt: Spinner
    lateinit var nombreTxt: EditText
    lateinit var paternoTxt: EditText
    lateinit var maternoTxt: EditText
    lateinit var correoTxt: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_update, container, false)
        val insertBtn = root.findViewById<Button>(R.id.updateBtn)

        val alumnos:List<Alumno> = getAlumnoList()
        var items: MutableList<String> = ArrayList()
        for (a in alumnos) items.add(a.noBoleta.toString())
        var adapter = ArrayAdapter<String>(root.context,R.layout.support_simple_spinner_dropdown_item,items)
        boletaTxt = root.findViewById(R.id.boletaTxt)
        boletaTxt.adapter = adapter
        val a = findAlumno(items[0])

        nombreTxt = root.findViewById(R.id.nombreTxt)
        nombreTxt.setText(a.nombre, TextView.BufferType.EDITABLE)
        paternoTxt = root.findViewById(R.id.paternoTxt)
        paternoTxt.setText(a.paterno, TextView.BufferType.EDITABLE)
        maternoTxt = root.findViewById(R.id.maternoTxt)
        maternoTxt.setText(a.materno, TextView.BufferType.EDITABLE)
        correoTxt = root.findViewById(R.id.correoTxt)
        correoTxt.setText(a.email, TextView.BufferType.EDITABLE)
        insertBtn.setOnClickListener { modificar() }
        boletaTxt.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val a = findAlumno(items[position])
                nombreTxt.setText(a.nombre, TextView.BufferType.EDITABLE)
                paternoTxt.setText(a.paterno, TextView.BufferType.EDITABLE)
                maternoTxt.setText(a.materno, TextView.BufferType.EDITABLE)
                correoTxt.setText(a.email, TextView.BufferType.EDITABLE)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        return root
    }

    fun modificar(){
        var a= Alumno()
        a.noBoleta = boletaTxt.selectedItem.toString()
        a.nombre = nombreTxt.text.toString()
        a.paterno = paternoTxt.text.toString()
        a.materno = maternoTxt.text.toString()
        a.email = correoTxt.text.toString()
        var confirm = AlertDialog.Builder(activity)
        if (updateAlumno(a)){
            confirm.setMessage("Se ha modificado el Alumno con exito")
        }else{
            confirm.setMessage("No se modificó al alumno")
        }
        confirm.setNeutralButton("Aceptar"){dialog, which ->
            dialog.cancel()
        }
        val dialog: AlertDialog = confirm.create()
        dialog.show()
    }
}
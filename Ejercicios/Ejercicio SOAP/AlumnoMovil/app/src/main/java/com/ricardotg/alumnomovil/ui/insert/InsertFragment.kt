package com.ricardotg.alumnomovil.ui.insert

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ricardotg.alumnomovil.R
import org.ricardotg.alumno.Alumno
import org.ricardotg.alumno.insertAlumno

class InsertFragment : Fragment() {
    lateinit var boletaTxt: EditText
    lateinit var nombreTxt: EditText
    lateinit var paternoTxt: EditText
    lateinit var maternoTxt: EditText
    lateinit var correoTxt: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_insert, container, false)
        val insertBtn = root.findViewById<Button>(R.id.insertBtn)
        boletaTxt = root.findViewById(R.id.boletaTxt)
        nombreTxt = root.findViewById(R.id.nombreTxt)
        paternoTxt = root.findViewById(R.id.paternoTxt)
        maternoTxt = root.findViewById(R.id.maternoTxt)
        correoTxt = root.findViewById(R.id.correoTxt)
        insertBtn.setOnClickListener { agregar() }
        return root
    }
    fun agregar() {
        var a= Alumno()
        a.noBoleta = boletaTxt.text.toString()
        a.nombre = nombreTxt.text.toString()
        a.paterno = paternoTxt.text.toString()
        a.materno = maternoTxt.text.toString()
        a.email = correoTxt.text.toString()
        var confirm = AlertDialog.Builder(activity)
        if (insertAlumno(boletaTxt.text.toString())){
            confirm.setMessage("Se ha agregado el Alumno con exito")
        }else{
            confirm.setMessage("No se Agrego al alumno")
        }
        confirm.setNeutralButton("Aceptar"){dialog, which ->
            dialog.cancel()
        }
        val dialog: AlertDialog = confirm.create()
        dialog.show()
    }
}
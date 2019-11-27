package com.ricardotg.alumnomovil.ui.delete

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ricardotg.alumnomovil.R
import org.ricardotg.alumno.deleteAlumno
import org.ricardotg.alumno.insertAlumno

class DeleteFragment : Fragment() {
    lateinit var boletaTxt: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_delete, container, false)

        val deleteBtn = root.findViewById<Button>(R.id.deleteBtn)
        boletaTxt = root.findViewById(R.id.boletaTxt)
        deleteBtn.setOnClickListener { eliminar() }
        return root
    }

    fun eliminar(){
        val noBoleta = boletaTxt.text.toString()
        var confirm = AlertDialog.Builder(activity)
        if (deleteAlumno(noBoleta)){
            confirm.setMessage("Se ha eliminado el Alumno con exito")
        }else{
            confirm.setMessage("No se eliminó al alumno")
        }
        confirm.setNeutralButton("Aceptar"){dialog, which ->
            dialog.cancel()
        }
        val dialog: AlertDialog = confirm.create()
        dialog.show()
    }

}
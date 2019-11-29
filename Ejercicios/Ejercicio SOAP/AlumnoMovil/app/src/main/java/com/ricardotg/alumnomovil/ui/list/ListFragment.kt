package com.ricardotg.alumnomovil.ui.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ricardotg.alumnomovil.R
import de.codecrafters.tableview.TableDataAdapter
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter
import org.ricardotg.alumno.Alumno
import org.ricardotg.alumno.AlumnoTableDataAdapter
import org.ricardotg.alumno.getAlumnoList

class ListFragment : Fragment() {
    private var header= arrayOf("Boleta","Nombre","Paterno","Materno","Correo")

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        var tableview: TableView<String> = root.findViewById(R.id.tablaAlumnos)
        var alumnos:List<Alumno> = getAlumnoList()

        tableview.headerAdapter = SimpleTableHeaderAdapter(root.context,*header)
        tableview.dataAdapter = AlumnoTableDataAdapter(root.context,alumnos) as TableDataAdapter<String>
        return root
    }
}
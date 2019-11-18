package org.ricardotg.alumno

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.codecrafters.tableview.TableDataAdapter
import org.ricardotg.alumno.Alumno

class AlumnoTableDataAdapter : TableDataAdapter<Alumno> {
    constructor(context: Context, data:List<Alumno>): super(context,data)
     override fun getCellView(rowIndex:Int,columnIndex:Int, parentView: ViewGroup): View? {
         var alumno:Alumno = getRowData(rowIndex)
         var renderedView: View? = null
         var textView = TextView(context)
         when (columnIndex) {
             0 -> {
                 textView.setText(alumno.noBoleta)
                 renderedView =  textView
             }
             1 -> {
                 textView.setText(alumno.nombre)
                 renderedView =  textView
             }
             2 -> {
                 textView.setText(alumno.paterno)
                 renderedView =  textView
             }
             3 -> {
                 textView.setText(alumno.materno)
                 renderedView =  textView
             }
             4 -> {
                 textView.setText(alumno.email)
                 renderedView =  textView
             }
         }
         return renderedView
     }
}
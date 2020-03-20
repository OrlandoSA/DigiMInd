package lumbreras.marquez.mydigimind.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.elemento_view.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import lumbreras.marquez.mydigimind.Actividad
import lumbreras.marquez.mydigimind.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var adaptador: AdaptadorActividad? = null

    companion object {
        var actividades = ArrayList<Actividad>()
        var primero = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        if (primero) {
            cargarActividades()
            primero = false
        }

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        adaptador = AdaptadorActividad(root.context, actividades)
        root.gridViewActividades.adapter = adaptador

        return root
    }

    fun cargarActividades() {

        actividades.add(Actividad("Practice 1", arrayListOf("Tuesday"), "17:30"))
        actividades.add(Actividad("Practice 2", arrayListOf("Monday", "Sunday"), "17:00"))
        actividades.add(Actividad("Practice 3", arrayListOf("Wednesday"), "14:00"))
        actividades.add(Actividad("Practice 4", arrayListOf("Saturday"), "11:00"))
        actividades.add(Actividad("Practice 5", arrayListOf("Friday"), "13:00"))
        actividades.add(Actividad("Practice 6", arrayListOf("Thursday"), "10:40"))
        actividades.add(Actividad("Practice 7", arrayListOf("Monday"), "12:00"))
        actividades.add(Actividad("Practice 8", arrayListOf("Sunday"), "19:00"))

    }

    class AdaptadorActividad : BaseAdapter {

        var act = ArrayList<Actividad>()
        var contexto: Context? = null

        constructor(contexto: Context, peliculas: ArrayList<Actividad>) {
            this.contexto = contexto
            this.act = peliculas
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var act = act[position]

            var inflater =
                contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflater.inflate(R.layout.elemento_view, null)
            vista.tx_titulo.setText(act.titulo)
            vista.tx_desc.setText(act.dias.toString())
            vista.tx_hora.setText(act.hora)

            return vista

        }

        //Estos tres de abajo no importan de momento se les pone return cualquier cosa
        override fun getItem(position: Int): Any {
            return act[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return act.size
        }

    }

}

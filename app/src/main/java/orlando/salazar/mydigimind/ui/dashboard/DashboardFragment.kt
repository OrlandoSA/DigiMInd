package lumbreras.marquez.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import lumbreras.marquez.mydigimind.Actividad
import lumbreras.marquez.mydigimind.R
import lumbreras.marquez.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        //dashboardViewModel.text.observe(this, Observer {
        //    textView.text = it
        //})
        root.btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)

                btn_time.text= SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context,timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                true).show()
        }
        root.btn_finalizar.setOnClickListener {
            var titulo = et_task.text.toString()
            var hora = btn_time.text.toString()
            var dias= ArrayList<String>()

            if(checkMonday.isChecked)
                dias.add("Monday")
            if(checkTuesday.isChecked)
                dias.add("Tuesday")
            if(checkWednesday.isChecked)
                dias.add("Wednesday")
            if(checkThursday.isChecked)
                dias.add("Thursday")
            if(checkFriday.isChecked)
                dias.add("Friday")
            if(checkSaturday.isChecked)
                dias.add("Saturday")
            if(checkSunday.isChecked)
                dias.add("Sunday")

            var tarea= Actividad(titulo,dias,hora)
            HomeFragment.actividades.add(tarea)
            Toast.makeText(root.context,"New task added", Toast.LENGTH_SHORT).show()

        }
        return root
    }


}
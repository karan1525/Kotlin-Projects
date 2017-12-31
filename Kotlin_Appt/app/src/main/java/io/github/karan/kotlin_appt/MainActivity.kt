package io.github.karan.kotlin_appt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var appointmentArrayList = ArrayList<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createSomeTestAppointmentsToStartWith()
    }

    private fun createSomeTestAppointmentsToStartWith() {
        appointmentArrayList.add(Appointment("Doctors Visit", "Health", "Oct",
                9, 2016, 9, 0, "AM"))
        appointmentArrayList.add(Appointment("Hair Cut appointment", "Personal",
                "Oct", 10, 2016, 9, 30,
                "AM"))
        appointmentArrayList.add(Appointment("Meeting with Accountant", "Personal",
                "Oct", 11, 2016, 11, 0,
                "AM"))
        appointmentArrayList.add(Appointment("Boss/HR Meeting", "Work", "Oct",
                12, 2016, 2, 30, "PM"))
        appointmentArrayList.add(Appointment("Teacher Conference", "School",
                "Nov", 1, 2016, 9, 30,
                "AM"))
        appointmentArrayList.add(Appointment("Dentist For Son", "Health",
                "Nov", 1, 2016, 9, 30,
                "AM"))
        appointmentArrayList.add(Appointment("Dinner With Friends", "Other",
                "Nov", 1, 2016, 9, 30,
                "AM"))

        for (i in appointmentArrayList.indices) {
            populateTable(i)
        }
    }

    private fun setToDateAndTime(appointment: Appointment): String {
        val currentDateAndTime = System.currentTimeMillis() //Today's Date
        val formatDate = SimpleDateFormat("MMM d, yyyy", Locale.US) //Date Format

        val todaysDate = formatDate.format(currentDateAndTime) //Today's date formatted
        val passDate = appointment.monthDate + " " + appointment.dayDate + ", " +
                appointment.yearDate //Tasks date formatted the same way

        return if (todaysDate == passDate) { //Compare today's date and passed date,
            // return time if dates match
            appointment.hourTime.toString() + ":" + appointment.minuteTime + " " +
                    appointment.AMorPMTime
        } else appointment.monthDate + " " + appointment.dayDate + ", " +
                appointment.yearDate
        //Otherwise, return the date

    }

    override//Returns information passed from addAppointment Activity
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                //Creates a new appointment with the information passed
                appointmentArrayList.add(Appointment(
                        data.getStringExtra("name"), data.getStringExtra("type"),
                        data.getStringExtra("monthOfYear"),
                        data.getIntExtra("dayOfMonth", 0),
                        data.getIntExtra("year", 1111),
                        data.getIntExtra("hour", 11),
                        data.getIntExtra("minute", 11),
                        data.getStringExtra("AMorPM")))
                //Displays new appointment on in the table
                populateTable(appointmentArrayList.size - 1)
            }
        }
    }

    private fun populateTable(arrayListCounter: Int) {
        val appointmentTBL = findViewById<View>(R.id.tblTaskContent) as TableLayout

        val newTableRow = TableRow(this)
        val lp = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        newTableRow.layoutParams = lp

        val txtvName = TextView(this)
        txtvName.layoutParams = lp
        txtvName.gravity = Gravity.CENTER
        txtvName.text = appointmentArrayList[arrayListCounter].name
        txtvName.width = 140
        txtvName.textSize = 12f
        txtvName.textAlignment = View.TEXT_ALIGNMENT_CENTER

        val txtvType = TextView(this)
        txtvType.layoutParams = lp
        txtvType.gravity = Gravity.CENTER
        txtvType.text = appointmentArrayList[arrayListCounter].type
        txtvType.width = 93
        txtvType.textSize = 12f
        txtvType.textAlignment = View.TEXT_ALIGNMENT_CENTER

        val txtvDate = TextView(this)
        txtvDate.layoutParams = lp
        txtvDate.gravity = Gravity.CENTER
        txtvDate.text = setToDateAndTime(appointmentArrayList[arrayListCounter])
        txtvDate.width = 97
        txtvDate.textSize = 12f
        txtvDate.textAlignment = View.TEXT_ALIGNMENT_CENTER

        newTableRow.addView(txtvName)
        newTableRow.addView(txtvType)
        newTableRow.addView(txtvDate)
        appointmentTBL.addView(newTableRow, arrayListCounter + 1)

    }

    fun addAppointmentBtn(view: View) {
        startActivityForResult(Intent(this, AddAppointmentActivity::class.java),
                1)
    }
}
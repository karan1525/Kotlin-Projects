@file:Suppress("OverridingDeprecatedMember", "DEPRECATION")

package io.github.karan.kotlin_appt

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import java.util.Calendar

class AddAppointmentActivity : AppCompatActivity() {

    private var txtDate = findViewById<View>(R.id.txttvDate) as TextView
    private var txtTime = findViewById<View>(R.id.txtvTime) as TextView
//    internal var txtDate: TextView
//    internal var txtTime: TextView

    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0

    private var hour: Int = 0
    private var minute: Int = 0

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
        // when dialog box is closed, below method will be called.
        year = selectedYear
        month = selectedMonth
        day = selectedDay

        // set selected date into textview
        txtDate.text = StringBuilder().append(month + 1)
                .append("-").append(day).append("-").append(year)
                .append(" ")
    }

    private val timePickerListener = TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
        hour = selectedHour
        minute = selectedMinute

        // set current time into textview
        txtTime.text = StringBuilder().append(pad(hour))
                .append(":").append(pad(minute))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        setCurrentDateAndTimeOnView()
    }

    // display current date and time
    private fun setCurrentDateAndTimeOnView() {
        txtDate = findViewById(R.id.txttvDate)
        txtTime = findViewById(R.id.txtvTime)

        val c = Calendar.getInstance()
        year = c.get(Calendar.YEAR)
        month = c.get(Calendar.MONTH)
        day = c.get(Calendar.DAY_OF_MONTH)

        hour = c.get(Calendar.HOUR_OF_DAY)
        minute = c.get(Calendar.MINUTE)

        // set current time into textview
        txtTime.text = StringBuilder().append(pad(hour))
                .append(":").append(pad(minute))

        // set current date into textview
        txtDate.text = StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" ")
    }

    //Displays a new dialog for date picker or time picker
    override fun onCreateDialog(id: Int): Dialog? {
        when (id) {
            DATE_DIALOG_ID -> return DatePickerDialog(this,
                    datePickerListener, year, month, day)
            TIME_DIALOG_ID ->
                // set time picker as current time
                return TimePickerDialog(this,
                        timePickerListener, hour, minute, false)
        }
        return null
    }

    /**When Date Text View is touched, opens a Date Picker Dialog  */
    fun edittxtDate() {
        showDialog(DATE_DIALOG_ID)
    }

    /**When Time Text View is touched, opens a Time Picker Dialog */
    fun edittxtTime() {
        showDialog(TIME_DIALOG_ID)
    }

    //Closes AddAppointmentActivity
    fun btnCancel() {

        finish()
    }

    //Closes AddAppointmentActivity and returns the information entered in each field
    fun btnAddAppointment() {
        val editAppointmentName = findViewById<EditText>(R.id.editTaskName)
        val spinnerAppointmentType = findViewById<Spinner>(R.id.spnTaskType)
        if (!editAppointmentName.text.toString().isEmpty()) {
            val intent = Intent()

            intent.putExtra("name", editAppointmentName.text.toString())

            intent.putExtra("type", spinnerAppointmentType.selectedItem.toString())

            intent.putExtra("monthOfYear", displayTheMonthInCharacters(month))
            intent.putExtra("dayOfMonth", day)
            intent.putExtra("year", year)

            intent.putExtra("hour", formatTheHour(hour))
            intent.putExtra("minute", minute)
            intent.putExtra("AMorPM", amOrPM(hour))

            setResult(Activity.RESULT_OK, intent)

            finish()
        } else {
            val toast = Toast.makeText(this@AddAppointmentActivity, "Please enter an Appointment Name", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    /* Helper Methods */

    //Returns the Month Abbreviation for the corresponding number.
    private fun displayTheMonthInCharacters(passedMonth: Int): String {
        when (passedMonth) {
            0 -> return "Jan"
            1 -> return "Feb"
            2 -> return "Mar"
            3 -> return "Apr"
            4 -> return "May"
            5 -> return "Jun"
            6 -> return "Jul"
            7 -> return "Aug"
            8 -> return "Sept"
            9 -> return "Oct"
            10 -> return "Nov"
            11 -> return "Dec"
        }
        return ""
    }

    //Converts the 24 hours PassedHour to a 12 Hour time.
    private fun formatTheHour(passedHour: Int): Int {
        @Suppress("NAME_SHADOWING")
        var passedHour = passedHour
        if (passedHour > 12) {
            passedHour -= 12
        }
        return passedHour
    }

    //Returns AM or PM depending on the hour (1-24)
    private fun amOrPM(passedHour: Int): String {
        return if (passedHour > 12) {
            "PM"
        } else {
            "AM"
        }
    }


    private fun pad(c: Int): String {
        return if (c >= 10)
            c.toString()
        else
            "0" + c.toString()
    }

    companion object {

        internal val DATE_DIALOG_ID = 999
        internal val TIME_DIALOG_ID = 998
    }
}


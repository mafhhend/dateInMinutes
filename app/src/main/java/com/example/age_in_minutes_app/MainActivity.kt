package com.example.age_in_minutes_app

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tvSelectedDate:TextView;
    lateinit var tvSelectedDateInMinutes: TextView;
    lateinit var btnDatePicker: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker=findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)


        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        //view: the view we are.
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        // if Clicked on OK OnDateSetListener will RUN:
       val dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(this, "$year $month $dayOfMonth", Toast.LENGTH_SHORT).show()
            val SelectedDate = "$dayOfMonth/${month + 1}/$year";
            tvSelectedDate.setText(SelectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(SelectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000;
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes= currentDate!!.time / 60000;
            val differenceInMinutes= currentDateToMinutes - selectedDateInMinutes
            tvSelectedDateInMinutes.setText(differenceInMinutes.toString());


        }, year, month, dayOfMonth)
        //86400000 is one day
        // now we can't select future, only from yesterday
        dpd.datePicker.maxDate = Date().time - 86400000;
        dpd.show();
    }
}
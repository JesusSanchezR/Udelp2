package com.example.datetimepickerdialogexample

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePickerDialog.setOnClickListener{
            //Es para la fecha actual

            //Segunda parte
            //var cal = Calendar.getInstance()
            //var year:Int = cal.get(Calendar.YEAR)
            //var month:Int = cal.get(Calendar.MONTH)
            //var day:Int = cal.get(Calendar.DAY_OF_MONTH)


            //es para fichas fijos
            /*var year:Int = 2020
            var month:Int = 0
            var day:Int = 15
            */

            //esto sirve para el if
            var year:Int
            var month:Int
            var day:Int

            if(txvDatePickerDialog.text.toString().isEmpty()){

                var cal = Calendar.getInstance()
                year = cal.get(Calendar.YEAR)
                month = cal.get(Calendar.MONTH)
                day = cal.get(Calendar.DAY_OF_MONTH)

            }else{
                val myDate = txvDatePickerDialog.text.toString()
                val formatter = DateTimeFormatter.ofPattern("d-MM-yyyy")
                val date = LocalDate.parse(myDate,formatter)

                year = date.year
                month = date.monthValue -1
                day = date.dayOfMonth
            }

            var dpd = DatePickerDialog(this@MainActivity,DatePickerDialog.OnDateSetListener{ datePicker: DatePicker,y: Int,m:Int,d:Int ->

                val dayString = if(d!in 1..9){
                    d.toString()
                }else{
                    //es para agrefar los dos digitos
                    "0$d"
                }
                //para que los meses no empiecen en 0 (se muestren)
                val monthString = if(m !in 0..9){
                    (m+1).toString()
                }else{
                    "0${m+1}"
                }


                txvDatePickerDialog.text="$d-$m-$y"

            },year,month,day)
            dpd.show()

        }


        btnTimePickerDialog.setOnClickListener{

            var hourOfDate: Int = 15
            var minute:Int = 25

            if(txvTimePickerDialog.text.toString().isEmpty()){
                val cal= Calendar.getInstance()
                hourOfDate = cal.get(Calendar.HOUR_OF_DAY)
                minute = cal.get(Calendar.MINUTE)
            }else{
                val myTime = txvTimePickerDialog.text.toString()
                val formatter = DateTimeFormatter.ofPattern("H:mm")
                val time = LocalTime.parse(myTime,formatter)

                hourOfDate = time.hour
                minute = time.minute
            }


            val tpd = TimePickerDialog(this@MainActivity, TimePickerDialog.OnTimeSetListener{ timePicker: TimePicker, h: Int, m: Int ->
                val hourString = if(h !in 1..9){
                    h.toString()
                }else{
                    "0$h"
                }

                val minuteString = if(m !in 1..9){
                    m.toString()
                }else{
                    "0$m"
                }




                txvTimePickerDialog.text = "$hourString:$minuteString"
            },hourOfDate,minute,true)
            //este es para 24  },hourOfDate,minute,false)
            tpd.show()

        }



    }
}

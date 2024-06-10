package com.myappname.temperatureapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var DayText: EditText
    private lateinit var MorningTempText: EditText
    private lateinit var AfternoonTempText: EditText
    private lateinit var NotesText: EditText
    private lateinit var AverageTempText: TextView
    private lateinit var TempText: TextView
    private lateinit var Backbtn: Button
    private lateinit var Savebtn: Button
    private lateinit var Clearbtn: Button
    private lateinit var Nextbtn: Button
    private lateinit var Calculatebtn: Button

    private val dayArray = mutableListOf<String>()
    private val morningTempArray = mutableListOf<Float>()
    private val afternoonTempArray = mutableListOf<Float>()
    private val notesArray = mutableListOf<String>()




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        DayText = findViewById(R.id.DayText)
        MorningTempText = findViewById(R.id.MorningTempText)
        AfternoonTempText = findViewById(R.id.AfternoonTempText)
        NotesText = findViewById(R.id.NotesText)
        AverageTempText = findViewById(R.id.AverageTempText)
        TempText = findViewById(R.id.TempText)
        Backbtn = findViewById(R.id.Backbtn)
        Savebtn = findViewById(R.id.Savebtn)
        Clearbtn = findViewById(R.id.Clearbtn)
        Nextbtn = findViewById(R.id.Nextbtn)
        Calculatebtn = findViewById(R.id.Calculatebtn)

        Clearbtn.setOnClickListener {
            DayText.setText("")
            MorningTempText.setText("")
            AfternoonTempText.setText("")
            NotesText.setText("")
        }

        Savebtn.setOnClickListener {
            val date = DayText.text.toString()
            val morningTemp = MorningTempText.text.toString()
            val afternoonTemp = AfternoonTempText.text.toString()
            val notes = NotesText.text.toString()

            if (date.isNotEmpty() && morningTemp.isNotEmpty() && afternoonTemp.isNotEmpty() && notes.isNotEmpty()) {
                try {
                    dayArray.add(morningTemp)
                    morningTempArray.add(morningTemp.toFloat())
                    afternoonTempArray.add(afternoonTemp.toFloat())
                    notesArray.add(notes)
                    DayText.text.clear()
                    MorningTempText.text.clear()
                    AfternoonTempText.text.clear()
                    NotesText.text.clear()
                } catch (e: NumberFormatException) {
                    AverageTempText.text = "Please enter a valid temperature"
                }
            } else {
                  AverageTempText.text = "Please enter all fields"
            }
        }

        Nextbtn.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("dateArray", dayArray.toTypedArray())
            intent.putExtra("morningTempArray", morningTempArray.toFloatArray())
            intent.putExtra("afternoonTempArray", afternoonTempArray.toFloatArray())
            intent.putExtra("notesArray", notesArray.toTypedArray())
            startActivity(intent)
        }

        Calculatebtn.setOnClickListener {
            data class DailyTemperature(val date: String, val morningTemp: Float, val afternoonTemp: Float)

            fun calculateAverageWeeklyTemperature(dailyTemperatures: List<DailyTemperature>): Double {
             if (dailyTemperatures.isEmpty()) {
                 return 0.0
             }
             var totalSum = 0.0
             for (temperature in dailyTemperatures) {
                 totalSum += temperature.morningTemp + temperature.afternoonTemp
             }
             return totalSum / (dailyTemperatures.size * 2)
         }
           val weeklyTemperatures = listOf(
               DailyTemperature("Monday", 12.0f, 25.0f),
               DailyTemperature("Tuesday", 15.0f, 29.5f),
               DailyTemperature("Wednesday", 08.0f, 18.0f),
               DailyTemperature("Thursday", 15.0f, 29.0f),
               DailyTemperature("Friday", 13.0f, 30.0f),
               DailyTemperature("Saturday", 10.0f, 18.0f),
               DailyTemperature("Sunday", 10.5f, 16.0f)
           )
            val averageWeeklyTemperature = calculateAverageWeeklyTemperature(weeklyTemperatures)
            println("Average weekly temperature: $averageWeeklyTemperature")

            val TempText = try {
                calculateAverageWeeklyTemperature(weeklyTemperatures)
            } catch (e: Exception) {
                null
            }
            if (averageWeeklyTemperature != null) {
                String.format("%.2f", averageWeeklyTemperature)
            } else {
                "Error To calculate Temperature"
            }
        }

        Backbtn.setOnClickListener {
            finish()
        }

    }
}
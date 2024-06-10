package com.myappname.temperatureapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    private lateinit var resultDate: TextView
    private lateinit var resultMoriningTime: TextView
    private lateinit var resultAfternoonTime: TextView
    private lateinit var resultNotes: TextView
    private lateinit var Backbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        resultDate = findViewById(R.id.resultDate)
        resultMoriningTime = findViewById(R.id.resultMoriningTime)
        resultAfternoonTime = findViewById(R.id.resultAfternoonTime)
        resultNotes = findViewById(R.id.resultNotes)
        Backbtn = findViewById(R.id.Backbtn)

        val dateArray = intent.getStringArrayExtra("dateArray")?.toList() ?: emptyList()
        val morningTempArray = intent.getFloatArrayExtra("morningTempArray")?.toList() ?: emptyList()
        val afternoonTempArray = intent.getFloatArrayExtra("afternoonTempArray")?.toList() ?: emptyList()
        val notesArray = intent.getStringArrayExtra("notesArray")?.toList() ?: emptyList()

        val dated = StringBuilder()
        for ((index, date) in dateArray.withIndex()) {
            dated.append("Date $index: $date \n")
        }
        val morningTemp = StringBuilder()
        for ((index, temp) in morningTempArray.withIndex()) {
            morningTemp.append("Morning Temp $index: $temp \n")
        }
        val afternoonTemp = StringBuilder()
        for ((index, temp) in afternoonTempArray.withIndex()) {
            afternoonTemp.append("Afternoon Temp $index: $temp \n")
        }
        val notes = StringBuilder()
        for ((index, note) in notesArray.withIndex()) {
            notes.append("Notes $index: $note \n")
        }

        resultDate.text = dated.toString()
        resultMoriningTime.text = morningTemp.toString()
        resultAfternoonTime.text = afternoonTemp.toString()
        resultNotes.text = notes.toString()

        Backbtn.setOnClickListener {
            finish()
        }

    }
}
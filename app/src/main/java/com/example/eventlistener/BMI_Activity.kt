package com.example.eventlistener

import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BMI_Activity : AppCompatActivity() {
    lateinit var edBerat: EditText
    lateinit var edTinggi: EditText
    lateinit var btn: Button
    lateinit var spn: Spinner
    lateinit var adp: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edBerat = findViewById(R.id.numberWeight)
        edTinggi = findViewById(R.id.numberHeight)
        btn = findViewById(R.id.btnCalculate)
        spn = findViewById(R.id.ageGroupSpinner)
        adp = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.grup_usia))
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn.adapter = adp

        //set event listener
        btn.setOnClickListener {
            //convert float/double
            val isiBerat = edBerat.text.toString()
            val isiTinggi = edTinggi.text.toString()
            val result: TextView = findViewById(R.id.result)
            val hitungBMI = isiBerat.toFloat()/((isiTinggi.toFloat()/100))
            val hitungBMIAnak = isiBerat.toFloat()/((isiTinggi.toFloat()*isiTinggi.toFloat())/100)
            result.text = hitungBMI.toString()
            result.text = hitungBMIAnak.toString()
         // Menentukan kategori BMI berdasarkan umur
            val grupUsia = spn.selectedItem.toString()
            if (grupUsia == "Dewasa") {
                resultBMIDewasa(hitungBMI)
            } else {
                resultBMIAnak(hitungBMIAnak)
            }
        }
    }
        private fun resultBMIDewasa(bmi: Float){
            val result: TextView = findViewById(R.id.result)
            result.text = bmi.toString()
            when {
                bmi < 18.5 -> {
                    Toast.makeText(this, "Underweight", Toast.LENGTH_LONG).show()
                }
                bmi in 18.5 .. 25.0 -> {
                    Toast.makeText(this, "Healthy", Toast.LENGTH_LONG).show()
                }
                bmi in 25.0 .. 29.9 -> {
                    Toast.makeText(this, "Overweight", Toast.LENGTH_LONG).show()
                }
                bmi >= 30.0 ->{
                    Toast.makeText(this, "Obesity", Toast.LENGTH_LONG).show()
                }
            }
        }
        private fun resultBMIAnak(bmi: Float){
            val result: TextView = findViewById(R.id.result)
            result.text = bmi.toString()
            when {
                bmi < 18.5 -> {
                    Toast.makeText(this, "Underweight", Toast.LENGTH_LONG).show()
                }
                bmi in 18.5 .. 25.0 -> {
                    Toast.makeText(this, "Healthy", Toast.LENGTH_LONG).show()
                }
                bmi in 25.0 .. 29.9 -> {
                    Toast.makeText(this, "Overweight", Toast.LENGTH_LONG).show()
                }
                bmi >= 30.0 ->{
                    Toast.makeText(this, "Obesity", Toast.LENGTH_LONG).show()
                }
            }
        }
}
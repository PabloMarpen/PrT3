package com.example.prt3

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import android.widget.RadioButton
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.CheckBox

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var boton: Button
    private lateinit var resultado: TextView
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var email: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var hombre: RadioButton
    private lateinit var mujer: RadioButton
    private lateinit var otro: RadioButton
    private lateinit var spinner: Spinner
    private lateinit var lectura : CheckBox
    private lateinit var deporte : CheckBox
    private lateinit var musica : CheckBox
    private lateinit var arte : CheckBox
    private val checkBoxHobbieString = arrayOf("Lectura", "Deporte", "Musica", "Arte")
    private val checkBoxHobbieBoolean = arrayOf(false, false, false, false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

       spinner = findViewById<Spinner>(R.id.spinnerPais)

        ArrayAdapter.createFromResource(this, R.array.paises, android.R.layout.simple_spinner_item).also { adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

       listener("")
    }

    private fun listener(pais: String) {

         boton = findViewById<Button>(R.id.button)
         resultado = findViewById<TextView>(R.id.textViewResultado)
         nombre = findViewById<EditText>(R.id.editTextNombre)
         apellido = findViewById<EditText>(R.id.editTextApellido)
         email = findViewById<EditText>(R.id.editTextMail)

         radioGroup = findViewById<RadioGroup>(R.id.radioGroup2)

         hombre = findViewById<RadioButton>(R.id.radioButtonMasculino)
         mujer = findViewById<RadioButton>(R.id.radioButtonFemenino)
         otro = findViewById<RadioButton>(R.id.radioButtonOtro)
         lectura = findViewById<CheckBox>(R.id.checkBoxLectura)
         deporte = findViewById<CheckBox>(R.id.checkBoxDeporte)
         musica = findViewById<CheckBox>(R.id.checkBoxMusica)
         arte = findViewById<CheckBox>(R.id.checkBoxArte)

        lectura.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxHobbieBoolean[0] = true
            }else{
                checkBoxHobbieBoolean[0] = false
            }

        }
        deporte.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxHobbieBoolean[1] = true
            }else{
                checkBoxHobbieBoolean[1] = false
            }
        }
        musica.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxHobbieBoolean[2] = true
            }else{
                checkBoxHobbieBoolean[2] = false
            }
        }
        arte.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBoxHobbieBoolean[3] = true
            }else{
                checkBoxHobbieBoolean[3] = false
            }
        }

         boton.setOnClickListener {
            var hobbies = ""
            val nombreS = nombre.text
            val apellidoS = apellido.text
            val emailS = email.text

            var genero = when (radioGroup.checkedRadioButtonId) {
                hombre.id -> "Hombre"
                mujer.id -> "Mujer"
                otro.id -> "Otro"
                else -> ""
            }
             if (pais == "" || genero == "" || emailS.toString() == "" || nombreS.toString() == ""|| apellidoS.toString() == ""){
                 resultado.text = "Porfavor use todos los campos"
             }else{
             for (i in checkBoxHobbieBoolean.indices){
                 if (checkBoxHobbieBoolean[i]){
                     hobbies += checkBoxHobbieString[i]
                     hobbies += ", "
                 }


            }
                 resultado.text = "Nombre: $nombreS\nApellido: $apellidoS\nEmail: $emailS\nGenero: $genero\nPais: $pais\nHobbies: $hobbies"
             }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        listener(spinner.selectedItem.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
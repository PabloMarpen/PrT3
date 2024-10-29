package com.example.prt3

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly


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
    private lateinit var barra : SeekBar
    private lateinit var textViewNivel : TextView
    private lateinit var suscripcion : Switch
    private var suscripcionBoolean = false
    private var suscripcionString = "No"
    private val checkBoxHobbieString = arrayOf("Lectura", "Deporte", "Musica", "Arte")
    private val checkBoxHobbieBoolean = arrayOf(false, false, false, false)
    private var nivelSatifaccion = 0

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

    @SuppressLint("NewApi")
    private fun listener(pais: String) {

        val duration = Toast.LENGTH_SHORT
        boton = findViewById<Button>(R.id.button)
        resultado = findViewById<TextView>(R.id.textViewResultado)
        nombre = findViewById<EditText>(R.id.editTextNombre)
        nombre.requestFocus()
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
        barra = findViewById<SeekBar>(R.id.seekBarNivel)
        textViewNivel = findViewById<TextView>(R.id.textViewNivel)
        suscripcion = findViewById<Switch>(R.id.switch1)
        barra.min = 0
        barra.max = 10


        barra.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textViewNivel.text = "Nivel de satisfacción: $progress"
                nivelSatifaccion = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementación en este caso
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No se necesita implementación en este caso
            }
        })

        suscripcion.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked) {
                suscripcionBoolean = true
                suscripcionString = "Si"
            }else{
                suscripcionBoolean = false
                suscripcionString = "No"
            }
        }

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
            var hobbies  = ""
            val nombreS = nombre.text
            val apellidoS = apellido.text
            val emailS  = email.text



            var genero = when (radioGroup.checkedRadioButtonId) {
                hombre.id -> "Hombre"
                mujer.id -> "Mujer"
                otro.id -> "Otro"
                else -> ""
            }
             if (pais == "" || genero == "" || emailS.toString() == "" || nombreS.toString() == ""|| apellidoS.toString() == ""){
                 Toast.makeText(this, "Usa todos los campos ⚠⚠", duration).show() // in Activity

             }else if( nombreS.isDigitsOnly() || apellidoS.isDigitsOnly()){
                 Toast.makeText(this, "No metas numeros 😡😡", duration).show() // in Activity

             }else if (!isValidEmail(emailS)){

               Toast.makeText(this, "Por favor, introduzca bien su email 😡😡",duration ).show()

             }else{

                 for (i in checkBoxHobbieBoolean.indices){
                     if (checkBoxHobbieBoolean[i]){
                         hobbies += checkBoxHobbieString[i]
                         hobbies += ", "
                     }
                }
                 if (hobbies == ""){
                     hobbies = "No hay hobbies"
                 }

                 resultado.text = "Nombre: $nombreS\nApellido: $apellidoS\nEmail: $emailS\nGenero: $genero\nPais: $pais\nHobbies: $hobbies\nSatisfaccion: $nivelSatifaccion\nSuscripción: $suscripcionString"
             }
        }
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Resultado", resultado.text.toString());
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        resultado.text = savedInstanceState.getString("Resultado")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        listener(spinner.selectedItem.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
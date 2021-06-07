package mx.tecnm.nlaredo.proyectou2_17100228

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
//Karla González Torres -17100228
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Agregar Imagen
        btn_imagen.setOnClickListener {

            val imagen = Intent().apply {
                action = Intent.ACTION_VIEW
                type = "image/*"
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }; // nuevo Intent

            if (imagen.resolveActivity(packageManager) != null){
                startActivity(imagen)
            }
        }

        //Insertar nueva mascota
        btn_insertar.setOnClickListener {

            AlertDialog.Builder(this).apply {
                setTitle("Confirmación")
                setMessage("¿Seguro que deseas guardar?")
                setPositiveButton("Aceptar"){ _: DialogInterface, _: Int ->
                    InsertarMascota()
                    LimpiarControles()
                    Toast.makeText(applicationContext, "Registro Guardado", Toast.LENGTH_LONG).show()
                }
                setNegativeButton("Cancelar", null)
            }.show()
        }

        //Spinneerrsss
        val spinner_Tamaño: Spinner = findViewById(R.id.spin_tamaño)
        val spinner_raza: Spinner = findViewById(R.id.spin_raza)

        ArrayAdapter.createFromResource(
            this,
            R.array.array_tamaño,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_Tamaño.adapter = adapter
            spinner_Tamaño.isSelected = false
            spinner_Tamaño.setSelection(0, true)
            spinner_Tamaño.onItemSelectedListener = this
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.array_raza,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner_raza.adapter = adapter
            spinner_raza.isSelected = false
            spinner_raza.setSelection(0, true)
            spinner_raza.onItemSelectedListener = this
        }

        etxt_nacimiento.setOnClickListener {
            MostrarDatePickerDialog()
        }

    }//Fin de onCreate()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menu_agregar -> {
                LimpiarControles()
                true
            }
            R.id.menu_lista_pacientes -> {

                //Intención Explicita
                val intent = Intent(this, BasicActivity2_17100228::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun LimpiarControles(){
        etxt_nombre.setText("")
        etxt_edad.setText("")
        etxt_otro.setText("")
        etxt_peso.setText("")
        etxt_nacimiento.setText("")
        etxt_nombre.requestFocus()
    }

    fun InsertarMascota(){
        val nombre = etxt_nombre.text.toString()
        val edad = etxt_edad.text.toString().toInt()
        val nacimiento = etxt_nacimiento.text.toString()
        val peso = etxt_peso.text.toString().toDouble()
        //val raza = spin_raza.getSelectedItem().toString()
        val raza = if (spin_raza.selectedItem == "Otro") etxt_otro.text.toString() else spin_raza.getSelectedItem().toString()
        val tamaño = spin_tamaño.getSelectedItem().toString()
        val sexo = if(rb_hembra.isChecked) 0 else 1
        val vacunado = if(checkBox.isChecked) 1 else 0

        Datos.mascotas.add(Mascota(nombre,edad,nacimiento,peso,tamaño,raza,sexo,vacunado))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinner: Spinner = findViewById(R.id.spin_raza)
        if (spinner.selectedItem == "Otro"){
            etxt_otro.isEnabled = true
            Toast.makeText(this, "Selección: ${spinner.selectedItem}", Toast.LENGTH_SHORT).show()
        }
        else{
            etxt_otro.isEnabled = false
            val image = when(spinner.selectedItem){
                "Pastor Alemán" ->{R.drawable.pastor_aleman_cachorro}
                "Labrador" ->{R.drawable.labrador_cachorro}
                "Poodle" ->{R.drawable.poodle}
                "Chihuahua" ->{R.drawable.chihuahua_pequeno}
                "Husky" ->{R.drawable.husky_pequeno}
                "Schnauzer" ->{R.drawable.schnauzer}
                "Salchicha" ->{R.drawable.salchicha}
                "Dalmata" ->{R.drawable.dalmata_mediano}
                "Otro" ->{R.drawable.memes}
                else -> R.drawable.memes
            }
            img_captura_imagen.setImageResource(image)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun MostrarDatePickerDialog(){
        val datepicker = DatePickerFragment{day, month, year -> onDateSelected(day, month, year)}
        datepicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day:Int, month:Int, year:Int){
        etxt_nacimiento.setText("$day/${month+1}/$year")
    }
}
package mx.tecnm.nlaredo.proyectou2_17100228

import android.content.DialogInterface
import android.content.Intent
import android.icu.text.IDNA
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
//Karla González Torres -17100228
class BasicActivity2_17100228 : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {

    lateinit var recycler: RecyclerView
    lateinit var adaptador: Adaptador_mascotas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        setSupportActionBar(findViewById(R.id.toolbar))

        AddMascotas()
        recycler = findViewById(R.id.recycler_id)
        recycler.layoutManager = LinearLayoutManager(this)
        adaptador = Adaptador_mascotas{ m ->
            //Aquí puedes ejecutar acciones cuando se le da clic a alguna mascota de la lista
            //El objeto "m" te puede dar las propiedades de esa mascota clickeada, por ejemplo m.nombre
            //showPopup(recycler)
            //Toast.makeText(applicationContext,"Mascota clickeada", Toast.LENGTH_LONG).show()
            MostrarInformacion(m)
        }

        recycler.adapter = adaptador

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true

        }
    }


    override fun onStart() {
        super.onStart()
        //adaptador.notifyDataSetChanged()
    }

    fun showPopup(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        popup.setOnMenuItemClickListener(this)
        inflater.inflate(R.menu.popup_menu_ba, popup.menu)
        popup.show()

    }
    fun AddMascotas(){
        Datos.mascotas.add(Mascota("Musy",3,"2017-05-23",5.5,"Pequeño","Otro",0,1))
        Datos.mascotas.add(Mascota("Cowi",8,"20010-10-30",8.4,"Mediano","Husky",1,0))
        Datos.mascotas.add(Mascota("Sowe",2,"2019-02-15",10.3,"Grande","Dalmata",0,1))
    }

    fun MostrarInformacion(m:Mascota){
        //showPopup(view)

        val info = "Edad: ${m.edad} \nFecha Nacimiento: ${m.nacimiento}" +
                "\nTamaño: ${m.tamaño} \nSexo: ${m.Sexo} \nVacunado: ${m.Vacuna}"
        AlertDialog.Builder(this).apply {
            setTitle("Confirmación")
            setMessage("$info")
            setNegativeButton("OK", null)
            setPositiveButton("Enviar Info."){ _: DialogInterface, _: Int ->
                EnviarInformacion(info);
                Snackbar.make(recycler, "Enviando Información...", Snackbar.LENGTH_LONG)
                    .setAction("OK", null).show()
                //
            }

        }.show()
    }
    fun EnviarInformacion(mensaje: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mensaje)
            type = "text/plain"
        }
        // Verify that the intent will resolve to an activity
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        }
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.id_detalles -> {
                adaptador = Adaptador_mascotas { m ->
                    MostrarInformacion(m)
                }
                //Toast.makeText(this, "Opción 1", Toast.LENGTH_LONG).show()
                true
            }
            else -> false
        }
    }

}

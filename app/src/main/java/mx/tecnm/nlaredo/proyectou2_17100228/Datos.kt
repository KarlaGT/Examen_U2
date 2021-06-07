package mx.tecnm.nlaredo.proyectou2_17100228

import android.widget.Switch
import java.util.*
import kotlin.collections.ArrayList

object Datos {
    val mascotas: ArrayList<Mascota> = arrayListOf()
}

data class Mascota (val nombre: String, val edad: Int, val nacimiento: String, val peso: Double, val tamaño: String, val raza: String, val sexo: Int, val vacunado: Int){
    val imagen = when(raza){
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
    val Sexo = if (sexo == 1) "Macho" else "Hembra"
    val Vacuna = if (vacunado == 1) "Sí" else "No"

}
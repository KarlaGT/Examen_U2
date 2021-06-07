package mx.tecnm.nlaredo.proyectou2_17100228

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class Adaptador_mascotas(private val listener: (Mascota) -> Unit): RecyclerView.Adapter<Adaptador_mascotas.ViewHolder> () {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val nombre: TextView = view.findViewById(R.id.txt_nombre)
        val peso: TextView = view.findViewById(R.id.txt_peso)
        val raza: TextView = view.findViewById(R.id.txt_raza)
        val imagen: ImageView = view.findViewById(R.id.img_mascota)
        /////////
        val edad: TextView = view.findViewById(R.id.txt_edad)
        val nacimiento:TextView = view.findViewById(R.id.txt_nacimiento)
        val tamaño:TextView = view.findViewById(R.id.txt_tamano)
        val sexo:TextView = view.findViewById(R.id.txt_sexo)
        val vacunado:TextView = view.findViewById(R.id.txt_vacunado)

        //val edad: EditText = view.findViewById(R.id.txtEdad)
        //val nacimiento: EditText = view.findViewById(R.id.etxt_nacimiento)
        //val tamaño: Spinner = view.findViewById(R.id.spin_tamaño)
        //val sexo: Int,
        //val vacunado: Int
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lista_mascotas,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Datos.mascotas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text = "Nombre: ${Datos.mascotas.get(position).nombre}"
        holder.peso.text = "Peso: ${Datos.mascotas.get(position).peso}"
        holder.raza.text = "Raza: ${Datos.mascotas.get(position).raza}"
        holder.imagen.setImageResource(Datos.mascotas.get(position).imagen)
        ///////
        holder.edad.text = "Edad: ${Datos.mascotas.get(position).edad}"
        holder.nacimiento.text = "Fecha Nacimiento: ${Datos.mascotas.get(position).nacimiento}"
        holder.tamaño.text = "Tamaño: ${Datos.mascotas.get(position).tamaño}"
        holder.sexo.text = "Sexo: ${Datos.mascotas.get(position).sexo}"
        holder.vacunado.text = "Vacunado: ${Datos.mascotas.get(position).vacunado}"

        holder.itemView.setOnClickListener {
            listener(Datos.mascotas.get(position))
        }
    }
}
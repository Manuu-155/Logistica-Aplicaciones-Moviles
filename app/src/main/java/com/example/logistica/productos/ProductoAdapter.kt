package com.example.logistica.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logistica.models.Producto
import com.example.logistica.R


class ProductoAdapter(private val lista: ArrayList<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvMarca = view.findViewById<TextView>(R.id.tvMarca)
        val tvStock = view.findViewById<TextView>(R.id.tvStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = lista[position]
        holder.tvNombre.text = prod.nombre
        holder.tvMarca.text = prod.marca
        holder.tvStock.text = "Stock: ${prod.stock}"
    }

    override fun getItemCount(): Int = lista.size
}

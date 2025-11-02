package com.example.logistica.productos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logistica.MainActivity
import com.example.logistica.models.Producto
import com.google.firebase.database.DatabaseReference
import com.example.logistica.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListadoProductosActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var lista: ArrayList<Producto>
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_productos)
        val btnVolver = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnVolver)
        val btnAgregar = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.btnAgregarProducto)
        recycler = findViewById(R.id.recyclerProductos)
        recycler.layoutManager = LinearLayoutManager(this)
        lista = ArrayList()

        db = FirebaseDatabase.getInstance().getReference("productos")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lista.clear()
                for (data in snapshot.children) {
                    val prod = data.getValue(Producto::class.java)
                    if (prod != null) lista.add(prod)
                }
                recycler.adapter = ProductoAdapter(lista)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarProductoActivity::class.java)
            startActivity(intent)
        }
        btnVolver.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

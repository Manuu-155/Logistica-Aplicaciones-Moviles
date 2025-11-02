package com.example.logistica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import android.content.Intent
import com.example.logistica.productos.ListadoProductosActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val tvUsuario = findViewById<TextView>(R.id.tvUsuario)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val btnProductos = findViewById<LinearLayout>(R.id.btnProductos)

        val uid = intent.getStringExtra("USER_UID") ?: ""

        if (uid.isEmpty()) {
            tvUsuario.text = "Usuario"
        } else {
            val database = FirebaseDatabase.getInstance().getReference("usuarios").child(uid)
            database.get().addOnSuccessListener { snapshot ->
                val nombre = snapshot.child("nombre").getValue(String::class.java)
                tvUsuario.text = nombre ?: "Usuario"
            }.addOnFailureListener {
                tvUsuario.text = "Usuario"
            }
        }

        btnProductos.setOnClickListener {
            startActivity(Intent(this, ListadoProductosActivity::class.java))
        }

        btnCerrarSesion.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

package com.example.logistica.productos
import android.content.Intent
import com.example.logistica.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logistica.models.Producto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase

class AgregarProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)

        val btnVolverListado = findViewById<FloatingActionButton>(R.id.btnVolverListado)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etMarca = findViewById<EditText>(R.id.etMarca)
        val etStock = findViewById<EditText>(R.id.etStock)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val marca = etMarca.text.toString()
            val stock = etStock.text.toString().toIntOrNull()
            val descripcion = etDescripcion.text.toString()

            if (nombre.isEmpty() || marca.isEmpty() || stock == null) {
                Toast.makeText(this, "Completa los datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = FirebaseDatabase.getInstance().getReference("productos")
            val id = db.push().key!!

            val producto = Producto(id, nombre, marca, stock, descripcion)

            db.child(id).setValue(producto).addOnSuccessListener {
                Toast.makeText(this, "Producto registrado", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolverListado.setOnClickListener {
            val intent = Intent(this, ListadoProductosActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

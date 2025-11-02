package com.example.logistica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        // Campos
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        // Flecha para login
        val btnLogin = findViewById<ImageView>(R.id.imageView5)

        // Texto para ir a registro
        val registrar = findViewById<TextView>(R.id.textView2)
        registrar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        // Acción login
        btnLogin.setOnClickListener {
            val correo = etCorreo.text.toString()
            val password = etPassword.text.toString()

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                iniciarSesion(correo, password)
            }
        }
    }

    private fun iniciarSesion(correo: String, password: String) {
        auth.signInWithEmailAndPassword(correo, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
                    // Redirigir a Activity principal
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error al iniciar sesión: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}

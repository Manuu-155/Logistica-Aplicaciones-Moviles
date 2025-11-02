package com.example.logistica.models

data class Producto(
    val id: String = "",
    val nombre: String = "",
    val marca: String = "",
    val stock: Int = 0,
    val descripcion: String = ""
)

package com.example.serializableparcelable

import java.io.Serializable

data class Person(
    val name: String, val surname: String,
    val address: String, val phone: String
) : Serializable {

    override fun toString() = "$surname $name"
}
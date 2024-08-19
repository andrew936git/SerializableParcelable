package com.example.serializableparcelable

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.Serializable


class SecondActivity : AppCompatActivity() {

    private var person: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val toolbarTB: Toolbar = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)
        title = "Персональные данные"

        val textViewTV: TextView = findViewById(R.id.textViewTV)

        person = intent.extras?.customGetSerializable(Person::class.java.simpleName) as Person?

        textViewTV.text = """${person?.surname} ${person?.name}: 
            |адрес ${person?.address}, 
            |тел. ${person?.phone}""".trimMargin()
    }

    private inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializable(key, T::class.java)
        } else {
            getSerializable(key) as? T
        }
    }
}
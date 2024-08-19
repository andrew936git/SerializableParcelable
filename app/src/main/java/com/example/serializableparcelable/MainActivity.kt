package com.example.serializableparcelable

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var addressET: EditText
    private lateinit var phoneET: EditText

    private lateinit var listViewLV: ListView

    private val list = mutableListOf<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        addressET = findViewById(R.id.addressET)
        phoneET = findViewById(R.id.phoneET)

        listViewLV = findViewById(R.id.listViewLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listViewLV.adapter = adapter

        val saveButtonBT: Button = findViewById(R.id.saveButtonBT)
        saveButtonBT.setOnClickListener {
            list.add(
                Person(
                    nameET.text.toString(),
                    surnameET.text.toString(),
                    addressET.text.toString(),
                    phoneET.text.toString()
                )
            )

            adapter.notifyDataSetChanged()
            nameET.text.clear()
            surnameET.text.clear()
            addressET.text.clear()
            phoneET.text.clear()
        }

        listViewLV.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val person = adapter.getItem(position)
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra(Person::class.java.simpleName, person)
                startActivity(intent)

            }
    }


}
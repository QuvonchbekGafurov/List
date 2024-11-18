package com.example.codingtech1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var itemRepository: ItemRepository
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemRepository = ItemRepository()
        itemAdapter = ItemAdapter(itemRepository.getItems(), ::onEditItem, ::onDeleteItem)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter

        val etName: EditText = findViewById(R.id.etName)
        val etAge: EditText = findViewById(R.id.etAge)
        val btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty() && age > 0) {
                itemRepository.addItem(name, age)
                itemAdapter.notifyDataSetChanged()
                etName.text.clear()
                etAge.text.clear()
            }
        }
    }

    private fun onEditItem(item: Item) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit, null)

        val etName: EditText = dialogView.findViewById(R.id.etName)
        val etAge: EditText = dialogView.findViewById(R.id.etAge)

        etName.setText(item.name)
        etAge.setText(item.age.toString())

        builder.setView(dialogView)
            .setTitle("Edit Item")
            .setPositiveButton("Save") { _, _ ->
                val newName = etName.text.toString()
                val newAge = etAge.text.toString().toIntOrNull() ?: item.age

                itemRepository.updateItem(item.copy(name = newName, age = newAge))

                itemAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel", null) // Cancel tugmasi
            .create()
            .show()
    }

    private fun onDeleteItem(item: Item) {
        itemRepository.deleteItem(item)
        itemAdapter.notifyDataSetChanged()
    }
}

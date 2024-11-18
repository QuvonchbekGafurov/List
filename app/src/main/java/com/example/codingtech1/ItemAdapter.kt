package com.example.codingtech1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class ItemAdapter(
    private val itemList: List<Item>,
    private val onEdit: (Item) -> Unit,
    private val onDelete: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.tvId) // idni ko'rsatish uchun TextView
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvAge: TextView = view.findViewById(R.id.tvAge)
        val btnEdit: ImageButton = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvId.text = "id " + item.id.toString()  // id ni ko'rsatish
        holder.tvName.text = "Name " +item.name
        holder.tvAge.text = "Age "+item.age.toString()

        holder.btnEdit.setOnClickListener { onEdit(item) }
        holder.btnDelete.setOnClickListener { onDelete(item) }
    }

    override fun getItemCount(): Int = itemList.size
}

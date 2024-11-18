package com.example.codingtech1


class ItemRepository {

    private val itemList = mutableListOf<Item>()
    private var idCounter = 1

    fun addItem(name: String, age: Int) {
        val newItem = Item(id = idCounter, name = name, age = age)
        itemList.add(newItem)
        idCounter++
    }

    fun getItems(): List<Item> {
        return itemList
    }

    fun deleteItem(item: Item) {
        itemList.remove(item)
    }

    fun updateItem(item: Item) {
        val index = itemList.indexOfFirst { it.id == item.id }
        if (index != -1) {
            itemList[index] = item
        }
    }
}

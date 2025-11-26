package com.example.todoapp

import org.json.JSONArray
import java.io.File

class FileStorage(val file: File) {
    private val _itemList = mutableListOf<ToDoItem>()

    val itemList: List<ToDoItem>
            get(){
                return _itemList
            }

    fun addItem(item: ToDoItem): Boolean {
        return _itemList.add(item)
    }

    fun removeItem(uid: String): Boolean {
        return _itemList.removeIf { it.uid == uid }
    }

    fun saveItems(){
        val jsItems = JSONArray()
        _itemList.forEach { item ->
            jsItems.put(item.json)
        }
        file.writeText(jsItems.toString())
    }

    fun loadFromFile(){
        val jsItems = JSONArray(file.readText())
        _itemList.clear()
        for (i in 0 until jsItems.length()) {
            val jsonItem = jsItems.getJSONObject(i)
            val newItem = parse(jsonItem)
            _itemList.add(newItem)
        }
    }
}
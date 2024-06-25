package com.sample.to_do_list.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.to_do_list.Model.ToDo

class ToDoViewModel : ViewModel() {
    private val _todos = MutableLiveData<MutableList<ToDo>>()
    val todos: LiveData<MutableList<ToDo>> get() = _todos

    init {
        _todos.value = mutableListOf(
            ToDo(1, "Buy milk", true),
            ToDo(2, "Buy milk", true),
            ToDo(3, "Buy milk", true),
        )
    }

    fun addTask(id: Int, task: String) {
        val currentList = _todos.value
        currentList?.add(ToDo(id, task))
        _todos.value = currentList!!
    }

    fun swapItems(fromPosition: Int, toPosition: Int){
        val currentList = _todos.value
        currentList?.let {
            val fromItem = it[fromPosition]
            it.removeAt(fromPosition)
            it.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, fromItem)
            _todos.value = currentList
        }
    }
}
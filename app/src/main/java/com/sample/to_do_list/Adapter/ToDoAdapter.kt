package com.sample.to_do_list.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.to_do_list.Model.ToDo
import com.sample.to_do_list.R

class ToDoAdapter(private val todoList: MutableList<ToDo>) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTask: TextView = itemView.findViewById(R.id.tvTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val todo = todoList[position]
        holder.tvTask.text = todo.task
    }

    override fun getItemCount(): Int = todoList.size

    fun updateList(newList: MutableList<ToDo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        val fromItem = todoList[fromPosition]
        todoList.removeAt(fromPosition)
        todoList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, fromItem)
        notifyItemMoved(fromPosition, toPosition)
    }
}

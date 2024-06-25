package com.sample.to_do_list

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sample.to_do_list.Adapter.ToDoAdapter
import com.sample.to_do_list.Fragment.FullScreenDialogFragment
import com.sample.to_do_list.Model.ToDo
import com.sample.to_do_list.ViewModel.ToDoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: ToDoAdapter
    private lateinit var todoViewModel: ToDoViewModel
    private val todoList = mutableListOf<ToDo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        todoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        todoAdapter = ToDoAdapter(mutableListOf())

        todoAdapter = ToDoAdapter(todoList)
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

/////////////////////////////////////////////////////////////////////////



        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlags, 0)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                todoViewModel.swapItems(fromPosition, toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // No swiping action required
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)

/////////////////////////////////////////////////////////////////////////

        fab.setOnClickListener {
            // Add a new task
            showAddTaskDialog()
        }
    }
    private fun showAddTaskDialog() {
        val dialog = FullScreenDialogFragment.newInstance()
        dialog.onSaveListener = { task ->
            val newTask = ToDo(todoList.size + 1, task)
            todoAdapter.addTask(newTask)
        }
        dialog.show(supportFragmentManager, "FullScreenDialogFragment")
    }

}

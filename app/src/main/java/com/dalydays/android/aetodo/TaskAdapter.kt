package com.dalydays.android.aetodo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView

class TaskAdapter(var tasks: MutableList<Task> = ArrayList()) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.todo_item_row, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindTask(tasks[position])
    }

    fun addTask(task: Task) {
        tasks.add(task)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val descriptionTextView = view.findViewById(R.id.task_description) as TextView
        private val completedCheckBox = view.findViewById(R.id.task_completed) as CheckBox

        fun bindTask(task: Task) {
            descriptionTextView.text = task.description
            completedCheckBox.isChecked = task.completed

            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tasks[adapterPosition].completed = isChecked
            }
        }
    }
}
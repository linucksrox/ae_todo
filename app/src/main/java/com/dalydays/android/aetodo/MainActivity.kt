package com.dalydays.android.aetodo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var adapter = TaskAdapter()

    companion object {
        private val ADD_TASK_REQUEST = 0
        val DESCRIPTION_TEXT = "description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { _ ->
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST)
        }

        task_list.layoutManager = LinearLayoutManager(this)
        task_list.adapter = TaskAdapter(getSimpleTasks())
    }

    override fun onResume() {
        super.onResume()

        val tasks = Storage.readData(this)

        if (tasks != null && (adapter.tasks.isEmpty())) {
            adapter.tasks = tasks
        }
    }

    override fun onPause() {
        super.onPause()

        Storage.writeData(this, adapter.tasks)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            val task = Task(data?.getStringExtra(DESCRIPTION_TEXT).orEmpty())
            adapter.addTask(task)
        }
    }

    private fun getSimpleTasks(): MutableList<Task> {
        val task1 = Task("task1")
        val task2 = Task("task2", true)

        return mutableListOf(task1, task2)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

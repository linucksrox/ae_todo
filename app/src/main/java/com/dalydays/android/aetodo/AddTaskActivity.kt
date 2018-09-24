package com.dalydays.android.aetodo

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        submit?.setOnClickListener {
            if (task_description?.text?.toString().isNullOrBlank()) {
               task_description?.error = "Please enter a description"
            } else {
                val data = Intent()
                data.putExtra(MainActivity.DESCRIPTION_TEXT, task_description?.text.toString())
                setResult(Activity.RESULT_OK, data)

                finish()
            }
        }
    }
}

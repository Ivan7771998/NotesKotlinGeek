package com.dev777popov.noteskotlingeek.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.ui.adapters.NotesRVAdapter
import com.dev777popov.noteskotlingeek.ui.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        adapter = NotesRVAdapter {
            startActivity(NoteActivity.newIntent(this, it))
        }
        rv_notes.adapter = adapter

        viewModel.viewState().observe(this, Observer { state ->
            state?.let {
                adapter.notes = state.notes
            }
        })

        fab.setOnClickListener {
            startActivity(NoteActivity.newIntent(this))
        }
    }
}
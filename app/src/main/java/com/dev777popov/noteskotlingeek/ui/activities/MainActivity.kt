package com.dev777popov.noteskotlingeek.ui.activities


import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.adapters.NotesRVAdapter
import com.dev777popov.noteskotlingeek.ui.viewmodels.BaseViewModel
import com.dev777popov.noteskotlingeek.ui.viewmodels.MainViewModel
import com.dev777popov.noteskotlingeek.ui.viewstates.MainViewState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: BaseViewModel<List<Note>?, MainViewState> by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var adapter: NotesRVAdapter

    override val layoutRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NotesRVAdapter {
            startActivity(NoteActivity.newIntent(this, it))
        }
        rv_notes.adapter = adapter

        fab.setOnClickListener {
            startActivity(NoteActivity.newIntent(this))
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }
}
package com.dev777popov.noteskotlingeek.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.viewmodels.NoteViewModel
import com.dev777popov.noteskotlingeek.utils.getColor
import kotlinx.android.synthetic.main.activity_note.*
import java.util.*

class NoteActivity : AppCompatActivity() {

    private var note: Note? = null
    lateinit var viewModel: NoteViewModel

    private val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            isEmptyData()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)
        isEmptyData()
        note = intent.getParcelableExtra(NOTE_DATA)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
    }

    private fun initView() {
        et_title.removeTextChangedListener(textChangeListener)
        et_body.removeTextChangedListener(textChangeListener)
        btn_save.setOnClickListener {
            saveNote()
            finish()
        }

        note?.let { note ->
            et_title.setText(note.title)
            et_body.setText(note.text)
            toolbar.setBackgroundColor(getColor(note.color))
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }

    private fun isEmptyData() {
        btn_save.visibility =
            if (!et_title.text.isNullOrEmpty() and (!et_body.text.isNullOrEmpty()))
                View.VISIBLE else View.INVISIBLE
    }

    private fun saveNote() {
        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            color = Note.Color.values().random(),
                        lastChanged = Date ()
            ) ?: Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString())

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    companion object {

        const val NOTE_DATA = "note"

        fun newIntent(context: Context, note: Note? = null): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(NOTE_DATA, note)
            return intent
        }
    }
}
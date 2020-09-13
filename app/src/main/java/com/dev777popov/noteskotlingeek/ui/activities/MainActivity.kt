package com.dev777popov.noteskotlingeek.ui.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.adapters.NotesRVAdapter
import com.dev777popov.noteskotlingeek.ui.dialogs.LogOutDialog
import com.dev777popov.noteskotlingeek.ui.viewmodels.BaseViewModel
import com.dev777popov.noteskotlingeek.ui.viewmodels.MainViewModel
import com.dev777popov.noteskotlingeek.ui.viewstates.MainViewState
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuInflater(this).inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> showLogoutDialog().let { true }
        else -> false
    }

    private fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogOutDialog.TAG) ?: LogOutDialog.createInstance {
            onLogout()
        }.show(supportFragmentManager, LogOutDialog.TAG)
    }

    private fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }
}
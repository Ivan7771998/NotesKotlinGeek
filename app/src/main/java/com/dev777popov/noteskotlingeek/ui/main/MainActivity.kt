package com.dev777popov.noteskotlingeek.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.dev777popov.noteskotlingeek.R
import com.dev777popov.noteskotlingeek.data.entity.Note
import com.dev777popov.noteskotlingeek.ui.note.NoteActivity
import com.dev777popov.noteskotlingeek.ui.splash.SplashActivity
import com.dev777popov.noteskotlingeek.ui.base.BaseActivity
import com.dev777popov.noteskotlingeek.ui.dialogs.LogOutDialog
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: NotesRVAdapter

    override val layoutRes = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NotesRVAdapter {
            startActivity(
                NoteActivity.newIntent(
                    this,
                    it
                )
            )
        }
        rv_notes.adapter = adapter

        fab.setOnClickListener {
            startActivity(
                NoteActivity.newIntent(
                    this
                )
            )
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
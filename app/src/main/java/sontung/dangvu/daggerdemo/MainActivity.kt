package sontung.dangvu.daggerdemo

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.view.adapter.PlayerAdapter
import sontung.dangvu.daggerdemo.viewmodel.PlayerViewModel

private const val TAG = "$APP_TAG MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingButton: FloatingActionButton

    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playerAdapter = PlayerAdapter(ArrayList())

        playerViewModel.getPlayers.observe(this, Observer<List<Player>> {
            Log.d(TAG, "List player changed with size = [${it.size}]")
            playerAdapter.updateList(it)
        })

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = playerAdapter
        floatingButton = findViewById(R.id.floating)
        floatingButton.setOnClickListener {
            addPlayer()
        }
        Log.d(TAG, "onCreate finished()")

    }

    @SuppressLint("InflateParams")
    private fun addPlayer() {
        Log.d(TAG, "add player called")
        val dialog = AlertDialog.Builder(this)
        val customDialogView = LayoutInflater.from(this).inflate(R.layout.add_user_dialog, null)
        val userNameEditText = customDialogView.findViewById<EditText>(R.id.createUserName)
        dialog.setView(customDialogView)
        dialog.setTitle("Add user")
        dialog.setPositiveButton("Add") { _, _ ->
            Log.d(TAG, "Add clicked")
            val userName = userNameEditText.text.toString()
            val player = Player(userName, 0)
            Thread(Runnable {
                Log.d(TAG, "Adding")
                playerViewModel.addPlayer(player)
            }).start()
        }.setNegativeButton("Cancel") { _, _ ->
            Log.d(TAG, "Cancel clicked")
        }
        dialog.create().show()
    }

    private fun deleteSinglePlayer() {
        Log.d(TAG, "deleteSinglePlayer")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

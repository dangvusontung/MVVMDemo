package sontung.dangvu.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import sontung.dangvu.daggerdemo.model.Bag
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.view.adapter.PlayerAdapter
import sontung.dangvu.daggerdemo.viewmodel.PlayerViewModel
import java.lang.NullPointerException

private const val TAG = "$APP_TAG MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var deleteButton: Button
    private lateinit var addButton : Button
    private lateinit var deleteSinglePlayerButton : Button

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

        deleteButton = findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            Log.d(TAG, "try to delete all player")
            Thread(Runnable {
                addPlayerThread.join()
                playerViewModel.deleteAllPlayer()
            }).start()
        }

        addButton = findViewById(R.id.addDummyUserButton)
        addButton.setOnClickListener {
            addDummyPlayer()
        }

        deleteSinglePlayerButton = findViewById(R.id.deleteSinglePlayerButton)
        deleteSinglePlayerButton.setOnClickListener {
            deleteSinglePlayer()
        }

        Log.d(TAG, "onCreate finished()")

    }

    private fun deleteSinglePlayer() {
        Log.d(TAG, "deleteSinglePlayer")
        deleteSinglePlayerThread.start()
    }

    val deleteSinglePlayerThread = Thread(Runnable {
        Log.d(TAG, "i delete")
        Thread.sleep(2000)

        try {
            for (i in playerViewModel.getPlayers.value!!.indices){
                playerViewModel.deletePlayer(playerViewModel.getPlayers.value!![0])
                Thread.sleep(1000)
            }
        } catch (e : NullPointerException){
            Log.d(TAG, "null")
        } catch (e : InterruptedException) {
            Log.d(TAG, "interrupted")
        }
    })

    private fun addDummyPlayer() {
        Log.d(TAG, "addDummyPlayer() called")
            addPlayerThread.start()
    }

    val addPlayerThread =  Thread(Runnable {
        Log.d(TAG, "hey")
        Thread.sleep(2000)

        try {
            for (i in 1..10) {
                playerViewModel.addPlayer(Player("Player $i", i))
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            Log.d(TAG, "interrupted")
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}

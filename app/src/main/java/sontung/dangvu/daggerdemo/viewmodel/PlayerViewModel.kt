package sontung.dangvu.daggerdemo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.db.AppDatabase
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.repository.PlayerRepository

private const val TAG = "$APP_TAG PlayerViewModel"

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    val database = AppDatabase.getInstance(application)
    val playerDao = database.playerDao()
    val playerRepository = PlayerRepository(playerDao)

    val getPlayers = playerRepository.loadFromDb()

    fun addPlayer(player : Player){
        Log.d(TAG, "addPlayer")
        playerRepository.addPlayer(player)
    }

    fun deletePlayer(player: Player) {
        Log.d(TAG, "deletePlayer")
        playerRepository.deletePlayer(player)
    }

    fun deleteAllPlayer() {
        Log.d(TAG, "deleteeee")
        playerRepository.deleteAllPlayer()
    }
}
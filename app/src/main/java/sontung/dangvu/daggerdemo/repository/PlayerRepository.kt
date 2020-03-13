package sontung.dangvu.daggerdemo.repository

import androidx.lifecycle.LiveData
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.db.player.PlayerDao
import sontung.dangvu.daggerdemo.model.Player
import javax.inject.Inject

private const val TAG = "$APP_TAG PlayerRepository"
class PlayerRepository @Inject constructor(
    val playerDao : PlayerDao
) {

    fun loadFromDb() : LiveData<List<Player>> {
        return playerDao.getAllPlayer()
    }

    fun deleteAllPlayer() {
        playerDao.deleteAllPlayer()
    }

    fun addPlayer(player: Player) {
        playerDao.addPlayer(player)
    }

    fun deletePlayer(player: Player) {
        playerDao.deletePlayer(player)
    }
}
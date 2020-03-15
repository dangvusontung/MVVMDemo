package sontung.dangvu.daggerdemo.db.player

import androidx.lifecycle.LiveData
import androidx.room.*
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.model.Player

private const val TAG = "$APP_TAG PlayerDao"
@Dao
interface PlayerDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player)

    @Query ("SELECT * FROM player")
    fun getAllPlayer() : LiveData<List<Player>>

    @Delete
    fun deletePlayer(player: Player)

    @Query ("DELETE FROM player")
    fun deleteAllPlayer()

    @Update
    fun updatePlayer(player: Player)
}
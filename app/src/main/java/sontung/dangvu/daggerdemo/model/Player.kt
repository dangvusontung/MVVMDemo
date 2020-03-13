package sontung.dangvu.daggerdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "player")
class Player @Inject constructor(
    @PrimaryKey
    val name : String,
    val level : Int
) {
    override fun toString(): String {
        return "Player(name='$name')"
    }
}

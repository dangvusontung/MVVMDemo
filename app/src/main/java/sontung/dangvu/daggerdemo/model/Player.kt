package sontung.dangvu.daggerdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import javax.inject.Inject

@Entity(tableName = "player")
class Player @Inject constructor(
    @PrimaryKey
    var name: String,
    val level : Int
) : Serializable {
    override fun toString(): String {
        return "Player(name='$name')"
    }
}

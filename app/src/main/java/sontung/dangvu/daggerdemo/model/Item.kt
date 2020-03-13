package sontung.dangvu.daggerdemo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    val name : String,
    val damage : Int,
    val rarity : Int
)
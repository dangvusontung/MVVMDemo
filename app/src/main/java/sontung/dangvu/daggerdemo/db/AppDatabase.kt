package sontung.dangvu.daggerdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sontung.dangvu.daggerdemo.db.player.PlayerDao
import sontung.dangvu.daggerdemo.model.Item
import sontung.dangvu.daggerdemo.model.Player

@Database (entities = [Player::class, Item::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao() : PlayerDao

    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            var tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                ).build()

                INSTANCE = instance
                return INSTANCE as AppDatabase
            }
        }
    }
}
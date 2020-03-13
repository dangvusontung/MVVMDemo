package sontung.dangvu.daggerdemo.di

import dagger.Module
import dagger.Provides
import sontung.dangvu.daggerdemo.model.Bag
import sontung.dangvu.daggerdemo.model.Item
import sontung.dangvu.daggerdemo.model.Player
import java.util.ArrayList
import javax.inject.Singleton

@Module
class PlayerModule {

    @Provides @Singleton
    fun provideItem(name : String, damage : Int, rarity : Int) : Item {
        return Item(name, damage, rarity)
    }

    @Provides @Singleton
    fun provideBag(list : ArrayList<Item>) : Bag {
        return Bag(list)
    }

    @Provides @Singleton
    fun providePlayer(name : String, level : Int) : Player {
        return Player(name, level)
    }

}
package sontung.dangvu.daggerdemo.di

import dagger.Component
import sontung.dangvu.daggerdemo.model.Player

interface AppComponent {
    fun getPlayer() : Player
}
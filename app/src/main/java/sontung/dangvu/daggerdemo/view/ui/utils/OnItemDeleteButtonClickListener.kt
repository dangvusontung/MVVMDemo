package sontung.dangvu.daggerdemo.view.ui.utils

import sontung.dangvu.daggerdemo.model.Player

interface OnItemDeleteButtonClickListener {
    fun deletePlayer(player: Player)
}
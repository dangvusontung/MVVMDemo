package sontung.dangvu.daggerdemo.view.ui.utils

import sontung.dangvu.daggerdemo.model.Player

interface OnContainerClickListener {
    fun navigateToDetail(player: Player)
}
package sontung.dangvu.daggerdemo.view.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sontung.dangvu.daggerdemo.R


class PlayerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val playerListItemContainer =
        itemView.findViewById<RelativeLayout>(R.id.player_list_item_container)
    val playerNameTextView = itemView.findViewById<TextView>(R.id.player_name)
    val playerLevelTextView = itemView.findViewById<TextView>(R.id.player_level)
    val deletePlayerButton = itemView.findViewById<ImageButton>(R.id.delete_player)
}
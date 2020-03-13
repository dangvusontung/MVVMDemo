package sontung.dangvu.daggerdemo.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.player_list_item.view.*
import sontung.dangvu.daggerdemo.R


class PlayerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val playerNameTextView = itemView.findViewById<TextView>(R.id.player_name)
    val playerLevelTextView = itemView.findViewById<TextView>(R.id.player_level)
}
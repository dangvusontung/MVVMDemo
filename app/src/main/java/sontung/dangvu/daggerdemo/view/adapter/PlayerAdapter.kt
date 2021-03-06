package sontung.dangvu.daggerdemo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.R
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.view.ui.utils.OnContainerClickListener
import sontung.dangvu.daggerdemo.view.ui.utils.OnItemDeleteButtonClickListener
import sontung.dangvu.daggerdemo.view.viewholder.PlayerViewHolder

private const val TAG = "$APP_TAG PlayerAdapter"

class PlayerAdapter(
    val onItemDeleteButtonClickListener: OnItemDeleteButtonClickListener,
    val onContainerClickListener: OnContainerClickListener,
    var list: List<Player>
) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_list_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = list[position]
        holder.playerListItemContainer.setOnClickListener {
            onContainerClickListener.navigateToDetail(player)
        }
        holder.playerNameTextView.text = player.name
        holder.playerLevelTextView.text = "${player.level}"
        holder.deletePlayerButton.setOnClickListener {
            onItemDeleteButtonClickListener.deletePlayer(player)
        }
    }

    fun updateList(list: List<Player>) {
        this.list = list
        notifyDataSetChanged()
    }

}


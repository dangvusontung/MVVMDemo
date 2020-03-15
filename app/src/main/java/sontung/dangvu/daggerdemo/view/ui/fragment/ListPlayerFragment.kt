package sontung.dangvu.daggerdemo.view.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.R
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.view.adapter.PlayerAdapter
import sontung.dangvu.daggerdemo.view.ui.utils.OnContainerClickListener
import sontung.dangvu.daggerdemo.view.ui.utils.OnItemDeleteButtonClickListener
import sontung.dangvu.daggerdemo.viewmodel.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */
private const val TAG = "$APP_TAG ListPlayer"

class ListPlayerFragment : Fragment() {

    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_player, container, false)

        val playerAdapter =
            PlayerAdapter(onDeleteButtonListener, onContainerClickListener, ArrayList())

        playerViewModel.getPlayers.observe(viewLifecycleOwner, Observer<List<Player>> {
            Log.d(TAG, "List player changed with size = [${it.size}]")
            playerAdapter.updateList(it)
        })

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = playerAdapter
        floatingButton = view.findViewById(R.id.floating)
        floatingButton.setOnClickListener {
            addPlayer()
        }
        Log.d(TAG, "onCreate finished()")
        return view
    }

    @SuppressLint("InflateParams")
    private fun addPlayer() {
        Log.d(TAG, "add player called")
        val dialog = AlertDialog.Builder(activity)
        val customDialogView = LayoutInflater.from(activity).inflate(R.layout.add_user_dialog, null)
        val userNameEditText = customDialogView.findViewById<EditText>(R.id.createUserName)
        dialog.setView(customDialogView)
        dialog.setTitle("Add player")
        dialog.setPositiveButton("Add") { _, _ ->
            Log.d(TAG, "Add clicked")
            val userName = userNameEditText.text.toString()
            val player = Player(userName, 0)
            Thread(Runnable {
                Log.d(TAG, "Adding")
                playerViewModel.addPlayer(player)
            }).start()
        }.setNegativeButton("Cancel") { _, _ ->
            Log.d(TAG, "Cancel clicked")
        }
        dialog.create().show()
    }

    private val onDeleteButtonListener = object : OnItemDeleteButtonClickListener {
        override fun deletePlayer(player: Player) {
            Thread(Runnable {
                playerViewModel.deletePlayer(player)
            }).start()
        }
    }

    private val onContainerClickListener = object : OnContainerClickListener {
        override fun navigateToDetail(player: Player) {
            val action =
                ListPlayerFragmentDirections.listToDetail(player)
            Navigation.findNavController(view!!).navigate(action)
        }
    }
}

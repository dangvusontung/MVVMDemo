package sontung.dangvu.daggerdemo.view.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import sontung.dangvu.daggerdemo.APP_TAG
import sontung.dangvu.daggerdemo.R
import sontung.dangvu.daggerdemo.model.Player
import sontung.dangvu.daggerdemo.viewmodel.PlayerViewModel

/**
 * A simple [Fragment] subclass.
 */

private const val TAG = "$APP_TAG DetailFragmetn"

class PlayerDetailFragment : Fragment() {

    val args: PlayerDetailFragmentArgs by navArgs()
    private val playerViewModel: PlayerViewModel by viewModels()

    private lateinit var playerNameDetail: EditText
    private lateinit var editPlayerDetail: Button
    private lateinit var backButtonDetail: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_player_detail, container, false)

        playerViewModel.getPlayers.observe(viewLifecycleOwner, Observer<List<Player>> {
            Log.d(TAG, "list player changed")

        })

        playerNameDetail = view.findViewById(R.id.player_name_detail)
        playerNameDetail.isEnabled = false
        val playerLevelDetail = view.findViewById<TextView>(R.id.player_level_detail)

        playerNameDetail.setText(args.player.name)
        playerLevelDetail.text = args.player.level.toString()
        editPlayerDetail = view.findViewById(R.id.edit_player_detail)
        editPlayerDetail.setOnClickListener {
            showEditPlayerDialog()
        }

        backButtonDetail = view.findViewById(R.id.back_button_detail)
        backButtonDetail.setOnClickListener {
            val action = PlayerDetailFragmentDirections.backToList()
            findNavController(requireView()).navigate(action)
        }

        return view
    }

    private fun showEditPlayerDialog() {
        val dialog = AlertDialog.Builder(activity)
        val customDialogView = LayoutInflater.from(activity).inflate(R.layout.add_user_dialog, null)
        val userNameEditText = customDialogView.findViewById<EditText>(R.id.createUserName)
        userNameEditText.setText(args.player.name)
        dialog.setView(customDialogView)
        dialog.setTitle("Edit player")
        dialog.setPositiveButton("Update") { _, _ ->
            Log.d(TAG, "Update clicked")
            val userName = userNameEditText.text.toString()
            args.player.name = userName
            Thread(Runnable {
                Log.d(TAG, "Adding")
                playerViewModel.updatePlayer(args.player)
            }).start()

            playerNameDetail.setText(userName)

        }.setNegativeButton("Cancel") { _, _ ->
            Log.d(TAG, "Cancel clicked")
        }
        dialog.create().show()
    }

}

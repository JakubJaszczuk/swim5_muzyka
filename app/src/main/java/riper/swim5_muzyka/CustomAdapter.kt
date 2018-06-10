package riper.swim5_muzyka

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.song_item_layout.view.*

class CustomAdapter(val activity: AppCompatActivity) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var textTitle: TextView
		var textAuthor: TextView
		var textDuration: TextView
		var playButton: ImageButton

		init {
			textTitle = itemView.textView_title
			textAuthor = itemView.textView_author
			textDuration = itemView.textView_duration
			playButton = itemView.playButton
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
		val context = parent.context
		val view = LayoutInflater.from(context).inflate(R.layout.song_item_layout, parent, false)// as View
		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		// Pobierz element i ustawiaj wartości
		val file = SongsData.data[position]
		val song = SongsData.parseFile(file)
		viewHolder.textTitle.text = song.title
		viewHolder.textAuthor.text = song.author
		viewHolder.textDuration.text = SongsData.parseDuration(song.duration)
		viewHolder.playButton.setOnClickListener{
			SongsData.currentSong = position
			SongsData.playNewSong()
			if(activity is MainActivity) activity.setControll()
		}
	}

	override fun getItemCount(): Int {
		return SongsData.data.size
	}

}

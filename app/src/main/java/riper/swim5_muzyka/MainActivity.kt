package riper.swim5_muzyka

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.RecyclerView
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var recyclerView: RecyclerView
	private val adapter: RecyclerView.Adapter<*> = CustomAdapter(this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		SongsData.act = this
		initRecyclerView()
		setControll()
		setSeekBar()
		setPlayButton()
		setPlayButtonImage()
		setAuxButtons()
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.menu_activity_main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return when (item.itemId) {
			R.id.item_about -> {
				AboutActivity.start(this)
			}
			R.id.item_settings -> {
				SettingsActivity.start(this)
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun initRecyclerView() {
		recyclerView = recyclerView1
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.adapter = adapter
	}

	fun setControll(){
		val song = SongsData.parseFile(SongsData.data[SongsData.currentSong])
		textViewCurrentSongTitle.text = song.title
		seekBar.max = song.duration.toInt()
		val handler = Handler()
		runOnUiThread(object: Runnable {
			override fun run() {
				val currentPosition = SongsData.mediaPlayer.currentPosition
				seekBar.progress = currentPosition
				handler.postDelayed(this, 1000)
			}
		})
	}

	private fun setSeekBar() {
		seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
			override fun onStopTrackingTouch(seekBar: SeekBar) {
			}

			override fun onStartTrackingTouch(seekBar: SeekBar) {
			}

			override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
				if(fromUser){
					SongsData.mediaPlayer.seekTo(progress)
				}
			}
		})
	}

	private fun setPlayButton(){
		imageButton_play.setOnClickListener {
			if(SongsData.mediaPlayer.isPlaying){
				SongsData.mediaPlayer.pause()
				imageButton_play.setImageDrawable(resources.getDrawable(android.R.drawable.ic_media_play))
			}
			else{
				SongsData.mediaPlayer.start()
				imageButton_play.setImageDrawable(resources.getDrawable(android.R.drawable.ic_media_pause))
			}
		}
	}

	private fun setPlayButtonImage(){
		if(SongsData.mediaPlayer.isPlaying){
			imageButton_play.setImageDrawable(resources.getDrawable(android.R.drawable.ic_media_pause))
		}
		else{
			imageButton_play.setImageDrawable(resources.getDrawable(android.R.drawable.ic_media_play))
		}
	}

	private fun setAuxButtons(){
		imageButton_forward.setOnClickListener {
			SongsData.mediaPlayer.seekTo(SongsData.mediaPlayer.currentPosition + SettingsHolder.rewindTime * 1000)
		}
		imageButton_prev.setOnClickListener {
			SongsData.mediaPlayer.seekTo(SongsData.mediaPlayer.currentPosition - SettingsHolder.rewindTime * 1000)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		SongsData.act = null
	}
}

package riper.swim5_muzyka

import android.media.AudioManager
import android.media.MediaMetadata
import android.os.Environment
import java.io.File
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.view.*
import riper.swim5_muzyka.R.id.textViewCurrentSongTitle
import java.util.*

object SongsData {

	var data: Array<File> = arrayOf<File>()
	var currentSong: Int = 0
	var mediaPlayer = MediaPlayer()
	var act: MainActivity? = null

	init {
		//val path = Environment.getExternalStorageDirectory().path + "/_ExternalSD/download"
		//val path = "/storage/self/primary/Music"
		val path = SettingsHolder.pathToMusic
		val directory = File(path)
		data = directory.listFiles()
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
		mediaPlayer.setOnCompletionListener {
			if(SettingsHolder.continue_){
				if(SettingsHolder.random){
					selectRandomSong()
					playNewSong()
				}
				else{
					mediaPlayer.start()
				}
			}
		}
	}

	fun parseFile(file: File): SongWithExtractedData{
		val metaDataRetriever = MediaMetadataRetriever()
		metaDataRetriever.setDataSource(file.absolutePath)
		var artist = metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
		if(artist == null) artist = ""
		var title = metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
		if(title == null) title = file.name.toString()
		val duration = metaDataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
		/*val mediaPlayer = MediaPlayer()
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
		mediaPlayer.setDataSource(file.absolutePath)
		mediaPlayer.prepare()
		//val duration = mediaPlayer.duration
		mediaPlayer.release()
		*/
		//Log.e("Piosenka metadane", file.toString() + "\n" + title + "\n" + artist + "\n" + duration)
		return SongWithExtractedData(file, title, artist, duration)
	}

	fun parseDuration(duration: String): String{
		val millis = duration.toInt()
		val second = millis / 1000 % 60
		val minute = millis / (1000 * 60) % 60
		val hour = millis / (1000 * 60 * 60)
		return String.format("%02d:%02d:%02d", hour, minute, second)
	}

	fun playNewSong(){
		mediaPlayer.reset()
		mediaPlayer.setDataSource(data[currentSong].absolutePath)
		mediaPlayer.prepare()
		mediaPlayer.start()
	}

	fun selectRandomSong(){
		val rand = Random()
		currentSong = rand.nextInt(data.size)
		act?.setControll()
	}
}

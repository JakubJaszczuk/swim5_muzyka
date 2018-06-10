package riper.swim5_muzyka

import android.os.Environment

object SettingsHolder {
	var rewindTime = 10
	var pathToMusic = Environment.getExternalStorageDirectory().path + "/_ExternalSD/download"
	var continue_ = false
	var random = false
}

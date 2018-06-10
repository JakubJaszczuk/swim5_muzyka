package riper.swim5_muzyka

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class AboutActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_about)
	}

	companion object {
		fun start(context: Context): Boolean {
			val starter = Intent(context, AboutActivity::class.java)
			context.startActivity(starter)
			return true
		}
	}
}

package riper.swim5_muzyka

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_settings)
		editText_time.setText(SettingsHolder.rewindTime.toString())
		textPath.setText(SettingsHolder.pathToMusic)
		setContinueCheckBox()
		setRandomCheckBox()
	}

	companion object {
		fun start(context: Context): Boolean {
			val starter = Intent(context, SettingsActivity::class.java)
			context.startActivity(starter)
			return true
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		SettingsHolder.rewindTime = editText_time.text.toString().toInt()
		SettingsHolder.pathToMusic = textPath.text.toString()
	}

	private fun setContinueCheckBox(){
		if(SettingsHolder.continue_){
			checkBoxContinue.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
		}
		else{
			checkBoxContinue.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
		}
		checkBoxContinue.setOnClickListener {
			if(SettingsHolder.continue_){
				SettingsHolder.continue_ = false
				checkBoxContinue.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
			}
			else{
				SettingsHolder.continue_ = true
				checkBoxContinue.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
			}
		}
	}

	private fun setRandomCheckBox(){
		if(SettingsHolder.random){
			checkBoxRandom.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
		}
		else{
			checkBoxRandom.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
		}
		checkBoxRandom.setOnClickListener {
			if(SettingsHolder.random){
				SettingsHolder.random = false
				checkBoxRandom.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
			}
			else{
				SettingsHolder.random = true
				checkBoxRandom.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
			}
		}
	}
}

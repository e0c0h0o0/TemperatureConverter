package com.example.temperatureconverter

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var celsiusValue: TextView
    private lateinit var fahrenheitValue: TextView

    private lateinit var celsiusSeekBar: SeekBar
    private lateinit var fahrenheitSeekBar: SeekBar
    private lateinit var interestingMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsiusValue = findViewById(R.id.celsiusValue)
        fahrenheitValue = findViewById(R.id.fahrenheitValue)



        celsiusSeekBar = findViewById(R.id.celsiusSeekBar)
        fahrenheitSeekBar = findViewById(R.id.fahrenheitSeekBar)
        interestingMessage = findViewById(R.id.interestingMessage)

        celsiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val fahrenheit = progress * 9 / 5 + 32
                fahrenheitSeekBar.progress = fahrenheit
                celsiusValue.text = "${progress}ºC"
                updateMessage(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }
        })

        fahrenheitSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress < 32) {
                    seekBar?.progress = 32
                    fahrenheitValue.text = "32ºF"
                    celsiusValue.text = "0ºC"
                    celsiusSeekBar.progress = 0
                    updateMessage(0)
                } else {
                    val celsius = (progress - 32) * 5 / 9
                    celsiusSeekBar.progress = celsius
                    fahrenheitValue.text = "${progress}ºF"
                    celsiusValue.text = "${celsius}ºC"
                    updateMessage(celsius)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not used
            }
        })
    }

    private fun updateMessage(celsius: Int) {
        interestingMessage.text = if (celsius <= 20) {
            "I wish it were warmer."
        } else {
            "I wish it were colder."
        }
    }
}

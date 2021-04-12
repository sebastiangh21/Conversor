package com.sgh21.conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sgh21.conversor.databinding.ActivityMainBinding

const val SPACE = " "

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.convertButton.setOnClickListener {

            val badge = mainBinding.badgeEditText.text.toString().toDoubleOrNull()
            var badegeEntry = mainBinding.badgeEntrySpinner.selectedItem.toString()
            var badegeOutput = mainBinding.badgeOutputSpinner.selectedItem.toString()
            var convert: Double

            if (badegeEntry == badegeOutput) {
                badegeEntry = SPACE
                badegeOutput = SPACE
            }

            when(badegeEntry) {
                "Peso Colombiano(COP)" -> convert = 0.0002302
                "Dolar Estadounidense(USD)" -> convert = 0.84981
                "Real Brasileño(BRL)" -> convert = 0.147
                "Yuan Chino(CNY)" -> convert = 0.12936
                else -> convert = 1.0
            }
            if (badge != null) {
                convert *= badge
                when (badegeOutput) {
                    "Peso Colombiano(COP)" -> convert *= 4343.40
                    "Dolar Estadounidense(USD)" -> convert *= 1.1767
                    "Real Brasileño(BRL)" -> convert *= 6.8027
                    "Yuan Chino(CNY)" -> convert *= 7.7310
                    else -> convert = badge
                }
                mainBinding.conversionTextView.text = convert.toString()
                mainBinding.badgeTextInputLayout.error = null
            } else {
                mainBinding.badgeTextInputLayout.error = getString(R.string.badge_error)
            }

        }
    }
}
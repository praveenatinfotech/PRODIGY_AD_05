package com.example.qrcodescanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                tvResult.text = "Cancelled"
            } else {
                tvResult.text = result.contents
                if (result.contents.startsWith("http://") || result.contents.startsWith("https://")) {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(result.contents))
                    startActivity(browserIntent)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

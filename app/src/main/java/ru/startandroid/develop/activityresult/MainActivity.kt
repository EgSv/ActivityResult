package ru.startandroid.develop.activityresult

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

const val REQUEST_CODE_COLOR = 1
const val REQUEST_CODE_ALIGN = 2

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var tvText:TextView
    lateinit var btnColor:Button
    lateinit var btnAlign:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tvText)

        btnColor = findViewById(R.id.btnColor)
        btnAlign = findViewById(R.id.btnAlign)

        btnColor.setOnClickListener(this)
        btnAlign.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent = Intent()
        when(v?.id) {
            R.id.btnColor -> {
                intent = Intent(this, ColorActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_COLOR)
            }
            R.id.btnAlign -> {
                intent = Intent(this, AlignActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE_ALIGN)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("myLogs", "requestCode = $requestCode resultCode = $resultCode")
        if (resultCode == RESULT_OK) {
            when(requestCode) {
                REQUEST_CODE_COLOR -> {
                    val color: Int = data!!.getIntExtra("color", Color.WHITE)
                    tvText.setTextColor(color)
                }
                REQUEST_CODE_ALIGN -> {
                    val align = data!!.getIntExtra("alignment", Gravity.LEFT)
                    tvText.gravity = align
                }
            }
        } else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show()
        }
    }
}
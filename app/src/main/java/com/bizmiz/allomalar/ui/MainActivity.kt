package com.gita.allomalar.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.Bizmiz.allomalar.R

class MainActivity : AppCompatActivity() {
    var check:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBackPressed() {
        if (check){
            super.onBackPressed()
        }else{
            exit()
        }

    }
    private fun exit() {
        val message = AlertDialog.Builder(this);
        message.setTitle("Chiqish")
            .setMessage("Dasturdan chiqmoqchimisiz?")
            .setCancelable(false)
            .setPositiveButton("Ha") { message, _ ->
                finish()
            }.setNegativeButton("Yo'q") { message, _ ->
                message.dismiss()
            }
            .create().show()
    }
}
package com.example.myshoppal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.myshoppal.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    //SnackBar表示
    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
                Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                            this@BaseActivity,
                            R.color.colorSnackBarError
                    )
            )
        }else{
            snackBarView.setBackgroundColor(
                    ContextCompat.getColor(
                            this@BaseActivity,
                            R.color.colorSnackBarSuccess
                    )
            )
        }
        snackBar.show()
    }
}
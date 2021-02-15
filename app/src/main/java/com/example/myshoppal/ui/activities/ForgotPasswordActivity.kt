package com.example.myshoppal.ui.activities

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.myshoppal.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        setupActionBar()

        //フルスクリーン画面の設定(システムバー非表示)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    //ActionBarの設定
    private fun setupActionBar() {
        setSupportActionBar(toolbar_forgot_password_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24)
        }

        toolbar_forgot_password_activity.setNavigationOnClickListener{ onBackPressed() }

        btn_submit.setOnClickListener {
            val email: String = et_email_forgot_pw.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {
                showProgressDialog(resources.getString(R.string.please_wait))
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            hideProgressDialog()
                            if (task.isSuccessful) {
                                //Show the toast message and finish the forgot password activity to go back to the login
                                Toast.makeText(
                                        this@ForgotPasswordActivity,
                                        resources.getString(R.string.email_sent_success),
                                        Toast.LENGTH_LONG
                                ).show()

                                finish()
                            } else {
                                showErrorSnackBar(task.exception!!.message.toString(), true)
                            }
                        }
            }
        }
    }
}
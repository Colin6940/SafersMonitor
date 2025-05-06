package com.example.safersmanagerapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safersmanagerapp.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnLogout = findViewById<Button>(R.id.logoutButton)

        btnLogout.setOnClickListener {
            Toast.makeText(this, "로그아웃 완료", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, QRLoginActivity::class.java))
            finish()
        }
    }
}

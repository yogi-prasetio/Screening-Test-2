package com.test.suitmedia.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.suitmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            btnNext.setOnClickListener {
                val name = edName.text.toString()
                val intent = Intent(this@MainActivity, ChooseActivity::class.java)
                intent.putExtra(ChooseActivity.EXTRA_DATA, name)
                startActivity(intent)
            }
        }

    }
}
package com.test.suitmedia.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.test.suitmedia.databinding.ActivityChooseBinding

class ChooseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChooseBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_EVENT = "extra_event"
        const val EXTRA_GUEST = "extra_guest"
        const val EXTRA_BIRTHDATE = "extra_birthdate"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_DATA)
        val event = intent.getStringExtra(EXTRA_EVENT)
        val guest = intent.getStringExtra(EXTRA_GUEST)
        val birthdate = intent.getStringExtra(EXTRA_BIRTHDATE)

        binding.apply {
            tvName.text = "Nama : $name"
            val date = birthdate?.substring(8,10)
            val dateInt = date?.toInt()

            btnEvent.setOnClickListener {
                val move = Intent(this@ChooseActivity, EventActivity::class.java)
                move.putExtra(EventActivity.EXTRA_DATA, name)
                move.putExtra(EventActivity.EXTRA_GUEST, guest)
                startActivity(move)
            }

            btnGuest.setOnClickListener {
                val move = Intent(this@ChooseActivity, GuestActivity::class.java)
                move.putExtra(GuestActivity.EXTRA_DATA, name)
                move.putExtra(GuestActivity.EXTRA_EVENT, event)
                startActivity(move)
            }

            if (event != null) {
                btnEvent.text = event
                if (guest != null) {
                    btnGuest.text = guest
                }
            } else if (guest != null) {
                btnGuest.text = guest
            }

            if (dateInt != null) {
                if (dateInt%2 == 0 && dateInt%3 == 0){
                    Toast.makeText(applicationContext, "iOS", Toast.LENGTH_SHORT).show()
                } else if (dateInt%2 == 0){
                    Toast.makeText(applicationContext, "Blackberry", Toast.LENGTH_SHORT).show()
                } else if (dateInt%3 == 0){
                    Toast.makeText(applicationContext, "Android", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Feature Phone", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
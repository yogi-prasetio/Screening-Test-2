package com.test.suitmedia.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.suitmedia.databinding.ActivityEventBinding
import com.test.suitmedia.model.DataDummy
import com.test.suitmedia.model.EventModel
import com.test.suitmedia.view.adapter.ListEventAdapter

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private var list: ArrayList<EventModel> = arrayListOf()
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_GUEST = "extra_guest"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvEvent.setHasFixedSize(true)
            list.addAll(DataDummy.generateDummyEvent())
        }
        loadDataEvent()
    }

    private fun loadDataEvent() {
        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        val eventAdapter = ListEventAdapter(list)
        binding.rvEvent.adapter = eventAdapter

        val name = intent.getStringExtra(EXTRA_DATA)
        val guest = intent.getStringExtra(EXTRA_GUEST)

        eventAdapter.setOnItemClick(object : ListEventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: EventModel?) {
                val intent = Intent(this@EventActivity, ChooseActivity::class.java)
                intent.putExtra(ChooseActivity.EXTRA_EVENT, data?.name)
                intent.putExtra(ChooseActivity.EXTRA_GUEST, guest)
                intent.putExtra(ChooseActivity.EXTRA_DATA, name)
                startActivity(intent)
            }
        })
    }
}
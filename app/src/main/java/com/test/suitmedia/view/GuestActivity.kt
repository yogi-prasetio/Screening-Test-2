package com.test.suitmedia.view

import GridGuestAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.test.suitmedia.BuildConfig.BASE_URL
import com.test.suitmedia.R
import com.test.suitmedia.databinding.ActivityGuestBinding
import com.test.suitmedia.model.GuestModel
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class GuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding
    private lateinit var adapter: GridGuestAdapter
    private var list: ArrayList<GuestModel> = ArrayList()

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_EVENT = "extra_event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GridGuestAdapter(list)
        binding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        binding.rvGuest.setHasFixedSize(true)

        loadDataGuest()
    }

    private fun loadDataGuest() {
        val images = intArrayOf(
            R.drawable.guest1,
            R.drawable.guest2,
            R.drawable.guest3,
            R.drawable.guest4,
            R.drawable.guest5
        )

        val client = AsyncHttpClient()
        client.get(BASE_URL, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                val result = String(responseBody)

                try {
                    val jsonArray = JSONArray(result)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val id = jsonObject.getInt("id")
                        val name = jsonObject.getString("name")
                        val birthdate = jsonObject.getString("birthdate")
                        list.add(GuestModel(id, name, birthdate, images[i]))
                    }
                    binding.progressBar.visibility = View.GONE
                    setRecycleView()
                } catch (e: Exception) {
                    Toast.makeText(this@GuestActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                binding.progressBar.visibility = View.GONE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@GuestActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRecycleView(){
        adapter = GridGuestAdapter(list)
        binding.rvGuest.adapter = adapter

        val name = intent.getStringExtra(EXTRA_DATA)
        val event = intent.getStringExtra(EXTRA_EVENT)
        adapter.setOnItemClick(object : GridGuestAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GuestModel?) {
                val intent = Intent(this@GuestActivity, ChooseActivity::class.java)
                intent.putExtra(ChooseActivity.EXTRA_GUEST, data?.name)
                intent.putExtra(ChooseActivity.EXTRA_BIRTHDATE, data?.birthdate)
                intent.putExtra(ChooseActivity.EXTRA_EVENT, event)
                intent.putExtra(ChooseActivity.EXTRA_DATA, name)
                startActivity(intent)
            }
        })
    }
}

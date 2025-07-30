package geeky.saif.demoappdata

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import geeky.saif.demoappdata.adapters.UserAdapter
import geeky.saif.demoappdata.apiSetUp.RetrofitInstance
import geeky.saif.demoappdata.rommSetup.AppDatabase
import geeky.saif.demoappdata.rommSetup.User

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var db: AppDatabase
    private var allUsers = listOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvList)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        db = AppDatabase.getInstance(this)

        adapter = UserAdapter(this,listOf()) { user ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("username", user.name)
            intent.putExtra("email", user.email)
            intent.putExtra("phone", user.phone)
            intent.putExtra("website", user.website)

            // Pass address fields separately
            intent.putExtra("street", user.address.street)
            intent.putExtra("suite", user.address.suite)
            intent.putExtra("city", user.address.city)
            intent.putExtra("zipcode", user.address.zipcode)
            intent.putExtra("lat", user.address.geo.lat)
            intent.putExtra("lng", user.address.geo.lng)

            // You can also pass company fields if needed
            intent.putExtra("company_name", user.company.name)
            intent.putExtra("catch_phrase", user.company.catchPhrase)
            intent.putExtra("bs", user.company.bs)

            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load data from Room or API
        lifecycleScope.launch {
            val localUsers = db.userDao().getAll()
            if (localUsers.isNotEmpty()) {
                allUsers = localUsers
                adapter.updateData(allUsers)
            } else {
                val remoteUsers = RetrofitInstance.api.getUsers()
                db.userDao().insertAll(remoteUsers)
                allUsers = remoteUsers
                adapter.updateData(allUsers)
            }
        }

        // Search/filter
        etSearch.addTextChangedListener {
            val query = it.toString().lowercase()
            val filteredList = allUsers.filter { user ->
                user.name.lowercase().contains(query)
            }
            adapter.updateData(filteredList)
        }
    }
}

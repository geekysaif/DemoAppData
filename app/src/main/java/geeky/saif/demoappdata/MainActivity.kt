package geeky.saif.demoappdata

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
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

        val recyclerView =findViewById<RecyclerView>(R.id.rvList)
        val etSearch   = findViewById<EditText>(R.id.etSearch)

        db = AppDatabase.getInstance(this)

        adapter =UserAdapter(listOf()){

            user -> val intent : Intent(this,DetailActivity::class.java)
            intent.putExtra("username", user.name)
            intent.putExtra("email", user.email)
            intent.putExtra("phone", user.phone)
            intent.putExtra("website", user.website)
            intent.putExtra("address", user.address)
            startActivity(intent)
        }

        lifecycleScope.launch {
            val local = db.userDao().getAll()
            if(local.isNotEmpty()){
                allUsers=local
                adapter.updateData(allUsers)
            }
            else{
                val remoteUsers = RetrofitInstance.api.getUsers()
                db.userDao().insertAll(remoteUsers)
                allUsers =remoteUsers
                adapter.updateData(allUsers)
            }
        }


        etSearch.addTextChangedListener {
            val query =it.toString().lowercase()
            val filteredList = allUsers.filter{
                user -> user.name.lowercase().contains(query)
            }
            adapter.updateData(filteredList)
        }


    }
}
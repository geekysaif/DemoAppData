package geeky.saif.demoappdata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //for name
        val name = intent.getStringExtra("username")
        val strName = findViewById<TextView>(R.id.name)
        strName.text = "userName: $name"

        //for email
        val email = intent.getStringExtra("email")
        val strEmail = findViewById<TextView>(R.id.email)
        strEmail.text = "Email: $email"

        //for phone
        val phone = intent.getStringExtra("phone")
        val strPhone = findViewById<TextView>(R.id.phone)
        strPhone.text = "Phone: $phone"

        //for website
        val website = intent.getStringExtra("website")
        val strWebsite = findViewById<TextView>(R.id.website)
        strWebsite.text = "Website: $website"


        //for address
        val address = intent.getStringExtra("street")
        val strAddress = findViewById<TextView>(R.id.address)
        strAddress.text = "address: $address"

    }
}
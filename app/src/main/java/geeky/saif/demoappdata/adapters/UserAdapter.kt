package geeky.saif.demoappdata.adapters



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import geeky.saif.demoappdata.MainActivity
import geeky.saif.demoappdata.R
import geeky.saif.demoappdata.rommSetup.User


class UserAdapter
    (
    private var context: Context,
    private var users: List<User>,
    private val OnItemClick: (User) -> Unit
): RecyclerView.Adapter<UserAdapter.UserViewHolder>()
 {


    fun updateData(newList: List<User>) {

        users = newList
        notifyDataSetChanged()
    }


    inner class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
        val email = view.findViewById<TextView>(R.id.email)
        val comapanyname = view.findViewById<TextView>(R.id.companyName)
        val profileImage = view.findViewById<ImageView>(R.id.profileImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }
        override fun getItemCount(): Int = users.size


        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = users[position]
            holder.name.text = user.name
            holder.email.text = user.email
            holder.comapanyname.text = user.username
            Glide.with(context).load("https://i.pravatar.cc/150?img=${user.id}").into(holder.profileImage);

            holder.itemView.setOnClickListener {
                OnItemClick(user)
            }


        }

    }

package geeky.saif.demoappdata.adapters

import android.renderscript.Type
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import geeky.saif.demoappdata.R
import geeky.saif.demoappdata.rommSetup.User

class UserAdapter
    (
    private var users: List<User>,
    private val OnItemClick: (User) -> Unit
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    fun updateData(newList: List<User>) {

        users = newList
        notifyDataSetChanged()
    }


    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        override fun getItemCount(): Int = users.size


        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            val user = users[position]
            holder.neme.text = user.name

            holder.itemView.setOnClickListener {
                OnItemClick(user)
            }


        }

    }
}
package com.shahnoza.recyclerviewanimationswipedragdrop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.shahnoza.recyclerviewanimationswipedragdrop.adapter.UserAdapter
import com.shahnoza.recyclerviewanimationswipedragdrop.adapter.UserOtherAdapter
import com.shahnoza.recyclerviewanimationswipedragdrop.databinding.ActivityMainBinding
import com.shahnoza.recyclerviewanimationswipedragdrop.model.User

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var userList:ArrayList<User>
    lateinit var userAdapter: UserAdapter
    lateinit var userOtherAdapter: UserOtherAdapter

    var count = 1
    @SuppressLint("NotifyDataSetChanged")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userList= ArrayList()
        // userAdapter = UserAdapter()
        // userAdapter.submitList(userList)

        userOtherAdapter = UserOtherAdapter(this,userList)
        binding.rv.adapter = userOtherAdapter

        // ekran bosilgandagi ishlarni tinglovchi class
        val itemTouchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                // tepa yoki pastga harakat bo`lishi
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                // chap yoki o`nga harakat bo`lishi
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            // item harakatlanganda bo`ladigan ish
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                userOtherAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                return true
            }

            // chap yoki o`ngga item surilganda bo`ladigan ish
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userOtherAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }
        // yuqoridagi itemTouch ni recycler viewga bog`lab qo`yish
        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(binding.rv)

        binding.btn.setOnClickListener {
            // list ga user malumotlarini qo`shish
            val user = User(count++,binding.edtUserName.text.toString(),binding.edtUserPassword.text.toString())
            userList.add(user)
            // userAdapter.submitList(userList)
            userOtherAdapter.notifyDataSetChanged()
            binding.rv.adapter = userOtherAdapter
        }

    }
}
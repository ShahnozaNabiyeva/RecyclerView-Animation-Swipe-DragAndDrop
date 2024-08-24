package com.shahnoza.recyclerviewanimationswipedragdrop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahnoza.recyclerviewanimationswipedragdrop.R
import com.shahnoza.recyclerviewanimationswipedragdrop.model.ItemTouchHelperAdapter
import com.shahnoza.recyclerviewanimationswipedragdrop.model.User
import java.util.Collections

class UserOtherAdapter(val context: Context, val list: ArrayList<User>):RecyclerView.Adapter<UserOtherAdapter.VH>(),ItemTouchHelperAdapter {


inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
    // onBind - listdagi ma`lumotlarni o`zida olib keladigan asosiy funktsiya hisoblanadi
    // buni ichida biz itemimizdagi viewlarni kelayotgan ma`lutmotlarga tenglashimiz va ekranga chiqarishimiz mumkin.
    fun onBind(user:User){

        itemView.findViewById<TextView>(R.id.txt_1).text = user.userName
        itemView.findViewById<TextView>(R.id.txt_2).text = user.password

        itemView.animation = AnimationUtils.loadAnimation(context,R.anim.anim1)
    }



}
    // nechta item yaratib berishi kerakligini ko`rsatish uchun
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false))
    }
    // nechta item yaratib berishi kerakligini ko`rsatish uchun
    override fun getItemCount(): Int {
        return list.size
    }
    // onBind ga listdan ma`lumotlarni olib berishi uchun kerak bo`ladigan fun
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }


    // itemni joyi o`zgarganini foydalanuvchiga bildirish uchun
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list,i,i+1)
            }
        }else{
            for (i in fromPosition until toPosition){
                Collections.swap(list,i,i-1)
            }
        }
        notifyItemMoved(fromPosition,toPosition)
    }
    // item joy o`zgarishi bekor qilinganligi haqidagi xabarni chiqarish uchun fun
    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}



package com.example.vehiclecareapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclecareapp.userDetailRedirect.forUserDetailRedirect

class ShopAdapter(private val shops: MutableList<Shop>, private val onDeleteClick: (Shop) -> Unit ) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>() {

    class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopName: TextView = itemView.findViewById(R.id.shop_name)
        val shopAddress: TextView = itemView.findViewById(R.id.shop_address)
        val shopMobile: TextView = itemView.findViewById(R.id.shop_mobile)
        val shopRating: TextView = itemView.findViewById(R.id.shop_rating)
        val shopImage: ImageView = itemView.findViewById(R.id.shop_image) // Add this line
        val shopDelete: Button = itemView.findViewById(R.id.shop_button)
        val userDetail: Button = itemView.findViewById(R.id.customerdetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = shops[position]
        holder.shopName.text = "Shop Name: ${shop.name}"
        holder.shopAddress.text = "Shop Address: ${shop.address}"
        holder.shopMobile.text = "Shop Mobile No. : ${shop.mobile}"
        holder.shopRating.text = "Rating ${shop.rating} Out of 5"

        holder.shopDelete.setOnClickListener {
            onDeleteClick(shop)  // Trigger delete callback
            removeShop(position)  // Remove item from list
        }

        holder.userDetail.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, forUserDetailRedirect::class.java)
            context.startActivity(intent)
        }



        // Load image using Glide
        Glide.with(holder.shopImage.context)
            .load(shop.imagePath) // Load image from URI
            .into(holder.shopImage)
    }



    override fun getItemCount() = shops.size

    private fun removeShop(position: Int) {
        shops.removeAt(position)
        notifyItemRemoved(position)
    }




}


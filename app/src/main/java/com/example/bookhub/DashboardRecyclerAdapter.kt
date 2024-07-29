package com.example.bookhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.model.Book
import com.squareup.picasso.Picasso


class DashboardRecyclerAdapter(val context: Context, private val itemList: ArrayList<Book>) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_recyler, parent, false)

        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val Book = itemList[position]
        holder.txtBookName.text = Book.bookName
        holder.txtBookAuthor.text = Book.bookAuthor
        holder.txtBookPrice.text = Book.bookPrice
        holder.txtBookRating.text = Book.bookRating
        Picasso.get().load(Book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

        holder.recyclerLayout.setOnClickListener{

            val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra("book_id", Book.bookId)
            context.startActivity(intent)
        }

    }

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtBookName: TextView = view.findViewById(R.id.txtBookName)
        val txtBookAuthor: TextView = view.findViewById(R.id.txtBookAuthor)
        val txtBookPrice: TextView = view.findViewById(R.id.txtBookPrice)
        val txtBookRating: TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage: ImageView = view.findViewById(R.id.imgBookImage)
        val recyclerLayout : RelativeLayout = view.findViewById(R.id.recyclerLayout)

    }
}
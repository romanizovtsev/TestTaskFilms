package com.example.testfilms.model

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testfilms.databinding.RecyclerItemBinding


class RecyclerAdapter(var mContext: Context) : RecyclerView.Adapter<MainViewHolder>() {

    var items = mutableListOf<Items>()

    fun setMovieList(films: List<Items>) {
        this.items = films.toMutableList()
        this.items.sortBy { it.releaseYear }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RecyclerItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var actorString = ""
        lateinit var distinctList: List<Actors>
        val film = items[position]
        val title = "${items[position].title} (${items[position].releaseYear})"
        val directorName = items[position].directorName.split(" ")[2] + " " +
                items[position].directorName.split(" ")[0].get(0) + ". " +
                items[position].directorName.split(" ")[1].get(0) + "."
        holder.binding.filmTitle.text = title
        holder.itemView.tag = items[position].title
        holder.binding.directorName.text = directorName
        distinctList = items[position].actors.distinctBy { it.actorName }
        distinctList.forEach {
            actorString += it.actorName
            actorString += "\n"
        }
        holder.binding.filmActors.text = actorString
        holder.itemView.setOnClickListener {
            showDialog(holder.itemView.tag.toString())
        }

    }

    private fun showDialog(title: String) {
        val alertDialog = AlertDialog.Builder(mContext)

        alertDialog.apply {

            setMessage("Фильм $title был нажат")
            setPositiveButton("Ок") { _, _ ->
            }

        }.create().show()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MainViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {}
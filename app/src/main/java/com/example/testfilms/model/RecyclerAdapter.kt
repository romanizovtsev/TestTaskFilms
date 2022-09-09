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
        holder.binding.filmTitle.text = getTitle(items[position])
        holder.binding.directorName.text = getDirectorName(items[position])
        holder.binding.filmActors.text = getActorsList(items[position])
        holder.itemView.tag = items[position].title

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

    private fun getTitle(item: Items) = "${item.title} (${item.releaseYear})"
    private fun getDirectorName(item: Items) = item.directorName.split(" ")[2] + " " +
            item.directorName.split(" ")[0].get(0) + ". " +
            item.directorName.split(" ")[1].get(0) + "."

    private fun getActorsList(item: Items): String {
        var actorString = ""
        val distinctList: List<Actors>
        distinctList = item.actors.distinctBy { it.actorName }
        distinctList.forEach {
            actorString += it.actorName
            actorString += "\n"
        }
        return actorString
    }
}

class MainViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {}
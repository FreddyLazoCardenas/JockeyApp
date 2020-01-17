package com.example.jockeyapp.features.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.entity.Movie
import com.example.jockeyapp.R
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(private val prefixPath: String,private val listener : (Movie) ->Unit) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var movies: List<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val data = movies[position]
        holder.bind(data, prefixPath)
    }

    override fun getItemCount(): Int {
        return if (movies.isNullOrEmpty()) 0 else movies.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.title
        private val tvContent = view.content
        private val image = view.image

        init {
            itemView.setOnClickListener { listener(movies[adapterPosition]) }
        }
        fun bind(data: Movie, path: String) {
            tvTitle.text = data.title
            tvContent.text = data.overview
            image.load(path + data.posterPath)

        }
    }

}
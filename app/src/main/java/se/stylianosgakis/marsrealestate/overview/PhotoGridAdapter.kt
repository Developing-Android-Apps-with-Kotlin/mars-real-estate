package se.stylianosgakis.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import se.stylianosgakis.marsrealestate.databinding.GridViewItemBinding
import se.stylianosgakis.marsrealestate.model.MarsProperty

class PhotoGridAdapter(
    private val clickListener: OnClickListener
) : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(
    MarsPropertyDiffCallback()
) {
    class MarsPropertyDiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder.from(
            parent,
            clickListener
        )
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MarsPropertyViewHolder(
        private val binding: GridViewItemBinding,
        private val clickListener: OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarsProperty) {
            binding.apply {
                marsProperty = item
                onClickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup, clickListener: OnClickListener): MarsPropertyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridViewItemBinding.inflate(layoutInflater)
                return MarsPropertyViewHolder(
                    binding,
                    clickListener
                )
            }
        }
    }
}

class OnClickListener(
    val clickListener: (marsProperty: MarsProperty) -> Unit
) {
    fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
}
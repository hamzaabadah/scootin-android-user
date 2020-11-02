package com.scootin.view.adapter

import com.scootin.R
import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import com.scootin.databinding.AdapterItemAddVegBinding
import com.scootin.network.AppExecutors
import com.scootin.network.response.sweets.SweetsItem
import timber.log.Timber


class VegItemAddAdapter(
    val appExecutors: AppExecutors,
    val imageAdapterClickListener: ImageAdapterClickLister
) : DataBoundListAdapter<SweetsItem, AdapterItemAddVegBinding>(
    appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<SweetsItem>() {
        override fun areItemsTheSame(
            oldItem: SweetsItem,
            newItem: SweetsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: SweetsItem,
            newItem: SweetsItem
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): AdapterItemAddVegBinding =
        AdapterItemAddVegBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

    override fun bind(
        binding: AdapterItemAddVegBinding,
        item: SweetsItem,
        position: Int,
        isLast: Boolean
    ) {
        Timber.i("item = $item")
        item.apply {
            binding.name.setText(name)
            binding.detail.setText(detail)
            binding.price.setText(price)
            binding.discountPrice.setText(discountprice)
        }
        binding.discountPrice.paintFlags= Paint.STRIKE_THRU_TEXT_FLAG

        binding.increment.setOnClickListener {
            val number = binding.count.text.toString().toInt()
            binding.count.text = number.inc().toString()
            imageAdapterClickListener.onIncrementItem(it)
        }
        binding.decrement.setOnClickListener {
            val number = binding.count.text.toString().toInt()
            if (number > 0)
                binding.count.text = number.dec().toString()
            imageAdapterClickListener.onDecrementItem(it)
        }

        val items = arrayOf("1/2kg", "1kg", "2kg")
        val adapter = ArrayAdapter<String>(
            binding.count.context,
            R.layout.spinner_layout_essential,
            items
        )
        binding.spinner.setAdapter(adapter)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
    }

    interface ImageAdapterClickLister {
        fun onIncrementItem(view: View)
        fun onDecrementItem(view: View)
    }
}
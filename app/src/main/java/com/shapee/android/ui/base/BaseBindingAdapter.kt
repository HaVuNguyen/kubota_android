package com.shapee.android.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.shapee.android.data.preference.SharedPreferenceHelper

abstract class BaseBindingAdapter<V : ViewDataBinding, T>(val onClicked: ((T?) -> Unit)? = null) : RecyclerView.Adapter<BaseViewHolder<V>>() {

    lateinit var prefs: SharedPreferenceHelper
    val list: MutableList<T> = mutableListOf()
    var parentWidth = 0
    lateinit var context: Context

    protected abstract fun getLayoutId(viewType: Int): Int
    abstract fun bindViewHolder(binding: V, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        parentWidth = parent.measuredWidth
        context = parent.context
        prefs = SharedPreferenceHelper(context)
        val binding = DataBindingUtil.inflate<V>(
            LayoutInflater.from(parent.context),
            getLayoutId(viewType),
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        bindViewHolder(holder.binding, position)
    }

    open fun updateData(list: MutableList<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    open fun addData(data: T) {
        this.list.add(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}

open class BaseViewHolder<V : ViewDataBinding>(val binding: V) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(position: Int) {}
}

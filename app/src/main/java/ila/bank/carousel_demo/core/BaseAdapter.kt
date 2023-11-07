package ila.bank.carousel_demo.core

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

 

/**
 * Creating an base adapter for recyclerview adapters with some boilerplate
 * functionality
 */
abstract class BaseAdapter<T, V : ViewDataBinding>() :
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder<V>>() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun onBindItem(data: T, rvItemBinding: V)

    private var items = arrayListOf<T>()

    internal fun getItems() = items

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(data: List<T>?) {
        items = (data ?: arrayListOf()) as ArrayList<T>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<V> {
        val viewDataBinding: V = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutResId(),
            parent,
            false
        )
        return BaseViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<V>, position: Int) {
        onBindItem(data = items[holder.adapterPosition], holder.itemBinding)
    }


    class BaseViewHolder<V : ViewDataBinding>(internal val itemBinding: V) :
        RecyclerView.ViewHolder(itemBinding.root)


}

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.suitmedia.databinding.GuestItemBinding
import com.test.suitmedia.model.GuestModel

class GridGuestAdapter(
    private var list: ArrayList<GuestModel>,
    private var onItemClickCallback: OnItemClickCallback? = null
): RecyclerView.Adapter<GridGuestAdapter.GridViewHolder>() {

    fun setOnItemClick(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun getItemCount(): Int = list.size

    inner class GridViewHolder(private val binding: GuestItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestModel) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(guest)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(guest.image)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgItemPhoto)
                tvNameItem.text = guest.name
                tvDateItem.text = guest.birthdate
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view = GuestItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int){
        holder.bind(list[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GuestModel?)
    }
}
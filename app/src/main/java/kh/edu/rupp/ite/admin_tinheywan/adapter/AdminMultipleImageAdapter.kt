package kh.edu.rupp.ite.admin_tinheywan.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.admin_tinheywan.databinding.AdminPostImageRowBinding

class AdminMultipleImageAdapter(
    private val imageUriList: MutableList<Uri?>,
    private val filenameList: MutableList<String?>
) : RecyclerView.Adapter<AdminMultipleImageAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdminPostImageRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdminPostImageRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.apply {
           imageView.setImageURI(imageUriList[position])
           tvFilename.text = filenameList[position]
       }
    }

    override fun getItemCount() = imageUriList.size
}
@file:Suppress("DeferredResultUnused")

package and5.abrar.challenge_ch4

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.edit_dialog.view.*
import kotlinx.android.synthetic.main.item_adapter_user.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@DelicateCoroutinesApi
class AdapterUser(private val listdatauser : List<User>):RecyclerView.Adapter<AdapterUser.ViewHolder>() {
    private var mDBnew : DbUser? = null
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter_user,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.idUser.text = listdatauser[position].id.toString()
        holder.itemView.Title.text = listdatauser[position].title
        holder.itemView.text.text = listdatauser[position].text
        //problem
        holder.itemView.delete.setOnClickListener {
            mDBnew = DbUser.getInstance(it.context)
            AlertDialog.Builder(it.context).setTitle("Hapus Data").setMessage("Yakin Hapus data?")
                .setPositiveButton("ya"){
                        _: DialogInterface, _: Int ->
                    GlobalScope.async {
                        val result = mDBnew?.userDao()?.deleteUser(listdatauser[position])
                        (holder.itemView.context as MainActivity).runOnUiThread{
                            if (result != 0){
                                Toast.makeText(it.context, "data ${listdatauser[position].title} dihapus",
                                    Toast.LENGTH_LONG).show()
                                (holder.itemView.context as MainActivity).recreate()
                            }else{
                                Toast.makeText(it.context, "gagal di hapus", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
                .setNegativeButton("Tidak"){
                        dialogInterface : DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }
        //problem
        holder.itemView.edit.setOnClickListener {
            mDBnew= DbUser.getInstance(it.context)
            val dEdit = LayoutInflater.from(it.context).inflate(R.layout.edit_dialog,null,false)
            val dDialog = AlertDialog.Builder(it.context).setView(dEdit).create()
            dEdit.ed_text.setText(listdatauser[position].text)
            dEdit.ed_title.setText(listdatauser[position].title)
            dEdit.ubah.setOnClickListener {
                val titleBaru = dEdit.ed_title.text.toString()
                val textBaru = dEdit.ed_text.text.toString()

                listdatauser[position].title = titleBaru
                listdatauser[position].text = textBaru

                GlobalScope.async {
                    val up = mDBnew?.userDao()?.updateUser(listdatauser[position])

                    (dEdit.context as MainActivity).runOnUiThread{
                        if (up != 0){
                            Toast.makeText(it.context, "Data Berhasil Di Ubah",Toast.LENGTH_SHORT).show()
                            (dEdit.context as MainActivity).recreate()
                        }else{
                            Toast.makeText(it.context,"data gagal di ubah",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            dDialog.show()
        }
    }

    override fun getItemCount(): Int {
       return listdatauser.size
    }
}
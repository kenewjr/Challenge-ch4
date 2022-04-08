@file:Suppress("DeferredResultUnused")

package and5.abrar.challenge_ch4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_add.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


@DelicateCoroutinesApi
class DialogAdd : DialogFragment() {
    private var dbuser : DbUser? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbuser = DbUser.getInstance(requireContext())

        add.setOnClickListener {
            GlobalScope.async {
                val ttl = in_title.text.toString()
                val txt = in_text.text.toString()
                if (in_title.text.isNotEmpty() && in_text.text.isNotEmpty()){
                val hasil = dbuser?.userDao()?.insertUser(User(null, ttl,txt ))
                activity?.runOnUiThread{
                    if (hasil != 0.toLong()){
                        Toast.makeText(requireContext(),"Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(requireContext(),"Failed", Toast.LENGTH_LONG).show()
                    }
                }
                }else{
                    Toast.makeText(requireContext(),"Mohon isi text dan title", Toast.LENGTH_LONG).show()
                }
            }
            dismiss()
            }
    }
    override fun onDetach() {
        super.onDetach()
        activity?.recreate()
    }
}
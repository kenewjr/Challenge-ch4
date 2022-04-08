package and5.abrar.challenge_ch4

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class HomeFragment : Fragment() {
    private var dbuser : DbUser? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sf = requireContext().getSharedPreferences("dtUser", Context.MODE_PRIVATE)
        val user = sf.getString("username", "")
        welcome.text = "Hello, $user"
        getDataUser()
        dbuser=DbUser.getInstance(requireContext())
        fab_add.setOnClickListener{
            DialogAdd().show(childFragmentManager, "adddialog")
        }
        logout.setOnClickListener {
            logout()
        }

    }
    private fun getDataUser(){
        rv_user.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        GlobalScope.launch {
            val listdata  =  dbuser?.userDao()?.getAllUser()
            activity?.runOnUiThread {
                listdata?.let {
                    val adapt = AdapterUser(it)
                    rv_user.adapter = adapt
                }
            }
        }
    }

    private fun logout(){
        AlertDialog.Builder(requireContext())
            .setTitle("LOGOUT")
            .setMessage("Yakin ingin logout?")
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }.setPositiveButton("Ya"){ _: DialogInterface, _: Int ->
                val sf = requireContext().getSharedPreferences("dtUser", Context.MODE_PRIVATE)
                val sfe = sf.edit()
                sfe.clear()
                sfe.apply()
                activity?.finish()
                startActivity(activity?.intent)
            }.show()
    }
    override fun onResume() {
        super.onResume()
        getDataUser()
    }

    }

package and5.abrar.challenge_ch4

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
        btnLogin.setOnClickListener {
            login()
        }
        btnLogin.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                login()
                return@OnKeyListener true
            }
            false
        })
    }
    private fun login(){
        if(login_email.text.isNotEmpty() && login_password.text.isNotEmpty() ){
            val sf = requireContext().getSharedPreferences("dtUser",Context.MODE_PRIVATE)
            val dtEmail = sf.getString("email","")
            val dtpass = sf.getString("pass","")

            val email = login_email.text.toString()
            val pass = login_password.text.toString()
            if(dtEmail == email && dtpass == pass){
                Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_homeFragment)
            }else{
                Toast.makeText(requireContext(), "email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Mohon isi Email Dan password", Toast.LENGTH_SHORT).show()
        }
    }
}
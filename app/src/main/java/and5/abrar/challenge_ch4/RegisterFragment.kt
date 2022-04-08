package and5.abrar.challenge_ch4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.in_email
import kotlinx.android.synthetic.main.fragment_register.in_pass


class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRegis.setOnClickListener {
            if (in_user.text.isNotEmpty() &&
                    in_pass.text.isNotEmpty()&&
                    in_email.text.isNotEmpty()&&
                    in_passwordulang.text.isNotEmpty()){
                if (in_pass.text.toString() == in_passwordulang.text.toString()){
                    inputUser()
                    Toast.makeText(requireContext(), "Akun berhasil terbuat", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                }else{
                    Toast.makeText(requireContext(), "Password Tidak sama", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "Mohon isi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show()
            }
        }
    }
   private fun inputUser(){
       val username = in_user.text.toString()
       val password = in_pass.text.toString()
       val email = in_email.text.toString()
       val verifpass = in_passwordulang.text.toString()

       val sf = requireContext().getSharedPreferences("dtUser",Context.MODE_PRIVATE)
       val esf = sf.edit()
       esf.putString("username",username)
       esf.putString("pass",password)
       esf.putString("email",email)
       esf.putString("confirmpass",verifpass)
       esf.apply()


   }

}
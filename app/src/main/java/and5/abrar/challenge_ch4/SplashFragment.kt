package and5.abrar.challenge_ch4

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation



class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_splash, container, false)
        val getShared = requireContext().getSharedPreferences("dtUser", Context.MODE_PRIVATE)
        Handler(Looper.getMainLooper()).postDelayed({
            if(getShared.contains("email")){
                Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_loginFragment)
            }
        },3000)
        return v
    }
}
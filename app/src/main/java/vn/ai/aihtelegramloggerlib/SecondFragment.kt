package vn.ai.aihtelegramloggerlib

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import vn.ai.aihtelegramlogger.Services.SendMessage
import vn.ai.aihtelegramlogger.TelegramLogger
import vn.ai.aihtelegramloggerlib.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            try {
                throw RuntimeException("Run to this line and crash app");
            } catch (e: Exception) {
                TelegramLogger.sendMessage(
                    SendMessage(
                        functionName = "onViewCreated",
                        className = "SecondFragment",
                        appName = "testing",
                        appVersion = "1.1.1",
                        errorText = e.message
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
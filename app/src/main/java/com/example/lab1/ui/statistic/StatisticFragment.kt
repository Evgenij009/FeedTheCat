package com.example.lab1.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.databinding.FragmentStatisticBinding
import com.example.lab1.service.DatabaseManager

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(StatisticViewModel::class.java)

        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStatistic
        val db = DatabaseManager(requireContext().applicationContext)
        db.openDb()
        textView.text = db.read().reversed().joinToString("\n")
        db.closeDb()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.lab1.ui.home

import android.R.attr.button
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.R
import com.example.lab1.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var textView: TextView
    private lateinit var imageCat: ImageView
    private lateinit var btn: Button
    private var lastAngle = -1;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        textView = binding.textView
        imageCat = binding.imageCat
        btn = binding.countButton
        btn.setOnClickListener { view ->
            countMe()
        }
        return root
    }

    fun countMe() {
        val countString = textView.text.toString();
        var count: Int = Integer.parseInt(countString);
        count++;


        if (count % 3 == 0) {
            val s = AnimationSet(true)
            s.addAnimation(spinCat())
            s.addAnimation(scalingCat())
            imageCat.startAnimation(s)
        }
        textView.text = count.toString()
    }

    fun spinCat() : Animation {
        var angle = Random.nextInt(3600 - 360) + 360;
        val pivotX = imageCat.width / 2;
        val pivotY = imageCat.height / 2;
        val animation: Animation = RotateAnimation(
            (if (lastAngle == -1) 0 else lastAngle).toFloat(),
            angle.toFloat(), pivotX.toFloat(), pivotY.toFloat()
        )
        lastAngle = angle
        animation.duration = 2500
        animation.fillAfter = true
        imageCat.startAnimation(animation)
        return animation;
    }

    fun scalingCat() : Animation? {
        val animation = AnimationUtils.loadAnimation(context, R.anim.bounce)

        val interpolator = BounceInterpolator(0.2, 20.0)
        animation.interpolator = interpolator
        return animation;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
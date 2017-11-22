package com.example.matthewtaila.rendevous.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matthewtaila.rendevous.R

/**
 * Created by matthewtaila on 8/25/17.
 */

class SignInFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): SignInFragment{
            val signInFragment = SignInFragment()
            val args = Bundle()
            signInFragment.arguments = args
            return signInFragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView: View = inflater!!.inflate(R.layout.fragment_sign_in, container, false)
        return rootView
    }

}
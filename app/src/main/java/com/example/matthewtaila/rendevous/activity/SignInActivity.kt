package com.example.matthewtaila.rendevous.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.matthewtaila.rendevous.R
import com.example.matthewtaila.rendevous.fragments.SignInFragment
import com.example.matthewtaila.rendevous.fragments.SignUpFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setUpViewpager()
    }

    private fun setUpViewpager() {

        val x = FragmentPagerItemAdapter(supportFragmentManager, FragmentPagerItems.with(this)
                .add("Sign In", SignInFragment::class.java)
                .add("Sign Up", SignUpFragment::class.java)
                .create())

        signInViewPager.adapter = x
        stl_ViewpagerTab.setViewPager(signInViewPager)

        val mSignInPagerAdaper = SignInPagerAdapter(supportFragmentManager)
        signInViewPager.setAdapter(mSignInPagerAdaper)

    }

    inner class SignInPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter (fm) {

        val NUM_ITEMS = 2

        override fun getItem(position: Int): Fragment? {

            var fragment = Fragment()

            when (position) {
                0 -> fragment = SignInFragment.newInstance()
                1 -> fragment = SignUpFragment.newInstance()
            }
            return fragment
        }

        override fun getCount(): Int {
           return NUM_ITEMS
        }
    }

}






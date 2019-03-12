package `in`.ac.bvmengineering.udaan2k19.Fragment

import android.support.v4.app.Fragment

abstract class FragmentThatCanRespondToBack : Fragment() {
    abstract fun onBackPressed(): Boolean
}
package `in`.ac.bvmengineering.udaan2k19.Activity

import `in`.ac.bvmengineering.udaan2k19.Fragment.EventCategoryFragment
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class EventActivity : AppCompatActivity() {
    var fragment: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        switchFragment(EventCategoryFragment())
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.entry, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
        fragmentTransaction.add(R.id.event_frame, fragment, "a")
        fragmentTransaction.commitNow()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.executePendingTransactions()
        Toast.makeText(this, "${supportFragmentManager.backStackEntryCount}", Toast.LENGTH_LONG).show()
//        fragment = currentFragment()
//        when (fragment?.tag) {
//            "Category" -> finish()
//            "Events", "Details" -> supportFragmentManager.popBackStackImmediate(fragment?.tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        }
    }

    private fun currentFragment(): Fragment? {
        return supportFragmentManager.findFragmentByTag(supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1).name)
    }
}
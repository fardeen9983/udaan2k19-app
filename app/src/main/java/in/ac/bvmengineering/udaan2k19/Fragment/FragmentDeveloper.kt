package `in`.ac.bvmengineering.udaan2k19.Fragment

import `in`.ac.bvmengineering.udaan2k19.Adapter.DeveloperAdapter
import `in`.ac.bvmengineering.udaan2k19.DataClass.Developer
import `in`.ac.bvmengineering.udaan2k19.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.preference.PowerPreference
import kotlinx.android.synthetic.main.fragment_developer.*

class FragmentDeveloper : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_developer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developer_list.layoutManager = LinearLayoutManager(context)
        val developers: Array<Developer> = PowerPreference.getDefaultFile().getObject("developers", Array<Developer>::class.java)
        developer_list.adapter = DeveloperAdapter(context!!, developers.toCollection(ArrayList<Developer>()))
    }
}
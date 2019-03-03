package `in`.ac.bvmengineering.udaan2k19.Adapter

import `in`.ac.bvmengineering.udaan2k19.DataClass.Developer
import `in`.ac.bvmengineering.udaan2k19.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.developer_list_item.view.*

class DeveloperAdapter(private val context: Context, private val developers: ArrayList<Developer>) : RecyclerView.Adapter<DeveloperAdapter.DeveloperViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DeveloperViewHolder {
        return DeveloperViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.developer_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return developers.size
    }

    override fun onBindViewHolder(p0: DeveloperViewHolder, p1: Int) {
        val developer = developers[p1]
        p0.name.text = developer.name
        p0.type.text = developer.type
        p0.image.setImageDrawable(context.getDrawable(developer.imageID))
        p0.github.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(context.getString(R.string.gith_link) + developer.github)
            context.startActivity(i)
        }
        p0.phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + developer.phone)
            context.startActivity(intent)
        }
        p0.mail.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf(developer.mail))
            email.type = "message/rfc822"
            context.startActivity(Intent.createChooser(email, "Choose an Email App :"))
        }
    }

    class DeveloperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.developer_icon!!
        val name = view.developer_name!!
        val type = view.developer_type!!
        val github = view.github!!
        val mail = view.mail!!
        val phone = view.dialer!!
    }
}
package xyz.quenix.xai_assistant

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import xyz.quenix.xai_assistant.scripts.*


class SettingFragment : Fragment() {

    private var AuthImage: ImageView? = null
    private var AuthName: TextView? = null
    private var AuthId: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_setting, container, false)

        AuthImage = rootView.findViewById(R.id.AuthImage) as ImageView
        AuthName = rootView.findViewById(R.id.AuthName) as TextView
        AuthId = rootView.findViewById(R.id.AuthId) as TextView

        Picasso.with(this.context).load(ControlActivity.Companion.account?.photoUrl.toString()).transform(CircleTransform()).into(AuthImage)

        AuthName?.text = ControlActivity.Companion.account?.displayName
        AuthId?.text = ControlActivity.Companion.account?.id

        return rootView
    }

}

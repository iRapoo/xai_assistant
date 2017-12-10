package xyz.quenix.xai_assistant.scripts

import android.app.AlertDialog
import android.content.Context

object Windows {

    fun alert(context: Context?, Title: String, Text: String) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle(Title)
                .setMessage(Text)
                .setCancelable(true)
                .setPositiveButton("ОК"
                ) { dialog, id -> dialog.cancel() }

        val alert = builder.create()
        alert.show()

    }

}

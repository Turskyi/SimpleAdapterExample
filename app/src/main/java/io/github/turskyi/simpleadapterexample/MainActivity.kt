package io.github.turskyi.simpleadapterexample

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    companion object {
        // attribute names for Map
        const val ATTRIBUTE_NAME_TEXT = "text"
        const val ATTRIBUTE_NAME_CHECKED = "checked"
        const val ATTRIBUTE_NAME_IMAGE = "image"
    }

    private lateinit var lvSimple: ListView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // data arrays
        val texts: Array<String> = arrayOf(
            "sometext 1", "sometext 2", "sometext 3",
            "sometext 4", "sometext 5"
        )
        val checked: BooleanArray = booleanArrayOf(true, false, false, true, false)
        val img: Int = R.mipmap.ic_launcher

        // packing the data into a structure understandable for the adapter
        val adapterData: ArrayList<Map<String, Any>> = ArrayList(texts.size)
        var mutableMap: MutableMap<String, Any>
        for (i in texts.indices) {
            mutableMap = HashMap()
            mutableMap[ATTRIBUTE_NAME_TEXT] = texts[i]
            mutableMap[ATTRIBUTE_NAME_CHECKED] = checked[i]
            mutableMap[ATTRIBUTE_NAME_IMAGE] = img
            adapterData.add(mutableMap)
        }

        // an array of attribute names from which the data will be read
        val keyList: Array<String> = arrayOf(
            ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED,
            ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT
        )
        // an array of ID View-components into which data will be inserted
        val viewList: IntArray = intArrayOf(R.id.tvText, R.id.cbChecked, R.id.ivImg, R.id.cbChecked)

        // creating adapter
        val simpleAdapter = SimpleAdapter(
            this, adapterData, R.layout.item,
            keyList, viewList
        )

        // определяем список и присваиваем ему адаптер
        lvSimple = findViewById<View>(R.id.lvSimple) as ListView
        lvSimple.adapter = simpleAdapter
    }
}
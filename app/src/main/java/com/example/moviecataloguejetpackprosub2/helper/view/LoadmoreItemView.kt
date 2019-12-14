package com.example.moviecataloguejetpackprosub2.helper.view

import com.example.moviecataloguejetpackprosub2.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class LoadmoreItemView : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
    }

    override fun getLayout(): Int = R.layout.load_more_progress
}
package com.example.growby.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growby.R
import com.example.growby.adapter.ArtikelAdapter
import com.example.growby.data.artikel.Artikel
import com.example.growby.data.artikel.ArtikelData
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_artikel.*
import kotlinx.android.synthetic.main.fragment_artikel.view.*


class FragmentArtikel: Fragment() {
    private var list: ArrayList<Artikel> = arrayListOf()
    var sampleImages = intArrayOf(
        R.drawable.artikel1,
        R.drawable.artikel2,
        R.drawable.artikel3,
        R.drawable.artikel4
    )
    private lateinit var carouselView: CarouselView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artikel, container, false)
        view.rv_artikel.setHasFixedSize(true)
        list.addAll(ArtikelData.listArtikel)
        show(view)
        carouselView = view.carosel
        carouselView.pageCount = sampleImages.size
        carouselView.setImageListener(imageListener)
        return view
    }
    private fun show(view: View){
        view.rv_artikel.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = ArtikelAdapter(list, requireContext())
        view.rv_artikel.adapter = adapter
    }
    var imageListener = ImageListener { position, imageView -> imageView.setImageResource(sampleImages[position]) }
}
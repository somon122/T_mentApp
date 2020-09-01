package com.example.pta.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.pta.R

class HomeFragment : Fragment() {




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var root =  inflater.inflate(R.layout.fragment_home, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel("https://resize.indiatvnews.com/en/resize/newbucket/715_-/2020/03/pubg-mobile-1-1583916680.jpg", "And people do that.", ScaleTypes.FIT))
        imageList.add(SlideModel("https://images.wallpapersden.com/image/download/pubg-fire-illustration_63574_1600x1200.jpg", "And people do that.", ScaleTypes.FIT))
        imageList.add(SlideModel("https://i.ytimg.com/vi/pKUu6PKNyzk/maxresdefault.jpg", "And people do that.", ScaleTypes.FIT))
        imageList.add(SlideModel("https://mobilemodegaming.s3.ap-south-1.amazonaws.com/wp-content/uploads/2019/12/Free-Fire-TDM-Mode-1.png", "And people do that.", ScaleTypes.FIT))
        imageList.add(SlideModel("https://staticg.sportskeeda.com/editor/2020/06/1d27a-15925520527800-500.jpg", "And people do that.", ScaleTypes.FIT))



        val imageSlider = root.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)
        imageSlider.startSliding(5000) // with new period
        imageSlider.startSliding()
        imageSlider.stopSliding()
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {

                if (position==0){
                    Toast.makeText(context,"0",Toast.LENGTH_LONG).show()
                }else if (position==1){
                    Toast.makeText(context,"1",Toast.LENGTH_LONG).show()
                }else if (position==2){
                    Toast.makeText(context,"2",Toast.LENGTH_LONG).show()
                }else if (position==3){
                    Toast.makeText(context,"3",Toast.LENGTH_LONG).show()
                }else if (position==4){
                    Toast.makeText(context,"4",Toast.LENGTH_LONG).show()
                }
            }
        })

        return root;
    }

}

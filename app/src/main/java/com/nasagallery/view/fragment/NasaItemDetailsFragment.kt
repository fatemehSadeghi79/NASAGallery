package com.nasagallery.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.nasagallery.R
import com.nasagallery.databinding.FragmentNasaItemDetailsBinding
import com.nasagallery.model.local.NASAPhotoItem
import com.nasagallery.viewmodel.NasaItemViewModel


class NasaItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNasaItemDetailsBinding
    private lateinit var nasaViewModel: NasaItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nasa_item_details,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nasaViewModel = ViewModelProvider(requireActivity())[NasaItemViewModel::class.java]

        nasaViewModel.photoItem.observe(viewLifecycleOwner) { item ->
            uiDesign(item)

        }
    }

    private fun uiDesign(item: NASAPhotoItem) {
        binding.txtViewCopyright.text = "Copyright: "+item.copyright
        binding.txtViewDateDetail.text = item.date
        binding.txtViewTitleDetail.text = item.title
        binding.txtViewDescription.text = item.explanation

        binding.imgBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        Glide.with(requireContext()).load(item.url).into(binding.imgViewPhotoNasaDetail)
    }

}
package com.example.qataloog.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.qataloog.database.LibraryDatabase
import com.example.qataloog.databinding.FragmentCreateLibraryBinding
import com.example.qataloog.viewmodel.LibraryViewModel
import com.example.qataloog.viewmodel.LibraryViewModelFactory
import java.io.File


class CreateLibraryFragment : Fragment() {
    private lateinit var binding: FragmentCreateLibraryBinding
    val YOUR_IMAGE_CODE = 200
    private lateinit var libraryViewModel: LibraryViewModel
    private lateinit var libraryTitle: String
    private lateinit var libraryDescription: String
    private lateinit var selectedImageUri: Uri
    private lateinit var libraryIcon: String
    private lateinit var libraryDatabase: LibraryDatabase
    private lateinit var libraryImagePath: String
    private var postPath: String? = null
    private var mediaPath: String? = null
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateLibraryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        libraryDatabase = LibraryDatabase.getInstance(requireContext())
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        setupViewmodel()
        binding.libraryIconEdittext.setOnClickListener {
            selectLibraryIcon()
        }
        binding.librarySaveButton.setOnClickListener {
            createLibrary()
        }

    }

    private fun selectLibraryIcon() {
//        val intent = Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "select a picture"), YOUR_IMAGE_CODE)

        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO)
    }

    private fun createLibrary() {
        libraryImagePath = selectedImageUri.path!!
        uploadLibraryFile()
        successMessage()
    }

    private fun successMessage() {
        libraryViewModel.getResponseMessage().observe(viewLifecycleOwner, Observer {
            val toast = Toast.makeText(activity, it, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        })
    }

    private fun setupViewmodel() {
        val application = requireNotNull(activity).application
        val dataSource = LibraryDatabase.getInstance(application).libraryDao
        val viewModelFactory = LibraryViewModelFactory(dataSource, application)
        libraryViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(LibraryViewModel::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode === REQUEST_PICK_PHOTO) {
                selectedImageUri = data?.data!!
                selectedImageUri.path?.let { Log.d("imagePath", it) }
                binding.libraryIcon.editText?.setText(selectedImageUri.path)
            }
        }
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(
                selectedImageUri, filePathColumn,
                null, null, null
            )
            assert(cursor != null)
            cursor!!.moveToFirst()

            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            mediaPath = cursor.getString(columnIndex)
            // Set the Image in ImageView for Previewing the Media
//        imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath))
            cursor.close()
            postPath = mediaPath
        }

    companion object {
        private const val REQUEST_PICK_PHOTO = 2
    }

    private fun uploadLibraryFile() {
        if (postPath == null || postPath == "") {
            Toast.makeText(activity, "please select an image ", Toast.LENGTH_LONG).show()
            return
        } else {
            val file = File(postPath!!)
            libraryTitle = binding.libraryTitle.editText?.text.toString()
            libraryDescription = binding.libraryDescription.text.toString()
            libraryIcon = selectedImageUri.toString()
            libraryViewModel.createLibrary(preferences, libraryTitle, libraryDescription, file)
        }

    }

}


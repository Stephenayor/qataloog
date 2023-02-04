package com.example.qataloog.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qataloog.databinding.FragmentUploadsBinding
import com.example.qataloog.model.responsemodel.BookUpload
import com.example.qataloog.network.UploadsApi
import com.example.qataloog.network.UsersRetrofitClientInstance
import com.example.qataloog.utilities.FileUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class UploadsFragment : Fragment() {
    private var uri: Uri? = null
    private lateinit var binding: FragmentUploadsBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var pdfPath: String
    private val FILE_PICKER_REQUEST_CODE = 1
    private val REQUEST_CAMERA = 0
    private  var SELECT_FILE:Int = 1
    private var mediaPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUploadsBinding.inflate(inflater, container, false)

        setupViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        binding.uploadEbookCardView.setOnClickListener {
            launchPdfPicker()
        }
        clickListeners()
    }

    private fun setupViewModel() {
    }

    private fun launchPdfPicker() {
//        MaterialFilePicker()
//            .withActivity(activity)
//            .withRequestCode(FILE_PICKER_REQUEST_CODE)
//            .withHiddenFiles(true)
//            .withFilter(Pattern.compile(".*\\.pdf$"))
//            .withTitle("Select PDF file")
//            .start()

        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE)

    }


//   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (resultCode == RESULT_OK) {
//            val uri = data?.data
//            pdfPath = getPDFPath(uri)!!
//
//
////            if (filePath != null) {
////                Log.d("FilePath", filePath)
////            }
////            val path = data?.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
////            Log.d("Path1: ", path!!)
////            val file = File(path)
////            if (path != null) {
////                Log.d("Path: ", path)
////                pdfPath = path
////                Toast.makeText(activity, "Picked file: $path", Toast.LENGTH_LONG).show()
////            }
//
//        }
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED){
        if (resultCode == Activity.RESULT_OK) {
            uri = data?.data
            pdfPath = uri?.path!!
        }

    }}

    @SuppressLint("Range")
    fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.getScheme().equals("content")) {
            val cursor: Cursor? = requireActivity().contentResolver
                .query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment()
        }
        return result
    }

    private fun uploadEbook() {
        binding.pdfPathEdittext.setText(pdfPath)
        val file: File = File(uri?.let { FileUtil.getPath(it, requireContext()) })
//        val file = File(pdfPath)
        preferences = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)!!
        val bookFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val book = MultipartBody.Part.createFormData("book", file.name, bookFile)
        val authToken: String? = preferences.getString("authToken", null)


        val content =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "Books")
        val author =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "Samji Diamond")
        val title =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "Samuel Engineering Research")
        val descriptionData =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(),
                "<p>ThisbookisimportantforallTvetstudenttogothroughitandseereachimadeandbecomebettertoimorrow</p>")
        val categoryId =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "3")
        val subCategoryId =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(),
                "69")
        val isbn =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "ISBN8349234324")
        val publisher =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "Samji Diamond")
        val status =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "1")
        val visibilitystatus =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "0")
        val isFree =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "1")
        val cost =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "0")
        val language =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "english")
        val pub_year =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "2022-06")


        val uploadsApi: UploadsApi = UsersRetrofitClientInstance.getRetrofitInstance()
            ?.create(UploadsApi::class.java)!!
        val call: Call<BookUpload> = uploadsApi
            .uploadBooks("Bearer $authToken",
                content, author, title, descriptionData,
                categoryId, subCategoryId, isbn, publisher, status, visibilitystatus,
                isFree, cost, language, pub_year,
                book)
        call.enqueue(object : Callback<BookUpload> {
            override fun onResponse(
                call: Call<BookUpload>,
                response: Response<BookUpload>
            ) {
                if (response.isSuccessful) {
                   Toast.makeText(activity, "UPLOADS HAPPENED", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<BookUpload>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(activity, "shit HAPPENED", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun clickListeners(){
        binding.uploadBtn.setOnClickListener {
            uploadEbook()
        }
    }


}
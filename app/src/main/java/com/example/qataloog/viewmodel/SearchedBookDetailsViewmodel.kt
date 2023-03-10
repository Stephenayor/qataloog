package com.example.qataloog.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qataloog.model.responsemodel.bookssearch.BookQueryData
import com.example.qataloog.model.responsemodel.dashboard.Recommended
import com.example.qataloog.network.PdfRetrofitInstance
import com.example.qataloog.network.RecommendedBooksPdf
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import kotlin.concurrent.thread

class SearchedBookDetailsViewmodel(val fileDir: File, bookData: BookQueryData) :
    ViewModel() {

    private var pdfFileName: File
    private var dirPath: String
    private var fileName: String
    var isFileReadyObserver = MutableLiveData<Boolean>()

    init {
        dirPath = "${fileDir}/cert/pdffiles"
        val dirFile = File(dirPath)
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }
        fileName = bookData.title + ".Pdf"
        val file = "${dirPath}/${fileName}"
        pdfFileName = File(file)
        if (pdfFileName.exists()) {
            pdfFileName.delete()
        }
    }

    fun getPdfFileUri(): File = pdfFileName

    fun downloadPdfFile(pdfUrl: String) {
        thread {
            val retroServiceInterface =
                PdfRetrofitInstance.getRetroInstance().create(RecommendedBooksPdf::class.java)
            retroServiceInterface.downloadPdfFile(pdfUrl).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    isFileReadyObserver.postValue(false)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.e("====", "====response : $response")
                    Log.e("====", "====response : " + response.isSuccessful)
                    if (response.isSuccessful) {
                        val result = response.body()?.byteStream()
                        result?.let {
                            writeToFile(it)
                        } ?: kotlin.run {
                            isFileReadyObserver.postValue(false)
                        }
                    } else
                        isFileReadyObserver.postValue(false)
                }
            })
        }
    }

    private fun writeToFile(inputStream: InputStream) {
        try {
            Log.e("====", "====writeToFile : ")
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded = 0
            val fos: OutputStream = FileOutputStream(pdfFileName)
            do {
                val read = inputStream.read(fileReader)
                if (read != -1) {
                    fos.write(fileReader, 0, read)
                    fileSizeDownloaded += read
                }
            } while (read != -1)
            fos.flush()
            fos.close()
            isFileReadyObserver.postValue(true)
        } catch (e: IOException) {
            Log.e("====", "====IOException : " + e)
            isFileReadyObserver.postValue(false)
        }
    }
}
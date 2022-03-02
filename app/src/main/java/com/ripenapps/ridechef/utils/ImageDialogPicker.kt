package com.ripenapps.ridechef.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment

import android.provider.MediaStore

import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ripenapps.ridechef.R
import com.ripenapps.ridechef.databinding.FragmentImageDialogPickerBinding
import java.io.ByteArrayOutputStream


class ImageDialogPicker(imagePickerInterface: ImagePickerInterface) : DialogFragment() {

    lateinit var binding: FragmentImageDialogPickerBinding
    val TAG = "ImageDialogPicker"
    val TAKE_PICTURE = 1
    val GALLERY_PICTURE = 2
    val imageDialogPicker: ImagePickerInterface = imagePickerInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_image_dialog_picker,
            container,
            false
        )

        askPermissions(requireContext())

        binding.camera.setOnClickListener {
            openCamera()
        }

        binding.gallery.setOnClickListener {
            openGallery()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_AppCompat_Dialog_Alert)
    }

    private fun openGallery() {
        try {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(
                Intent.createChooser(intent, "Select Image"),
                GALLERY_PICTURE
            )
        } catch (e: Exception) {
            Log.e(TAG, "openCamera: $e")
        }
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, TAKE_PICTURE)
        } catch (e: Exception) {
            Log.e(TAG, "openCamera: $e")
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG, "onActivityResult: ${data?.data}")

        if (requestCode == TAKE_PICTURE) {
            Log.e(TAG, "onActivityResult: PHOTO : ${data?.data}")
            Log.e(TAG, "onActivityResult: Extra : " + data?.extras?.get("data"))
            val photo = data!!.extras!!["data"] as Bitmap?
            imageDialogPicker.getUri(getImageUri(requireContext(),photo!!))
        } else if (requestCode == GALLERY_PICTURE) {
            Log.e(TAG, "onActivityResult: GALLERY : ${data?.data}")
            imageDialogPicker.getUri(data?.data)
        }
        dismiss()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
                askPermissions(requireContext())
            }
        }

    private fun askPermissions(context: Context) {
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            -> {
                Log.e(TAG, "askPermissions: permission.CAMERA PERMISSION_GRANTED")
            }

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            -> {
                Log.e(TAG, "askPermissions: permission.READ_EXTERNAL_STORAGE READ_EXTERNAL_STORAGE")
            }

            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            -> {
                Log.e(
                    TAG,
                    "askPermissions: permission.WRITE_EXTERNAL_STORAGE WRITE_EXTERNAL_STORAGE"
                )
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            ) -> {
            }


            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
            }


            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) -> {
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }

        }
    }

}

interface ImagePickerInterface {
    fun getUri(uri: Uri?)
}

//Convert To URI
fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path: String = MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}

@SuppressLint("Range")
fun getImagePath(context: Context, uri: Uri?): String? {
    var cursor: Cursor? = uri?.let { context.contentResolver.query(it, null, null, null, null) }
    cursor?.moveToFirst()
    var document_id: String? = cursor?.getString(0)
    document_id = document_id?.substring(document_id.lastIndexOf(":") + 1)
    cursor?.close()
    cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null
    )
    cursor?.moveToFirst()
    val path: String? = cursor?.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
    cursor?.close()
    return path
}

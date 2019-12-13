package com.example.home_med

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import java.io.ByteArrayOutputStream
/**
 * A simple [Fragment] subclass.
 */
class CameraPreview : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var intent : Intent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity?.startActivityFromFragment(this, intent, 1)
        return inflater.inflate(R.layout.fragment_camera_preview, container, false)
    }
    override fun onActivityResult(requestCode : Int, resultCode : Int, data : Intent?) {
        try {
            if (requestCode == 1) {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    var bmp : Bitmap = data.getExtras()?.get("data") as Bitmap
                    var stream = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    var  byteArray = stream.toByteArray()
                    // convert byte array to Bitmap
                    var bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.size)
                    view?.findNavController()?.navigate(CameraPreviewDirections.actionCameraPreviewToAddMedication((bitmap)))
                }
            }
        } catch (e : Exception) {
            print("Fuck this shit im out")
        }
    }
}
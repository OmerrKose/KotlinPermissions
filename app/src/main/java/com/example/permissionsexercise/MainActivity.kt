package com.example.permissionsexercise

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonCameraPermission = findViewById<Button>(R.id.buttonCameraPermission)

        buttonCameraPermission.setOnClickListener {
            // Check if the permission is granted before
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            )
                Toast.makeText(this,
                        "Already have permission for the camera and gps",
                        Toast.LENGTH_SHORT
                ).show()
            else {
                // Request Permission
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA,
                                Manifest.permission.ACCESS_FINE_LOCATION),
                        CAMERA_AND_LOCATION_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_AND_LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,
                        "Permission granted for camera and gps",
                        Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this,
                        "You have denied the camera and gps permission",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Permission codes
    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val LOCATION_PERMISSION_CODE = 2
        private const val CAMERA_AND_LOCATION_PERMISSION_CODE = 3
    }
}
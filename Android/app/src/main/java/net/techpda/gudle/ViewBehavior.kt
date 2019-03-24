package net.techpda.gudle

import android.view.View

object ViewBehavior {
    var yPosTabHomeBanner: Float = 0F
    var moveTabHomeBannerTo: () -> Unit = { }

    var heightInitMainActivity: Int = 0
    var heightTabHomeContentItem: Int = 0

    var heightMainActivity: Int = 0
    var adjustMainActivityHeight: () -> Unit = { }

    var decoView: View? = null

}

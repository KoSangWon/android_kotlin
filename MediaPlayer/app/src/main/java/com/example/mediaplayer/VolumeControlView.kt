package com.example.mediaplayer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import java.lang.Math.PI
import kotlin.math.atan2

class VolumeControlView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs) {
    var mx = 0.0f
    var my = 0.0f
    var tx = 0.0f//터치한 x 좌표값
    var ty = 0.0f//터치한 y 좌표값
    var angle = 0.0f

    var listener:VolumeListener?=null

    //내가 만들고 싶은 함수
    public interface VolumeListener{
        public fun onChanged(angle:Float):Unit//반환값은 없다
    }

    public fun setVolumeListener(listener: VolumeListener):Unit{
        this.listener = listener
    }

    fun getAngle(x1:Float, y1:Float):Float {
        mx = x1-(width/2.0f)
        my = (height/2.0f)-y1
        return (atan2(mx, my) * 180.0f/PI).toFloat()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {//ctrl + O
        if(event != null){
            tx = event.getX(0)//0 -> 첫번째 touch 한 x
            ty = event.getY(0)//0 -> 첫번째 touch 한 y
            angle = getAngle(tx, ty)
            invalidate()//onDraw를 호출하는 함수
            listener?.onChanged(angle)
            return true
        }
        return false
    }

    override fun onDraw(canvas: Canvas?) {//ctrl + O
        canvas?.rotate(angle, width/2.0f, height/2.0f)//null 값이 아니면 회전
        super.onDraw(canvas)
    }
}
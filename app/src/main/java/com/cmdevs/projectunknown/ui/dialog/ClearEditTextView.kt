package com.cmdevs.projectunknown.ui.dialog

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.cmdevs.projectunknown.R

class ClearEditTextView
@JvmOverloads constructor(
    context: Context,
    atrrs: AttributeSet? = null
) : AppCompatEditText(context, atrrs), TextWatcher, View.OnTouchListener,
    View.OnFocusChangeListener {

    val clearDrawable: Drawable

    init {
        clearDrawable = DrawableCompat.wrap(
            ContextCompat.getDrawable(
                context,
                R.drawable.abc_ic_clear_material
            )!!
        )
        clearDrawable.setBounds(0, 0, clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)
        DrawableCompat.setTintList(clearDrawable, hintTextColors)

        setClearButton(false)

        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        addTextChangedListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val x = event?.x?.toInt()
        clearDrawable?.takeIf {
            it.isVisible
        }?.let {
            x?.takeIf {
                it > (width - clearDrawable.intrinsicWidth)
            }?.let {
                event?.action.takeIf { it == MotionEvent.ACTION_UP }?.let {
                    setError(null)
                    setText(null)
                }
            }
            return true
        }

        return false
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) text?.let { setClearButton(it.length > 0) } else setClearButton(false)
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (isFocused) text?.let { setClearButton(it.length > 0) }
    }

    override fun afterTextChanged(s: Editable?) {
        //doSomething
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //doSomething
    }

    fun setClearButton(visible: Boolean) {
        clearDrawable.setVisible(visible, false)
        setCompoundDrawables(null, null, if (visible) clearDrawable else null, null)
    }

}
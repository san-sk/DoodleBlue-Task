package com.san.doodleblue.ui.customview

import android.content.Context
import android.content.ContextWrapper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.san.doodleblue.R
import com.san.doodleblue.databinding.LayoutCounterBinding


class CounterView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs),
    View.OnClickListener {

    private lateinit var onValueChangeListener: CounterViewInterface

    private var binding: LayoutCounterBinding =
        LayoutCounterBinding.inflate(LayoutInflater.from(context), this, true)

    private val mCounterValue = MutableLiveData(0)
    private val mContext: Context = context

    init {
        if (!isInEditMode) {
            setClickListener()
            updateCounterView()
        }
    }

    var counterValue
        get() = mCounterValue.value ?: 0
        set(value) {
            mCounterValue.value = value
        }

    fun setCallBack(listener: CounterViewInterface) {
        onValueChangeListener = listener
    }


    override fun onClick(v: View) {

        when (v.id) {

            R.id.btn_decrease -> {
                if (mCounterValue.value == 0) return
                mCounterValue.value = mCounterValue.value?.minus(1)
            }

            R.id.btn_increase -> {
                if (mCounterValue.value == 20) return
                mCounterValue.value = mCounterValue.value?.plus(1)
            }

            R.id.btn_add -> {
                mCounterValue.value = mCounterValue.value?.plus(1)
            }
        }
    }

    private fun updateCounterView() {
        mCounterValue.observe(mContext.getLifecycleOwner()) {
            binding.tvCounterValue.text = it.toString()
            if (it > 0) {
                binding.btnAdd.visibility = GONE
                binding.counterView.visibility = VISIBLE
            } else {
                binding.btnAdd.visibility = VISIBLE
                binding.counterView.visibility = GONE
            }

            if (this::onValueChangeListener.isInitialized) onValueChangeListener.onValueChange(it)
        }
    }

    private fun setClickListener() {
        binding.btnDecrease.setOnClickListener(this)
        binding.btnIncrease.setOnClickListener(this)
        binding.btnAdd.setOnClickListener(this)
    }


    private fun Context.getLifecycleOwner(): LifecycleOwner {
        return try {
            this as LifecycleOwner
        } catch (exception: ClassCastException) {
            (this as ContextWrapper).baseContext as LifecycleOwner
        }
    }

    fun interface CounterViewInterface {
        fun onValueChange(count: Int)
    }
}
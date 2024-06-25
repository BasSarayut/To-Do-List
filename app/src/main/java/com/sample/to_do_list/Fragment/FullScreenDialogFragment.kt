package com.sample.to_do_list.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.sample.to_do_list.R

class FullScreenDialogFragment : DialogFragment() {
    private lateinit var et_title: EditText
    private lateinit var sub_title: EditText
    private lateinit var btn_add: Button
    private lateinit var btn_cancel: Button

    var onSaveListener: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_task, container, false)
        et_title = view.findViewById(R.id.et_title)
        sub_title = view.findViewById(R.id.sub_title)
        btn_add = view.findViewById(R.id.btn_add)
        btn_cancel = view.findViewById(R.id.btn_cancel)

        btn_add.setOnClickListener {
            val task = et_title.text.toString()
            val subTask = sub_title.text.toString()
            if (task.isNotEmpty()) {
                onSaveListener?.invoke(task)
                onSaveListener?.invoke(subTask)
                dismiss()
            }
        }

        btn_cancel.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    companion object {
        fun newInstance(): FullScreenDialogFragment {
            return FullScreenDialogFragment()
        }
    }

}


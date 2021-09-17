package ru.androidlearning.recipessearch.presentation.fragments.search

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import io.reactivex.rxjava3.subjects.PublishSubject

object RxSearchObservable {
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            publishSubject.onNext(s.toString())
        }
    }

    fun getPublishSubject(): PublishSubject<String> = publishSubject

    fun beginObserveEditTextChanges(textInputEditText: EditText) {
        textInputEditText.addTextChangedListener(textWatcher)
    }

    fun endObserveEditTextChanges(textInputEditText: EditText) {
        textInputEditText.removeTextChangedListener(textWatcher)
    }
}

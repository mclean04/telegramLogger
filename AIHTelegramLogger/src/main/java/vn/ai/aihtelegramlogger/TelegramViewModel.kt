package vn.ai.aihtelegramlogger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import vn.ai.aihtelegramlogger.Services.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.ai.aihtelegramlogger.Services.ApiService

class TelegramViewModel(app: Application) : AndroidViewModel(app) {

    private val compositeDisposable = CompositeDisposable()

    private val _chatInfo = MutableLiveData<ResponseModel>()
    val chatInfo: LiveData<ResponseModel> = _chatInfo

    fun sendMessage(message: String) {
        compositeDisposable.add(
            ApiService.getInstance(getApplication()).sendMessage(message = message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _chatInfo.postValue(it)
                }, {})
        )


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
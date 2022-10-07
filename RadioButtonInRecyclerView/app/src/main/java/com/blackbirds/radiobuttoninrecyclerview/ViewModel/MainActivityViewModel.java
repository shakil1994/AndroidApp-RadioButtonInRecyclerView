package com.blackbirds.radiobuttoninrecyclerview.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.blackbirds.radiobuttoninrecyclerview.Model.UserResponse;
import com.blackbirds.radiobuttoninrecyclerview.Repo.UserRepo;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final UserRepo repo;

    public MainActivityViewModel() {
        repo = new UserRepo();
    }

    public LiveData<List<UserResponse>> getUserList(){
        return repo.getUserResponse();
    }
}

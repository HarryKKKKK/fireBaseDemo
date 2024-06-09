package com.example.firebasetest;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppModel extends ViewModel {
    private static AppModel instance;
//    private final DatabaseDemo db = new DatabaseDemo();
        private final User owner = new User("Harry", 0);
    private final MutableLiveData<Room> curRoom = new MutableLiveData<>();

    public User getOwner() {
        return owner;
    }

    public static AppModel getModel() {
        if (instance == null) {
            instance = new AppModel();
        }
        return instance;
    }

    public LiveData<Room> getCurRoom() {
        return curRoom;
    }

    public void setCurRoom(Room room) {
        curRoom.setValue(room);
    }

}

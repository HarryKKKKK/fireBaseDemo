package com.example.firebasetest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final Game game =new Game.Builder().withName("spaceShip").withPlace("Imperial").withInfo("intro for game1.").withImage(R.mipmap.spaceship).withCapacity(3).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);

        Button searchGame = findViewById(R.id.search_game_btn);
        searchGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room curRoom = new Room(game, new User("Owner", 0), "Id0000");
                AppModel.getModel().setCurRoom(curRoom);
                Intent intent = new Intent(MainActivity.this, GameRoomActivity.class);
                startActivity(intent);
            }
        });


        // button for joining rooms.
        ImageButton joinButton = findViewById(R.id.join_game_btn);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpWindow();
            }
        });

        // debug button
        Button debugBtn = findViewById(R.id.debug_btn);
        debugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("database", "debug btn click.");
//                AppModel.getModel().addGameSnapshot();
            }
        });
    }

    /*
    Create a popUpWindow for joining rooms.
    Can join room by: entering roomId / scan QR code.
     */
    private void showPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.join_window, null);

        // popUpWindow for options to join-in rooms.
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                600, 600,
                true
        );
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button joinRoomScan = popupView.findViewById(R.id.join_room_scan);
        joinRoomScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        // editText and button for entering roomId and find the room.
        EditText roomNumber = popupView.findViewById(R.id.room_id_et);
        Button enterRoom = popupView.findViewById(R.id.enter_room_btn);
        enterRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupWindow.dismiss();
//                try {
////                    boolean isFound = false;
////                    AppModel.getModel().test();
//                    boolean isFound = AppModel.getModel().joinRoom(String.valueOf(roomNumber.getText()));
//                    if (isFound) {
//                        Intent intent = new Intent(MainActivity.this, GameRoomActivity.class);
//                        startActivity(intent);
//                    } else {
//                        // TODO: room not found.
//                    }
//                } catch (NumberFormatException e) {
//                    AppModel.getModel().roomNotFoundView();
//                }
            }
        });

        // click outside to quit the popUp.
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, android.view.MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }

        });

        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}
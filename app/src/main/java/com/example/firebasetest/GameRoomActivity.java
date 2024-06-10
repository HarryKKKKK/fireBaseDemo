package com.example.firebasetest;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class GameRoomActivity extends AppCompatActivity {
    final Game game =new Game.Builder().withName("spaceShip").withPlace("Imperial").withInfo("intro for game1.").withImage(R.mipmap.spaceship).withCapacity(3).build();

    private AppModel model;
    private Room curRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.room_page);

        AppModel.getModel().getCurRoom().observe(this, new Observer<Room>() {
            @Override
            public void onChanged(Room room) {
                Log.e("room", "refreshed in observer");
                updateRoomView(room);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // button for going back.
        Button backButton = findViewById(R.id.room_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // button for inviting players.
        ImageButton joinButton = findViewById(R.id.add_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curRoom.getPlayers().size() == curRoom.getGame().getCapacity()) {
                    return;
                }
                showPopUpWindow();
            }
        });

        // button for starting the game.
        ImageButton startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curRoom.getPlayers().size() == curRoom.getGame().getCapacity()) {
                    // TODO:
//                    AppModel.getModel().startGame();
                    gameStart();
                }
            }
        });

        ImageButton refreshBtn = findViewById(R.id.refresh_button);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("room", "refreshed");
                Room curRoom = new Room(game, new User("Owner", 0), "iD89324");
                AppModel.getModel().setCurRoom(curRoom);
            }
        });
    }

    public void gameStart() {
//        Intent intent = new Intent(GameRoomActivity.this, GameStageActivity.class);
//        startActivity(intent);
    }

    public void updateRoomView(Room room) {
        curRoom = room;
        if (curRoom == null) {
            Log.e("room", "Room in view is null");
        }
        Game game = curRoom.getGame();

        // Display info of the game.
        TextView gameName = findViewById(R.id.room_game_name);
        gameName.setText(game.getName());
        TextView gameInfo = findViewById(R.id.room_game_info);
        gameInfo.setText(game.getInfo(false));
        ImageView imageView = findViewById(R.id.room_game_image);
        imageView.setImageResource(game.getImage());

        updatePlayers();
        updateStartBtn();
        updateRoomTitle();
    }

    private void updatePlayers() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int avatarSize = screenWidth / 6 ;

        GridLayout playersGrid = findViewById(R.id.players_grid);
        playersGrid.removeAllViews();
        Log.e("database", String.valueOf(curRoom.getPlayers().size()));
        Log.e("database", curRoom.getPlayers().toString());
        for (User player : curRoom.getPlayers()) {
            ImageView playerAvatar = new ImageView(this);
            playerAvatar.setImageResource(player.getProfile());

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.width = avatarSize;
            layoutParams.height = avatarSize;
            layoutParams.setMargins(30, 30, 30, 30);
            playerAvatar.setLayoutParams(layoutParams);
            playerAvatar.setScaleType(ImageView.ScaleType.CENTER_CROP);

            playersGrid.addView(playerAvatar);
        }
    }

    private void updateStartBtn() {
        ImageButton startButton = findViewById(R.id.start_button);
        startButton.setSelected(curRoom.getPlayers().size() == curRoom.getGame().getCapacity() &&
                curRoom.getOwner() == AppModel.getModel().getOwner());
    }


    @SuppressLint("DefaultLocale")
    private void updateRoomTitle() {
        TextView roomName = findViewById(R.id.room_title);
        roomName.setText(String.format("%s's Room (%d / %d players)",
                curRoom.getOwner().getName(),
                curRoom.getPlayers().size(),
                curRoom.getGame().getCapacity()));
    }


    private void showPopUpWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.invite_window, null);

        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                600, 600,
                true
        );

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView roomId = popupView.findViewById(R.id.room_id_text);
        roomId.setText(curRoom.getId());

        Button copyButton = popupView.findViewById(R.id.copy_roomId);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Room Id Copied", curRoom.getId());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(GameRoomActivity.this, "Room Id copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

//        ViewUtilities.darkenBg(popupWindow);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
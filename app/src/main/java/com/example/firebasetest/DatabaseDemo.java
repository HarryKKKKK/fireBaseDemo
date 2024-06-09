//package com.example.firebasetest;
//
//import android.content.Intent;
//import android.widget.Toast;
//
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class DatabaseDemo {
//    private final Gson gson = new Gson();
//    private final FirebaseFirestore db;
//    private final Game game =new Game.Builder().withName("spaceShip").withPlace("Imperial").withInfo("intro for game1.").withImage(R.mipmap.spaceship).withGameStageCount(3).withCapacity(3).build();
//    private final Game game2 =new Game.Builder().withName("Game2").withPlace("Harry").withInfo("Caoooo").withImage(0).withGameStageCount(3).withCapacity(4).build();
//    Room curRoom = new Room(game, new User("Owner", 0), "Id0000");
//
//    public DatabaseDemo() {
//        this.db = FirebaseFirestore.getInstance();
//    }
//
//    public void textSnapshot() {
//        DocumentReference docRef = db.collection("MyData").document("Data");
//        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null) {
////                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (value != null && value.exists()) {
////                    updatedTV.setText(value.getData().get("updatedValue").toString());
//                }
//            }
//        });
//    }
//
//    public void listSnapshot() {
//        Map<String, Object> dataMap = new HashMap<>();
//        List<User> userList = new ArrayList<>();
//        userList.add(new User("Person", 1));
//        dataMap.put("pList", userList);
//        DocumentReference listRef = db.collection("MyData").document("List");
//        listRef.set(dataMap);
//        listRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot value, @androidx.annotation.Nullable FirebaseFirestoreException error) {
//                if (error != null) {
////                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (value != null && value.exists()) {
////                    updatedTV.setText(value.getData().get("pList").toString());
//                }
//            }
//        });
//    }
//
//    public void gameSnapshot() {
//        Map<String, Object> gameMap = new HashMap<>();
//        gameMap.put("Game", game);
//        DocumentReference gameRef = db.collection("MyData").document("Game");
//        gameRef.set(gameMap);
//        gameRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@androidx.annotation.Nullable DocumentSnapshot value, @androidx.annotation.Nullable FirebaseFirestoreException error) {
//                if (error != null) {
////                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (value != null && value.exists()) {
////                    updatedTV.setText(value.getData().get("Game").toString());
//                    Gson gson = new Gson();
//                    String gameJson = gson.toJson(value.getData().get("Game"));
//                    Game tempGame = gson.fromJson(gameJson, Game.class);
////                    AppModel.getModel().debugUpdated(new Room(tempGame, new User("Owner", 0), "Id0000"));
////                    updatedTV.setText(tempGame.getInfo());
//
//                    // TODO: change here
//                    if (tempGame.getCapacity() == 4) {
//                        AppModel.getModel().setCurRoom(new Room(tempGame, new User("Owner", 0), "Id0000"));
////                        Intent intent = new Intent(MainActivity.this, GameRoomActivity.class);
////                        startActivity(intent);
//                    }
//                }
//            }
//        });
//    }
//}

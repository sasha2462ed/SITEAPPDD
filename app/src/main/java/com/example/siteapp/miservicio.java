//package com.example.siteapp;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.app.TaskStackBuilder;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.os.IBinder;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.annotation.RequiresApi;
//import androidx.core.app.NotificationCompat;
//import androidx.core.app.NotificationManagerCompat;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class miservicio extends Service {
//    public miservicio() {
//
//    }
//    private static final String CHANNEL_ID = "CHANNEL_ID";
//    private static final String CHANNEL_NAME = "CHANNEL_NAME";
//    private PendingIntent pendingIntent;
//    int count;
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        hilo();
//
//
//        return flags;
//    }
//    /***************************/
//    public void hilo (){
//        try{
//            Thread.sleep(4500);
//            inc();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void inc(){
//
//        String URL = "http://192.168.101.5/conexion_php/item_sugerencia.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
//
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onResponse(String response) {
//
//                if(response.equals("1")) {
//
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                        showNotification();
//                        //Runtime.getRuntime().gc();
//                        //System.gc();
//
//                    } else {
//                        showNewNotification();
//                        //Runtime.getRuntime().gc();
//                        //System.gc();
//                    }
//
//                    nov();
//
//                    Runtime.getRuntime().gc();
//                    System.gc();
//                    onDestroy();
//                    //new time().execute();
//                    startService(new Intent(getApplicationContext(), miservicio1.class ) );
//                    stopService(new Intent(getApplicationContext(), miservicio.class ) );
//                    //onDestroy();
//                    //onLowMemory();
//
//
//                }else{
//                    startService(new Intent(getApplicationContext(), miservicio1.class ) );
//                    stopService(new Intent(getApplicationContext(), miservicio.class ) );
//                    onDestroy();
//                    //onLowMemory();
//                    //new time().execute();
//                    //deleteNotificationChannel();
//                    Runtime.getRuntime().gc();
//                    System.gc();
//                    //onStop();
//                }
//            }
//        },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams () throws AuthFailureError {
//                Map<String,String> parametros = new HashMap<String, String>();
//                // parametros.clear();
//                return parametros;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        //requestQueue.getCache().clear();
//        requestQueue.add(stringRequest);
//        // requestQueue.cancelAll(requestQueue);
//    }
//
//    public void nov (){
//        onDestroy();
//        String URL="http://192.168.101.5/conexion_php/modificar_estado_notificaciones_nov.php";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//
//        }){
//            @Override
//            protected Map<String, String> getParams () throws AuthFailureError {
//                Map<String,String> parametros = new HashMap<String, String>();
//                return parametros;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//    }
//
//    /*
//    public int incc(){
//        String URL = "http://192.168.101.5/conexion_php/item_notificacion.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onResponse(String response) {
//                if(!response.isEmpty()) {
//                    try {
//                        JSONArray object= null;
//                        object = new JSONArray(response);
//                        Log.i("result","Data: "+response);
//
//                        for(int i=0;i<object.length();i++) {
//                            JSONObject indicencia = object.getJSONObject(0);
//                            indicencia.getString("CI");
//                            int itemn = Integer.parseInt(indicencia.getString("CI").toString());
//                            Log.i("resultm", String.valueOf(itemn));
//                            count = itemn;
//
//
//                            if(count==0){
//
//                                startService(new Intent(getApplicationContext(), miservicio.class ) );
//
//
//                            }else{
//                                stopService(new Intent( getApplicationContext(), miservicio.class ) );
//
//                            }
//
//                            if (Objects.equals(String.valueOf(itemn),"0")) {
//                                deleteNotificationChannel();
//                            } else {
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                                    showNotification();
//                                } else {
//                                    showNewNotification();
//                                }
//                            }
//                        }
//                    }
//                    catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                }
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//        }){
//            @Override
//            protected Map<String, String> getParams () throws AuthFailureError {
//                Map<String,String> parametros = new HashMap<String, String>();
//                return parametros;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//
//        return count;
//
//    }
//
//     */
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void showNotification() {
//
//        NotificationChannel CHANNEL = new NotificationChannel (CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.createNotificationChannel(CHANNEL);
//        showNewNotification();
//
//    }
//
//    private void showNewNotification() {
//
//        setPendingIntent(interfaz_aviso.class);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_notification_add_black_24dp)
//                .setContentTitle("Usted tiene notificaciones pendientes")
//                .setContentText("")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent);
//        builder.setAutoCancel(true);
//        builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
//        managerCompat.notify( 1, builder.build());
//    }
//
//
//    private void setPendingIntent(Class<?> clsActivity){
//
//        Intent intent = new Intent(this, clsActivity);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(clsActivity);
//        stackBuilder.addNextIntent(intent);
//        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
//
//    }
/////*******//////
//
//    public void deleteNotificationChannel(){
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        // The id of the channel.
//        String id = "CHANNEL_ID";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationManager.deleteNotificationChannel(id);
//        } else {
//            NotificationManager manager = ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE));
//            manager.cancelAll();
//
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        stopService(new Intent(getApplicationContext(), miservicio.class ) );
//    }
//
//    private void finishAffinity() {
//        finishAffinity();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//}
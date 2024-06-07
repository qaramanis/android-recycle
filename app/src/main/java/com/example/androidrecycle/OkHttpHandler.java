package com.example.androidrecycle;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;

public class OkHttpHandler {
    private static final String BASE_URL = "http://192.168.1.198/recycleService/";
    private final OkHttpClient client = new OkHttpClient();

    private JSONObject postRequest(String url, RequestBody body) throws IOException, JSONException {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String resp = response.body().string();
            System.out.println("Response: " + resp);
            return new JSONObject(resp);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public JSONObject login(String username, String password) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        return postRequest(BASE_URL + "login.php", body);
    }

    public JSONObject register(String username, String password, int role) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("role", String.valueOf(role))
                .build();
        return postRequest(BASE_URL + "register.php", body);
    }

    public JSONObject addPoints(int userId, int paper, int glass, int aluminum) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .add("paper", String.valueOf(paper))
                .add("glass", String.valueOf(glass))
                .add("aluminum", String.valueOf(aluminum))
                .build();
        return postRequest(BASE_URL + "addPoints.php", body);
    }

    public JSONObject subtractPoints(int userId, int pointsToSubtract) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .add("pointsToSubtract", String.valueOf(pointsToSubtract))
                .build();
        return postRequest(BASE_URL + "subtractPoints.php", body);
    }

    public JSONObject approveRejectRequest(int requestId, boolean approve) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("requestId", String.valueOf(requestId))
                .add("approve", String.valueOf(approve))
                .build();
        return postRequest(BASE_URL + "approveRejectRequest.php", body);
    }

    public JSONObject getAllRequests() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "getAllRequests.php")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getMyRequests(int userId) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .build();
        return postRequest(BASE_URL + "getMyRequests.php", body);
    }

    public JSONObject makeRequest(int rewardId, int userId) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("rewardId", String.valueOf(rewardId))
                .add("userId", String.valueOf(userId))
                .build();
        return postRequest(BASE_URL + "makeRequest.php", body);
    }

    public JSONObject getMyPointsHistory(int userId) throws IOException, JSONException {
        RequestBody body = new FormBody.Builder()
                .add("userId", String.valueOf(userId))
                .build();
        return postRequest(BASE_URL + "getMyPointsHistory.php", body);
    }

    public JSONObject getTopRecyclers() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "getTopRecyclers.php")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getAllUsers() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL + "getAllUsers.php")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return new JSONObject(response.body().string());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
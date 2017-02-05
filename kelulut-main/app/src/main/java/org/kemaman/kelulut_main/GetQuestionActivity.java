package org.kemaman.kelulut_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kemaman.kelulut_main.dto.QuestionDTO;
import org.kemaman.kelulut_main.utils.AppConfig;
import org.kemaman.kelulut_main.utils.AppController;
import java.util.ArrayList;
import java.util.HashMap;

public class GetQuestionActivity extends AppCompatActivity {
    private static final String TAG = GetQuestionActivity.class.getSimpleName();
    ArrayList<QuestionDTO> listQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getQuestion();
    }


    public void getQuestion(){

        HashMap<String, String> mRequestParams = new HashMap<String, String>();
        mRequestParams.put("approved","true");

        JSONArray dummy = new JSONArray ();

        // Tag used to cancel the request
        String tag_string_getQuestion = "req_getQuestion";

        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, AppConfig.URL_GET_QUESTION,
                dummy,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Response: " + response.toString());

                        ArrayList<QuestionDTO> listQuestion = null;

                        try {
                            for (int i = 0; i < response.length(); ++i) {
                                JSONObject rec = response.getJSONObject(i);

                                // get members of the question object
                                int id = rec.getInt("id");
                                String question = rec.getString("question");
                                String imgLocation = rec.getString("img_location");
                                String optA = rec.getString("answer1");
                                String optB = rec.getString("answer2");
                                String optC = rec.getString("answer3");
                                String optD = rec.getString("answer4");
                                String answer = rec.getString("correct_answer");


                                QuestionDTO questionDTO = new QuestionDTO(id, question, imgLocation,
                                                                optA, optB, optC, optD, answer);
                                listQuestion.add(questionDTO);
                            }

                            if (listQuestion != null){
                                Intent intent = new Intent(GetQuestionActivity.this, ExamActivity.class);
                                intent.putExtra("question_list", listQuestion);
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "onErrorResponse: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_string_getQuestion);
    }

}

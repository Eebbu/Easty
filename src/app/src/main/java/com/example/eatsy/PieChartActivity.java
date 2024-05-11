package com.example.eatsy;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.myPieChart);

    }







        private void setupPieChart() {

            pieChart.setUsePercentValues(true);
            pieChart.setEntryLabelTextSize(12);
            pieChart.setEntryLabelColor(Color.BLACK);
            pieChart.setCenterText("Post Type Distribution");
            pieChart.setCenterTextSize(24);
            pieChart.getDescription().setEnabled(false);

            Legend legend = pieChart.getLegend();
            legend.setTextSize(12);
            legend.setForm(Legend.LegendForm.CIRCLE);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        }

        private void loadPieChartData() {
            HashMap<String, Integer> typeCounts = new HashMap<>();
            typeCounts.put("donate", 0);
            typeCounts.put("exchange", 0);
            typeCounts.put("wanted", 0);

            try {
                InputStream is = getResources().openRawResource(R.raw.posts); // replace json_file with your file's name
                Scanner scanner = new Scanner(is);
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    builder.append(scanner.nextLine());
                }

                JSONArray jsonArray = new JSONArray(builder.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String postType = jsonObject.getString("post_type");
                    typeCounts.put(postType, typeCounts.get(postType) + 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<PieEntry> entries = new ArrayList<>();
            for (String type : typeCounts.keySet()) {
                entries.add(new PieEntry(typeCounts.get(type), type));
            }

            PieDataSet dataSet = new PieDataSet(entries, "Post Types");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setSliceSpace(3f);
            dataSet.setSelectionShift(5f);

            PieData data = new PieData(dataSet);
            data.setDrawValues(true);
            data.setValueTextSize(12f);
            data.setValueTextColor(Color.BLACK);


            pieChart.setData(data);
            pieChart.invalidate();
        }
//        Map<String, Integer> typeCounts = new HashMap<>();
//
//        try {
//            InputStream is = getResources().openRawResource(R.raw.posts); // make sure your file is named correctly
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            String json = new String(buffer, "UTF-8");
//
//            JSONArray jsonArray = new JSONArray(json);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String postType = jsonObject.getString("post_type");
//                typeCounts.put(postType, typeCounts.getOrDefault(postType, 0) + 1);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        List<PieEntry> entries = new ArrayList<>();
//        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
//            Log.d("PieData", "Type: " + entry.getKey() + ", Count: " + entry.getValue());
//            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
//        }
//
//        PieDataSet dataSet = new PieDataSet(entries, "Post Types");
//        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        PieData data = new PieData(dataSet);
//        pieChart.setData(data);
//        pieChart.invalidate(); // refresh the pie chart

}

package vn.com.recpic.HomeScreen.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import vn.com.recpic.R;


/**
 * Created by Administrator on 14/12/2016.
 */

public class ExpenseFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener{

    private PieChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    public  ExpenseFragment(){

    }

    public static ExpenseFragment newInstance(){
        ExpenseFragment fragment = new ExpenseFragment();
        return  fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, parent, false);
        init(view);
        return view;
    }

    private void init(View view){
       // tvX = (TextView) view.findViewById(R.id.tvXMax);
       // tvY = (TextView) view.findViewById(R.id.tvYMax);

        //mSeekBarX = (SeekBar) view.findViewById(R.id.seekBar1);
       // mSeekBarY = (SeekBar) view.findViewById(R.id.seekBar2);

        mChart = (PieChart) view.findViewById(R.id.chart);

        mChart.setCenterText(generateCenterSpannableText());
        mChart.setUsePercentValues(true);

        //set Hole Piechart center
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        //Set dememsion pie chart
        mChart.setHoleRadius(68f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        //mChart.animateY(1400);
        mChart.invalidate();


        //mSeekBarX.setOnSeekBarChangeListener(this);
       // mSeekBarY.setOnSeekBarChangeListener(this);

        setData(4,10);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //tvX.setText("" + (mSeekBarX.getProgress()));
        //tvY.setText("" + (mSeekBarY.getProgress()));

        //setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onValueSelected(Entry entry, int i, Highlight highlight) {

    }

    @Override
    public void onNothingSelected() {

    }
//set centerText PieChart
    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("Recpic\ndeveloped by Tan Le");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 6, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 6, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 6, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 6, s.length() - 7, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 6, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 6, s.length(), 0);
        return s;
    }

    private void setData(int count, float range) {
        float mult = range;

        ArrayList<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 0));
        entries.add(new Entry(6f, 0));
        entries.add(new Entry(12f, 0));
        entries.add(new Entry(18f, 0));
        entries.add(new Entry(9f, 0));
 /*
        for(int i =0; i < count; i++){
            entries.add(new Entry(count[i], i);
        }

        for (int i = 0; i < count + 1 ; i++) {
            entries.add(new Entry((float) ((Math.random() * mult) + mult / 5), i));
        }
*/
        PieDataSet pieDataSet = new PieDataSet(entries, "#");

        Legend legend = mChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setXOffset(0f);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("Febuary");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        PieData pieData = new PieData(labels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        mChart.setDescription("Description");
        mChart.setData(pieData);

        mChart.animateY(500);
        mChart.saveToGallery("/sd/mychart.jpg", 85);

    }
}

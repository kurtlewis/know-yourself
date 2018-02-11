package com.kurtjlewis.knowyourself.db;

import com.kurtjlewis.knowyourself.db.entity.FeelingEntity;
import com.kurtjlewis.knowyourself.model.Emotion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
/**
 * Created by kurt on 2/10/18.
 * @author Kurt Lewis
 * @date 2018.02.10
 */

public class DataGenerator {

    public static List<FeelingEntity> generateFeelingEntityList(int numToGenerate) {
        List<FeelingEntity> feelings = new ArrayList<FeelingEntity>();
        Random rdm = new Random();
        Emotion[] emotions = Emotion.values();
        for (int i = 0; i < numToGenerate; i++) {
            FeelingEntity feeling = new FeelingEntity();
            feeling.setEmotion(emotions[rdm.nextInt(emotions.length)]);
            feeling.setIntensity(rdm.nextInt(100));
            feeling.setNotes("This is a mocked Feeling Entity");
            Calendar timestamp = Calendar.getInstance();
            timestamp.setTimeInMillis(timestamp.getTimeInMillis() - rdm.nextInt());
            feeling.setTimestamp(timestamp);
            feelings.add(feeling);
        }
        return feelings;
    }
}

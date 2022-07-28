package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {

    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        double count = 0;
        for (Pupil pupil : pupils) {
            for (Subj subj : pupil.subjects()) {
                sum += subj.score();
                count++;
            }
        }
        return sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subj subj : pupil.subjects()) {
                sum += subj.score();
            }
            labels.add(new Label(pupil.name(), sum / pupil.subjects().size()));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subj subj : pupil.subjects()) {
                if (map.containsKey(subj.name())) {
                    map.put(subj.name(), map.get(subj.name()) + subj.score());
                } else {
                    map.put(subj.name(), subj.score());
                }

            }
        }

        for (String n : map.keySet()) {
            labels.add(new Label(n, (int) (map.get(n) / map.size())));
        }

        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subj subj : pupil.subjects()) {
                sum += subj.score();
                labels.add(new Label(pupil.name(), sum));
            }
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subj subj : pupil.subjects()) {
                if (map.containsKey(subj.name())) {
                    map.put(subj.name(), map.get(subj.name()) + subj.score());
                } else {
                    map.put(subj.name(), subj.score());
                }
            }
        }
        for (String s : map.keySet()) {
            labels.add(new Label(s, map.get(s)));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);

    }
}
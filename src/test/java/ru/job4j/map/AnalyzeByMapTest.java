package ru.job4j.map;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class AnalyzeByMapTest {
    @Test
    public void whenAverageScore() {
        double average = AnalyzeByMap.averageScore(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subj("Math", 100),
                                        new Subj("Lang", 70),
                                        new Subj("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subj("Math", 80),
                                        new Subj("Lang", 90),
                                        new Subj("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subj("Math", 70),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).isCloseTo(74.44, offset(0.01D));
    }

    @Test
    public void whenListOfPupilAverage() {
        List<Label> average = AnalyzeByMap.averageScoreByPupil(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subj("Math", 100),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subj("Math", 80),
                                        new Subj("Lang", 90),
                                        new Subj("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subj("Math", 70),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Label("Ivanov", 80D),
                new Label("Petrov", 80D),
                new Label("Sidorov", 60D)
        ));
    }

    @Test
    public void whenListOfSubjAverage() {
        List<Label> average = AnalyzeByMap.averageScoreBySubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subj("Math", 70),
                                        new Subj("Lang", 90),
                                        new Subj("Philosophy", 100)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subj("Math", 60),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 60)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subj("Math", 80),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(average).hasSameElementsAs(List.of(
                new Label("Math", 70D),
                new Label("Lang", 70D),
                new Label("Philosophy", 70D)
        ));
    }

    @Test
    public void whenBestPupil() {
        Label best = AnalyzeByMap.bestStudent(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subj("Math", 100),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subj("Math", 80),
                                        new Subj("Lang", 80),
                                        new Subj("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subj("Math", 70),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new Label("Ivanov", 240D));
    }

    @Test
    public void whenBestSubj() {
        Label best = AnalyzeByMap.bestSubject(
                List.of(
                        new Pupil("Ivanov",
                                List.of(
                                        new Subj("Math", 100),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 80)
                                )
                        ),
                        new Pupil("Petrov",
                                List.of(
                                        new Subj("Math", 80),
                                        new Subj("Lang", 90),
                                        new Subj("Philosophy", 70)
                                )
                        ),
                        new Pupil("Sidorov",
                                List.of(
                                        new Subj("Math", 70),
                                        new Subj("Lang", 60),
                                        new Subj("Philosophy", 50)
                                )
                        )
                )
        );
        assertThat(best).isEqualTo(new Label("Math", 250D));
    }
}
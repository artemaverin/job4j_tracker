package ru.job4j.queue;

import java.util.Deque;
import java.util.Iterator;

public class ReconstructPhrase {
    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iter = evenElements.iterator();
        for (int i = 0; i < evenElements.size(); i++) {
            if (i % 2 == 0) {
                sb.append(iter.next());
            } else {
                iter.next();
            }
        }
        return sb.toString();
    }

    private String getDescendingElements() {
        StringBuilder sb = new StringBuilder();
        Iterator<Character> iter = descendingElements.descendingIterator();
        for (int i = 0; i < descendingElements.size(); i++) {
            sb.append(iter.next());
        }
        return sb.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}

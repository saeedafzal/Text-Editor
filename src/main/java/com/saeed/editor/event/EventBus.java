package com.saeed.editor.event;

import java.util.*;
import java.util.function.Consumer;

import static java.util.Collections.emptyList;

public class EventBus {

    private static final EventBus INSTANCE = new EventBus();
    private final Map<Event, List<Consumer<?>>> subscribers = new HashMap<>();

    public static EventBus getDefault() {
        return INSTANCE;
    }

    public <T> void subscribe(Event event, Consumer<T> consumer) {
        List<Consumer<?>> consumerList = subscribers.computeIfAbsent(event, k -> new ArrayList<>());
        consumerList.add(consumer);
    }

    public <T> void publish(Event event, T val) {
        //noinspection unchecked,rawtypes
        subscribers.getOrDefault(event, emptyList())
            .forEach(consumer -> ((Consumer) consumer).accept(val));
    }

    public void publish(Event event) {
        publish(event, null);
    }
}

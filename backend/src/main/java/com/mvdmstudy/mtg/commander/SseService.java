package com.mvdmstudy.mtg.commander;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter subscribe() {
        final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));

        return emitter;
    }

    public void sendEvent(final String message) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .data(message).build());
            } catch (final Exception e) {
                emitter.complete();
            }
        });
    }
}

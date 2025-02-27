package priv.ns.ecommerce.notifier;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@SuppressWarnings("all")
public class Notifier extends AbstractEventNotifier {
    protected Notifier(InstanceRepository repository) {
        super(repository);
    }

    /***
     * 实现对事件的通知
     * @param event
     * @param instance
     * @return
     */
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if(event instanceof InstanceStatusChangedEvent){
                log.info("Instance status changed event:[{}],[{}],[{}]",
                        instance.getRegistration().getName(),event.getInstance(),
                        ((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
            }else{
                log.info("Instance info:[{}],[{}],[{}]",
                        instance.getRegistration().getName(), event.getInstance(), event.getType());
            }
        });
    }
}

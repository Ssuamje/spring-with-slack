package club.slack.bot.event;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SomeEventService {

	private final ApplicationEventPublisher applicationEventPublisher;

	public void publishEvent() {
		System.out.println("서비스에서 이벤트를 발행하였다~~!");
		applicationEventPublisher.publishEvent(
			OverdueSlackEvent.builder()
				.intraId("sanan")
				.expiredAt(LocalDateTime.now())
				.build());
	}
}

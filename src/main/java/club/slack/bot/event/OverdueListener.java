package club.slack.bot.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OverdueListener {

	@EventListener
	public void sendOverdueMessage(OverdueSlackEvent event) {
		System.out.println("리스너에서 이벤트를 받았다~~!");
		System.out.println("event = " + event);
	}
}

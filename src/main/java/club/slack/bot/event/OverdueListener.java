package club.slack.bot.event;

import club.slack.bot.slackbot.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OverdueListener {

	private final SlackService slackService;

	@EventListener
	public void sendOverdueMessage(OverdueSlackEvent event) {
		System.out.println("리스너에서 이벤트를 받았다~~!");
		slackService.sendSlackDirectMessage(
				event.getIntraId()+ " 이 친구야.. 조심해라..",
				"side2850@naver.com");
	}
}

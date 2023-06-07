package club.slack.bot.slackbot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SlackTestController {

	private final SlackService slackService;

	@GetMapping("/slack/error")
	public void sendSlackErrorMessage(){
		log.info("Slack 에러 메시지 전송");
		slackService.sendSlackMessage("에러 발생", "error");
	}

	@GetMapping("/slack/batch")
	public void sendSlackBatchMessage(){
		log.info("Slack 배치 메시지 전송");
		slackService.sendSlackMessage("배치 실행", "batch");
	}

	@GetMapping("/slack/email/{email}")
	public String getSlackIdByEmail(@PathVariable String email){
		log.info("Slack 이메일로 아이디 조회");
		return slackService.getSlackNameByEmail(email);
	}

}

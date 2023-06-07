package club.slack.bot.slackbot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlackChannelPropertiesTest {

	@Autowired
	private SlackProperties slackProperties;
	@Test
	void 프로퍼티_확인() {
		System.out.println("slackProperties.getErrorName() = " + slackProperties.getErrorChannelName());
		System.out.println("slackProperties.getBatchName() = " + slackProperties.getBatchChannelName());
	}
}
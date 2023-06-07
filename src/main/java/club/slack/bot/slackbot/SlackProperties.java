package club.slack.bot.slackbot;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SlackProperties {
	@Value("${slack.token}")
	private String slackToken;

	@Value("${slack.channels.error[0].name}")
	private String errorChannelName;

	@Value("${slack.channels.batch[0].name}")
	private String batchChannelName;
}

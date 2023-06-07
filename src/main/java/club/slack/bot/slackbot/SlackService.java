package club.slack.bot.slackbot;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlackService {

	@Value("${slack.token}")
	String slackToken;

	public void sendSlackMessage(String message, String channel){

		String channelAddress = "";

		// 채널 값을 전달받아 올바른 슬랙채널로 분기
		if(channel.equals("error")){
			channelAddress = SlackConstant.ERROR_CHANNEL;
		} else if(channel.equals("batch")){
			channelAddress = SlackConstant.BATCH_CHANNEL;
		}

		try{
			MethodsClient methods = Slack.getInstance().methods(slackToken);

			ChatPostMessageRequest request = ChatPostMessageRequest.builder()
					.channel(channelAddress)
					.text(message)
					.build();

			methods.chatPostMessage(request);

			log.info("Slack " + channel + " 에 메시지 보냄");
		} catch (SlackApiException | IOException e) {
			log.error(e.getMessage());
		}
	}
}

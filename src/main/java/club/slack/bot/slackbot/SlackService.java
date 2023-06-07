package club.slack.bot.slackbot;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlackService {

	private final SlackProperties properties;

	public void sendSlackMessage(String message, String channel){

		String channelAddress = "";

		// 채널 값을 전달받아 올바른 슬랙채널로 분기
		if(channel.equals("error")){
			channelAddress = properties.getErrorChannelName();
		} else if(channel.equals("batch")){
			channelAddress = properties.getBatchChannelName();
		}

		try{
			MethodsClient methods = Slack.getInstance().methods(properties.getSlackToken());

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

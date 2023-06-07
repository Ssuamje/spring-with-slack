package club.slack.bot.slackbot;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.User;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SlackService {

	private final SlackProperties properties;

	//realName이 Slack의 Nickname이다.
	public String getSlackNameByEmail(String email){
		String slackId = "";

		try{
			MethodsClient methods = Slack.getInstance().methods(properties.getSlackToken());

			User user = methods.usersLookupByEmail(r -> r.email(email)).getUser();

			slackId = "<@" + user.getRealName() + ">";
		} catch (SlackApiException | IOException e) {
			log.error(e.getMessage());
		}

		return slackId;
	}

	public void sendSlackDirectMessage(String message, String email){
		try{
			MethodsClient methods = Slack.getInstance().methods(properties.getSlackToken());

			User user = methods.usersLookupByEmail(r -> r.email(email)).getUser();

			ChatPostMessageRequest request = ChatPostMessageRequest.builder()
					.channel(user.getId())
					.text(message)
					.build();

			methods.chatPostMessage(request);

			log.info("Slack DM 에 메시지 보냄");
		} catch (SlackApiException | IOException e) {
			log.error(e.getMessage());
		}
	}

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

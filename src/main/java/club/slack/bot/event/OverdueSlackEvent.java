package club.slack.bot.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class OverdueSlackEvent{

	private final String intraId;
	private LocalDateTime expiredAt;
}

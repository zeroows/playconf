package actors.messages;

import models.Proposal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public final class RandomlySelectTalkEvent implements UserEvent {

    private final Proposal submission;

    public RandomlySelectTalkEvent(Proposal s) {
        this.submission = s;
    }

    public JsonNode json() {
        final ObjectNode result = Json.newObject();
        result.put("messageType", "proposalSubmission");
        result.put("title", submission.title);
        result.put("proposal", submission.proposal);
        result.put("name", submission.speaker.name);
        result.put("pictureUrl", submission.speaker.pictureUrl);
        result.put("twitterId", submission.speaker.twitterId);
        return result;
    }
}

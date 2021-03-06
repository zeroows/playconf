package views;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static helpers.TestSetup.*;
import models.Speaker;
import models.Proposal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import play.api.templates.Html;
import play.mvc.Http.Context;

public class IndexViewTest {

    //test render home page
    @Test
    public void renderHomePage() {
        running(fakeApplication(testGlobalSettings()), new Runnable() {
            @Override
            public void run() {
                Context.current.set(testHttpContext());
                Speaker speaker = new Speaker();
                speaker.name = "Nilanjan";
                speaker.twitterId = "nraychaudhuri";
                speaker.pictureUrl = "picture url";

                Proposal s = new Proposal();
                s.title = "No Work just Play";
                s.proposal = "This is the description";
                s.speaker = speaker;
                Html html = views.html.index.render(s);
                assertThat(contentType(html)).isEqualTo("text/html");
                //parsing html so that we can jquery type selector to test each element
                Document doc = Jsoup.parse(contentAsString(html));
                assertThat(doc.select("#title").text()).isEqualTo("Keynote - " + s.title);
                assertThat(doc.select("#speakerName").text()).isEqualTo(speaker.name);
            }
        });
    }
}

package it.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Set;

import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.hascode.Task;
import com.hascode.parser.SingleLineDslParser;

public class SingleLineParserTest {
	SingleLineDslParser parser = Parboiled
			.createParser(SingleLineDslParser.class);

	@Test
	public void shouldParseMultipleLabels() throws Exception {
		String input = "labels:\"foo,bar,baz\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Labels()).run(input);
		Set<String> labels = ((Task) result.resultValue).labels();
		assertThat(labels.size(), equalTo(3));
		assertThat(labels, containsInAnyOrder("foo", "bar", "baz"));
	}

	@Test
	public void shouldParseSummary() throws Exception {
		String input = "- This is a first task";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Summary()).run(input);
		Task task = (Task) result.resultValue;
		assertThat(task.summary(), equalTo("This is a first task"));
	}

	@Test
	public void shouldParseAssignee() throws Exception {
		String input = "assignee:\"tim\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Assignee()).run(input);
		Task task = (Task) result.resultValue;
		assertThat(task.assignee(), equalTo("tim"));
	}

	@Test
	public void shouldParseCompleteLine() throws Exception {
		String input = "- This is a first task| assignee:\"tim\" labels:\"foo,bar,baz\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(parser.Task())
				.run(input);
		Task task = (Task) result.resultValue;
		assertThat(task.summary(), equalTo("This is a first task"));
		assertThat(task.assignee(), equalTo("tim"));
		Set<String> labels = task.labels();
		assertThat(labels.size(), equalTo(3));
		assertThat(labels, containsInAnyOrder("foo", "bar", "baz"));
	}
}

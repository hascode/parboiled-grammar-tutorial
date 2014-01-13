package com.hascode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.hascode.parser.CompleteDslParser;

public class CompleteDslParserTest {
	CompleteDslParser parser = Parboiled.createParser(CompleteDslParser.class);

	@Test
	public void shouldParseMultipleLines() throws Exception {
		String line1 = String
				.format("- A first task / assignee:\"joe\" labels:\"foo,bar,baz\"");
		String line2 = "- This is the second entry / assignee:\"fred\" labels:\"beer,wine\"";
		String line3 = "- And a third entry";
		String dslString = line1 + "\n" + line2 + "\n" + line3;
		ParsingResult<TaskList> result = new RecoveringParseRunner<TaskList>(
				parser.Tasks()).run(dslString);
		TaskList taskList = result.resultValue;
		List<Task> tasks = taskList.tasks();
		assertThat(tasks.size(), equalTo(3));

		Task task1 = tasks.get(0);
		assertThat(task1.summary(), equalTo("A first task "));
		assertThat(task1.assignee(), equalTo("joe"));
		Set<String> task1Labels = task1.labels();
		assertThat(task1Labels, containsInAnyOrder("foo", "bar", "baz"));

		Task task2 = tasks.get(1);
		assertThat(task2.summary(), equalTo("This is the second entry "));
		assertThat(task2.assignee(), equalTo("fred"));
		Set<String> task2Labels = task2.labels();
		assertThat(task2Labels, containsInAnyOrder("beer", "wine"));

		Task task3 = tasks.get(2);
		assertThat(task3.summary(), equalTo("And a third entry"));
		assertThat(task3.assignee(), nullValue());
		Set<String> task3Labels = task3.labels();
		assertThat(task3Labels.isEmpty(), equalTo(true));
	}
}

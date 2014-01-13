package com.hascode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Set;

import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

import com.hascode.parser.SingleLineDslParser;

public class LabelRuleTest {
	SingleLineDslParser parser = Parboiled
			.createParser(SingleLineDslParser.class);

	@Test
	public void shouldCreateMultipleLabels() throws Exception {
		String input = "labels:\"foo,bar,baz\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Labels()).run(input);
		Set<String> labels = ((Task) result.resultValue).labels();
		assertThat(labels.size(), equalTo(3));
		assertThat(labels, containsInAnyOrder("foo", "bar", "baz"));
	}
}

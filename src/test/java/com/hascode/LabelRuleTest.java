package com.hascode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Set;

import org.junit.Test;
import org.parboiled.Parboiled;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

public class LabelRuleTest {
	DslParser parser = Parboiled.createParser(DslParser.class);

	@Test
	public void shouldCreateMultipleLabels() throws Exception {
		String input = "labels:\"foo,bar,baz\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Labels()).run(input);
		Set<String> labels = ((Task) result.resultValue).labels();
		System.out.println(labels.size() + " labels: " + labels);
		assertThat(labels.size(), equalTo(3));
	}
}

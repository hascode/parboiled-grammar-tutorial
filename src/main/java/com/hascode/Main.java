package com.hascode;

import org.parboiled.Parboiled;
import org.parboiled.errors.ParseError;
import org.parboiled.parserunners.RecoveringParseRunner;
import org.parboiled.support.ParsingResult;

public class Main {
	public static void main(final String[] args) {
		runSimpleLine();
		runMultiLine();
	}

	public static void runSimpleLine() {
		System.out.println("SIMPLE LINE");
		DslParser parser = Parboiled.createParser(DslParser.class);
		String dslString = "- This is just a test / assignee:\"joe\" labels:\"foo,bar, baz\"";
		ParsingResult<?> result = new RecoveringParseRunner<Task>(
				parser.Task()).run(dslString);

		if (result.hasErrors()) {
			System.err.println("\nParse Errors:");
			for (ParseError e : result.parseErrors) {
				System.err.println("\t" + e.getErrorMessage()
						+ ", startIndex: " + e.getStartIndex());
			}
			return;
		}

		Task dto = (Task) result.resultValue;
		System.out.println("success: " + dto);
		System.out.println(dto.labels().size() + " labels:");
		for (String label : dto.labels()) {
			System.out.println("\t" + label);
		}
	}

	private static void runMultiLine() {
		System.out.println("MULTI LINE");
		CompleteDslParser parser = Parboiled
				.createParser(CompleteDslParser.class);
		String line1 = "- This is just a test / assignee:\"joe\" labels:\"foo,bar,baz\"";
		String line2 = "- This is a second entry / assignee:\"fred\" labels:\"beer,wine\"";
		String dslString = line1 + "\n" + line2;
		ParsingResult<TaskList> result = new RecoveringParseRunner<TaskList>(
				parser.Tasks()).run(dslString);

		if (result.hasErrors()) {
			System.err.println("\nParse Errors:");
			for (ParseError e : result.parseErrors) {
				System.err.println("\t" + e.getErrorMessage()
						+ ", startIndex: " + e.getStartIndex());
			}
			return;
		}

		System.out.println(result.parseTreeRoot.toString());
		System.out.println("----------------------------");
		System.out.println("success: " + result.resultValue);
	}

}

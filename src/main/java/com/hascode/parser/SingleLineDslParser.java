package com.hascode.parser;

import org.parboiled.BaseParser;
import org.parboiled.Rule;
import org.parboiled.annotations.BuildParseTree;

import com.hascode.Task;

@BuildParseTree
public class SingleLineDslParser extends BaseParser<Task> {
	Task dto = new Task();

	public Rule Task() {
		return Sequence(Summary(), Optional(Options()), EOI, push(dto));
	}

	public Rule Summary() {
		return Sequence('-', ZeroOrMore(' '), Chars(),
				push(dto.summary(match())));
	}

	public Rule Chars() {
		return OneOrMore(TestNot(OptLim()), TestNot(FieldLim()), ANY);
	}

	public Rule NoCommaChars() {
		return OneOrMore(TestNot(ValSep()), TestNot(OptLim()),
				TestNot(FieldLim()), ANY);
	}

	public Rule Options() {
		return Sequence(OptSep(), Optional(Assignee()), Optional(Labels()));
	}

	public Rule OptSep() {
		return Sequence(OptSp(), OptLim(), OptSp());
	}

	public Rule Assignee() {
		return Sequence(OptSp(), String("assignee"), FieldSep(), FieldLim(),
				Chars(), push(dto.assignee(match())), FieldLim());
	}

	public Rule FieldLim() {
		return Ch('"');
	}

	public Rule OptLim() {
		return Ch('|');
	}

	public Rule FieldSep() {
		return Ch(':');
	}

	public Rule OptSp() {
		return ZeroOrMore(' ');
	}

	public Rule ValSep() {
		return Ch(',');
	}

	public Rule Labels() {
		return Sequence(OptSp(), String("labels"), FieldSep(), FieldLim(),
				OneOrMore(Label()), FieldLim());
	}

	public Rule Label() {
		return Sequence(OptSp(), NoCommaChars(), push(dto.label(match())),
				Optional(ValSep()), OptSp());
	}
}

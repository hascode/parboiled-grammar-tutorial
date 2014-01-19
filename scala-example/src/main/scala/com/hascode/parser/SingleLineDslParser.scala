package com.hascode.parser

import org.parboiled.scala._
import org.parboiled.errors.ErrorUtils
import org.parboiled.errors.ParsingException

sealed abstract class AstNode
case class TasksNode(tasks: List[TaskNode]) extends AstNode
case class TaskNode(summary: SummaryNode) extends AstNode
case class SummaryNode(text: StringNode) extends AstNode
case class StringNode(text: String) extends AstNode

class SingleLineDslParser extends Parser {
  def Tasks: Rule1[AstNode] = rule { oneOrMore(Task) ~~> TasksNode }

  def Task: Rule1[TaskNode] = rule { str("- ") ~ Summary ~~> TaskNode ~ optional(Newline) }

  def Summary: Rule1[SummaryNode] = rule { Chars ~~> SummaryNode }

  def Chars: Rule1[StringNode] = rule { oneOrMore(Char) ~> StringNode }

  def Char: Rule0 = rule { !anyOf("-") ~ ANY }

  def Newline: Rule0 = rule { str("\n") }

  def parse(input: String): AstNode = {
    val parsingResult = ReportingParseRunner(Tasks).run(input)
    parsingResult.result match {
      case Some(task) => task
      case None => throw new ParsingException("Invalid input:\n" +
        ErrorUtils.printParseErrors(parsingResult))
    }
  }
}

object ParserExample extends App {
  val dslLine = """- A first task
- And a second task
- Finally a third task
"""

  val parsingResult = new SingleLineDslParser().parse(dslLine);
  parsingResult match {
    case tasks: TasksNode => {
      println("Got " + tasks.tasks.length + " tasks:")
      tasks.tasks.foreach(task => println(s"Task: ${task.summary.text.text}"))
    }
    case _ => println("error")
  }
}
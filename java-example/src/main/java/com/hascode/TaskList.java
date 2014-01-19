package com.hascode;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private final List<Task> tasks = new ArrayList<>();

	public TaskList add(final Task task) {
		tasks.add(task);
		return this;
	}

	public List<Task> tasks() {
		return tasks;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TaskList [tasks=");
		builder.append(tasks);
		builder.append("]");
		return builder.toString();
	}
}

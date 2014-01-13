package com.hascode;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	List<Task> tasks = new ArrayList<>();

	TaskList add(final Task task) {
		tasks.add(task);
		return this;
	}

	List<Task> tasks() {
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

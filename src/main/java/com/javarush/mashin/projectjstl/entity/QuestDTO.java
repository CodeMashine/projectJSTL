package com.javarush.mashin.projectjstl.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestDTO {
	private QuestionDTO quest ;
	private List<QuestionDTO> questionList = new ArrayList<>();
	public List<QuestionDTO> getQuestionList() {
		return questionList;
	}
}

package de.hybris.training.core.dao;

import com.training.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionDao {
    List<QuestionModel> getQuestionsAfterDate(Date date);
    List<QuestionModel> getQuestions();
}

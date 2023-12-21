package com.training.questions.populators;

import com.training.questions.data.QuestionData;
import com.training.questions.model.QuestionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class QuestionPopulator implements Populator<QuestionModel, QuestionData> {
    @Override
    public void populate(QuestionModel questionModel, QuestionData questionData) throws ConversionException {
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setQuestionCustomer(questionModel.getCustomer().getName());
        questionData.setAnswer(questionModel.getAnswer());
        questionData.setAnswerCustomer(questionModel.getAnswerCustomer().getName());
    }
}

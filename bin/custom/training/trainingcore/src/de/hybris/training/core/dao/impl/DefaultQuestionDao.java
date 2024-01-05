package de.hybris.training.core.dao.impl;

import de.hybris.training.core.dao.QuestionDao;
import com.training.questions.model.QuestionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Date;
import java.util.List;

public class DefaultQuestionDao implements QuestionDao {
    private FlexibleSearchService flexibleSearchService;

    private static final String SELECT_QUESTIONS_AFTER_DATE = "SELECT {" + QuestionModel.PK + "} "
            + "FROM {" + QuestionModel._TYPECODE + "} "
            + "WHERE {creationtime} > ?date";
    private static final String SELECT_QUESTIONS = "SELECT {" + QuestionModel.PK + "} "
            + "FROM {" + QuestionModel._TYPECODE + "}";

    @Override
    public List<QuestionModel> getQuestionsAfterDate(Date date) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_QUESTIONS_AFTER_DATE);
        query.addQueryParameter("date", date);

        return flexibleSearchService.<QuestionModel>search(query).getResult();
    }

    @Override
    public List<QuestionModel> getQuestions() {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(SELECT_QUESTIONS);

        return flexibleSearchService.<QuestionModel>search(query).getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}

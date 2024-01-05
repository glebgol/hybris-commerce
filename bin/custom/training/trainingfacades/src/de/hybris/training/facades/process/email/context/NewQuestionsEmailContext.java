package de.hybris.training.facades.process.email.context;

import com.training.questions.data.QuestionData;
import com.training.questions.model.QuestionModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.training.core.model.SendNewQuestionsEmailProcessModel;
import org.apache.commons.configuration.Configuration;

import java.util.List;

public class NewQuestionsEmailContext extends AbstractEmailContext<SendNewQuestionsEmailProcessModel> {
    private List<QuestionData> questionDataList;
    private Converter<QuestionModel, QuestionData> questionDataConverter;

    @Override
    public void init(SendNewQuestionsEmailProcessModel businessProcessModel, EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);

        Configuration configuration = getConfigurationService().getConfiguration();
        put(EMAIL, configuration.getString("email"));
        put(DISPLAY_NAME, configuration.getString("display.name"));
        put(FROM_EMAIL, configuration.getString("from.email"));
        put(FROM_DISPLAY_NAME, configuration.getString("from.display.name"));

        setQuestionDataList(Converters.convertAll(businessProcessModel.getQuestionList(), questionDataConverter));
    }

    public void setQuestionDataConverter(Converter<QuestionModel, QuestionData> questionDataConverter) {
        this.questionDataConverter = questionDataConverter;
    }

    public List<QuestionData> getQuestionDataList() {
        return questionDataList;
    }

    public void setQuestionDataList(List<QuestionData> questionDataList) {
        this.questionDataList = questionDataList;
    }

    @Override
    protected BaseSiteModel getSite(SendNewQuestionsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(SendNewQuestionsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(SendNewQuestionsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }
}

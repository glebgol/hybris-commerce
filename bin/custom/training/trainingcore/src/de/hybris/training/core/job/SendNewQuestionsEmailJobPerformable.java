package de.hybris.training.core.job;

import com.training.questions.model.QuestionModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobHistoryModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.CronJobHistoryService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.training.core.dao.QuestionDao;
import de.hybris.training.core.model.SendNewQuestionsEmailProcessModel;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SendNewQuestionsEmailJobPerformable extends AbstractJobPerformable<CronJobModel> {
    private BusinessProcessService businessProcessService;
    private CronJobHistoryService cronJobHistoryService;
    private QuestionDao questionDao;
    private BaseSiteService baseSiteService;

    private static final String SEND_NEW_QUESTIONS_PROCESS = "sendNewQuestionsProcess";

    @Override
    public PerformResult perform(CronJobModel cronJobModel) {
        List<QuestionModel> questionModelList = questionDao
                .getQuestionsAfterDate(getLastDateOfSuccessRunningCronJob(cronJobModel.getCode()));

        if (questionModelList.isEmpty()) {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
        }

        SendNewQuestionsEmailProcessModel sendNewQuestionsEmailProcessModel = businessProcessService
                .createProcess(SEND_NEW_QUESTIONS_PROCESS + System.currentTimeMillis(), SEND_NEW_QUESTIONS_PROCESS);
        sendNewQuestionsEmailProcessModel.setQuestionList(questionModelList);
        setSite(sendNewQuestionsEmailProcessModel);
        modelService.save(sendNewQuestionsEmailProcessModel);

        businessProcessService.startProcess(sendNewQuestionsEmailProcessModel);

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private Date getLastDateOfSuccessRunningCronJob(String cronJobModelCode) {
        return cronJobHistoryService.getCronJobHistoryBy(cronJobModelCode).stream()
                .filter(cronJob -> cronJob.getResult().equals(CronJobResult.SUCCESS))
                .max(Comparator.comparing(CronJobHistoryModel::getEndTime))
                .map(CronJobHistoryModel::getEndTime)
                .orElse(Date.from(Instant.EPOCH));
    }

    private void setSite(SendNewQuestionsEmailProcessModel sendNewQuestionsEmailProcessModel) {
        BaseSiteModel site = baseSiteService.getBaseSiteForUID("electronics");
        sendNewQuestionsEmailProcessModel.setStore(site.getStores().get(0));
        sendNewQuestionsEmailProcessModel.setSite(site);
        sendNewQuestionsEmailProcessModel.setLanguage(site.getDefaultLanguage());
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public void setCronJobHistoryService(CronJobHistoryService cronJobHistoryService) {
        this.cronJobHistoryService = cronJobHistoryService;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }
}

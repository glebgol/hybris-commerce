package com.training.questions.controllers.cms;

import com.training.questions.facade.ProductQuestionsFacade;
import com.training.questions.model.QuestionsCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.data.ProductData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("QuestionsCMSComponentController")
@RequestMapping(value = "/view/QuestionsCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {
    @Resource
    private ProductQuestionsFacade productQuestionsFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        String productCode = getRequestContextData(request).getProduct().getCode();
        ProductData productData = productQuestionsFacade.getProductDataForCode(productCode);
        model.addAttribute("productQuestions", productData.getQuestions());

        model.addAttribute("fontSize", component.getFontSize());
        model.addAttribute("numberOfQuestionsToShow", component.getNumberOfQuestionsToShow());
    }
}

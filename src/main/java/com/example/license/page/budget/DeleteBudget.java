package com.example.license.page.budget;

import com.example.license.data.Budget;
import com.example.license.service.IBudgetService;
import com.example.license.service.ISectionService;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Date;

@MountPath("DeleteBudget")
public class DeleteBudget extends SelectBudget{

    @SpringBean
    private IBudgetService budgetService;
    @SpringBean
    private ISectionService sectionService;

    public DeleteBudget(Budget selectedBudget){
        var budgetNameModel = Model.of("");
        Model<Date> budgetStartDateModel = Model.of();
        Model<Date> budgetEndDateModel = Model.of();
    }
}

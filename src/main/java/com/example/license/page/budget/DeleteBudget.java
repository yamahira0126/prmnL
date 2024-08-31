package com.example.license.page.budget;

import com.example.license.data.Budget;
import com.example.license.data.Section;
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

        var budgetInfoForm = new Form<>("budgetInfo") {
            @Override
            protected void onSubmit() {
                var budgetName = budgetNameModel.getObject();
                Date budgetStartDate = budgetStartDateModel.getObject();
                Date budgetEndDate = budgetEndDateModel.getObject();
                var msg = "送信データ"
                        + budgetName
                        +","
                        + budgetStartDate
                        +","
                        + budgetEndDate;
                System.out.println(msg);

                budgetService.deleteBudget(selectedBudget.getBudgetId());
                setResponsePage(new SelectBudget());
            };
        };
        add(budgetInfoForm);

        var budgetNameField = new TextField<>("budgetName", budgetNameModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                setModelObject(selectedBudget.getBudgetName());
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算名称の選択肢"));
            }
        };
        budgetInfoForm.add(budgetNameField);

        var budgetStartDateField = new DateTextField("budgetStartDate", budgetStartDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedBudget.getBudgetStartDate());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("開始日の選択肢"));
            }
        };
        budgetInfoForm.add(budgetStartDateField);

        var budgetEndDateField = new DateTextField("budgetEndDate", budgetEndDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedBudget.getBudgetEndDate());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("終了日の選択肢"));
            }
        };
        budgetInfoForm.add(budgetEndDateField);

        var feedback = new FeedbackPanel("feedback");
        budgetInfoForm.add(feedback);
    }
}

//http://localhost:8080/MakeBudget
package com.example.license.page;

import com.example.license.page.common.MainMenu;
import com.example.license.service.EmailService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;


import java.util.Calendar;
import java.util.Date;


@MountPath("prt")
public class SelectPrt extends MainMenu {

    @SpringBean
    private EmailService emailService;

    public SelectPrt() {

        Calendar calendar = Calendar.getInstance();
        //入力のためのモデル
        var budgetName2Model = Model.of("");
        var budgetName1Model = Model.of("");
        Model<Date> budgetStartDateModel = Model.of();
        Model<Date> budgetEndDateModel = Model.of();
        Model<Date> budgetReminderDateModel = Model.of();

        Form<Void> budgetInfoForm = new Form<>("budgetInfo") {
            @Override
            protected void onSubmit() {
                System.out.printf("送信データ: %s, %s, %s, %s, %s%n",
                        budgetName1Model.getObject(),
                        budgetName2Model.getObject(),
                        budgetStartDateModel.getObject(),
                        budgetEndDateModel.getObject(),
                        budgetReminderDateModel.getObject());
                setResponsePage(new SelectPrt());
                emailService.sendSimpleEmail(budgetName2Model.getObject(), "test", "TEST");
            }

            @Override
            protected void onValidate() {
                super.onValidate();
                if (budgetEndDateModel.getObject() != null &&
                        budgetStartDateModel.getObject() != null &&
                        budgetEndDateModel.getObject().before(budgetStartDateModel.getObject())) {
                    error("終了日は開始日より後の日付である必要があります。");
                }
            }
        };
        add(budgetInfoForm);

        //budgetName1Fieldのテキストボックスの設定
        var budgetName1Field = new TextField<>("budgetName1", budgetName1Model){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算名称の選択肢"));
            }
        };
        budgetName1Field.setOutputMarkupId(true);

        //budgetName2Fieldのテキストボックスの設定
        var budgetName2Field = new TextField<>("budgetName2", budgetName2Model){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算名称の選択肢"));
            }
        };
        budgetName2Field.setOutputMarkupId(true);

        //budgetName1Fieldのadd
        //非同期処理の設定 budgetName1Fieldを入力をbudgetName2Fieldに反映
        budgetInfoForm.add(budgetName1Field.add(new AjaxFormComponentUpdatingBehavior("input") {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                String inputText = budgetName1Model.getObject();
                budgetName2Model.setObject(inputText);
                ajaxRequestTarget.add(budgetName2Field); // Update the output field
            }
        }));

        //budgetName2Fieldのadd
        budgetInfoForm.add(budgetName2Field);


        var budgetStartDateField = new DateTextField("budgetStartDate", budgetStartDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("開始日の選択肢"));
            }
        };
        budgetStartDateField.setOutputMarkupId(true);
        budgetInfoForm.add(budgetStartDateField);

        var budgetEndDateField = new DateTextField("budgetEndDate", budgetEndDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("終了日の選択肢"));
            }
        };
        budgetEndDateField.setOutputMarkupId(true);
        var budgetReminderDateField = new DateTextField("budgetReminderDate", budgetReminderDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("終了日の選択肢"));
            }
        };
        budgetReminderDateField.setOutputMarkupId(true);
        budgetStartDateField.add(new AjaxFormComponentUpdatingBehavior("input") {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                Date budgetStartDate = budgetStartDateModel.getObject();
                calendar.setTime(budgetStartDate);
                calendar.add(Calendar.YEAR, 1);
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                Date budgetEndDate = calendar.getTime();
                budgetEndDateModel.setObject(budgetEndDate);

                ajaxRequestTarget.add(budgetEndDateField);
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                Date budgetReminderDate = calendar.getTime();
                budgetReminderDateModel.setObject(budgetReminderDate);
                ajaxRequestTarget.add(budgetReminderDateField);
            }
        });
        budgetInfoForm.add(budgetEndDateField);

        budgetEndDateField.add(new AjaxFormComponentUpdatingBehavior("input") {
            @Override
            protected void onUpdate(AjaxRequestTarget ajaxRequestTarget) {
                Date budgetEndDate = budgetEndDateModel.getObject();
                calendar.setTime(budgetEndDate);
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                Date budgetReminderDate = calendar.getTime();
                budgetReminderDateModel.setObject(budgetReminderDate);
                ajaxRequestTarget.add(budgetReminderDateField);
            }
        });
        budgetInfoForm.add(budgetReminderDateField);

        var feedback = new FeedbackPanel("feedback");
        budgetInfoForm.add(feedback);

    }
}

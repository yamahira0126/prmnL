package com.example.license.page.license;


import com.example.license.MySession;
import com.example.license.data.Budget;
import com.example.license.data.Software;
import com.example.license.service.*;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.File;
import java.util.Date;

@MountPath("MakeLicense")
public class MakeLicense extends SelectLicense{

    @SpringBean
    private ILicenseService licenseService;
    @SpringBean
    private ISoftwareService softwareService;
    @SpringBean
    private IBudgetService budgetService;
    @SpringBean
    private ITerminalService terminalService;
    @SpringBean
    private IAccountService accountService;

    public MakeLicense() {

//        var selectionSoftwareModel = LoadableDetachableModel.of(() -> softwareService.findSoftwares(MySession.get().getAccount()));
//        var selectedSoftwareModel = new Model<Software>();
        Model<String> softwareIdModel = Model.of();
        Model<Date> licenseStartDateModel = Model.of(new Date());
        Model<Date> licenseEndDateModel = Model.of(new Date());
        var selectionBudgetModel = LoadableDetachableModel.of(() -> budgetService.findBudgets(MySession.get().getAccount()));
        var selectedBudgetModel = new Model<Budget>();
        Model<String> terminalIdModel = Model.of();
        Model<String> accountIdModel = Model.of();
        Model<String> serialCodeModel = Model.of();
        Model<String> licenseNumberModel = Model.of();
        //Model<File> licenseRemarksModel = Model.of();

        var rendererBudget = new ChoiceRenderer<>("budgetName");
        //var rendererSoftware = new ChoiceRenderer<>("softwareName");

        var licenseInfoForm = new Form<>("licenseInfo") {
            @Override
            protected void onSubmit() {
                var softwareId = softwareIdModel.getObject();
                Date licenseStartDate = licenseStartDateModel.getObject();
                Date licenseEndDate = licenseEndDateModel.getObject();
                var budgetId = selectedBudgetModel.getObject().getBudgetId().toString();
                var terminalId = terminalIdModel.getObject();
                var accountId = accountIdModel.getObject();
                var serialCode = serialCodeModel.getObject();
                var licenseNumber = licenseNumberModel.getObject();

                var msg = "送信データ"
                        + softwareId
                        + licenseStartDate
                        + licenseEndDate
                        + budgetId
                        + terminalId
                        + accountId
                        + serialCode
                        + licenseNumber;
                System.out.println(msg);

                licenseService.registerLicense(softwareId,
                        licenseStartDate,
                        licenseEndDate,
                        budgetId,
                        terminalId,
                        accountId,
                        serialCode,
                        licenseNumber,
                        MySession.get().getAccount());
                setResponsePage(new MakeLicense());
            }
        };
        add(licenseInfoForm);

        var softwareIdField = new TextField<>("softwareId", softwareIdModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算IDの選択肢"));
            }
        };
        licenseInfoForm.add(softwareIdField);

        var licenseStartDateField = new DateTextField("licenseStartDate", licenseStartDateModel, "yyyy-MM-dd"){
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
        licenseInfoForm.add(licenseStartDateField);

        var licenseEndDateField = new DateTextField("licenseEndDate", licenseEndDateModel, "yyyy-MM-dd"){
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
        licenseInfoForm.add(licenseEndDateField);

        var budgetSelection = new DropDownChoice<>("budgetId", selectedBudgetModel, selectionBudgetModel, rendererBudget){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算IDの選択肢"));
            }
        };
        licenseInfoForm.add(budgetSelection);

        var terminalIdField = new TextField<>("terminalId", terminalIdModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("端末IDの選択肢"));
            }
        };
        licenseInfoForm.add(terminalIdField);

        var accountIdField = new TextField<>("accountId", accountIdModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("アカウントIDの選択肢"));
            }
        };
        licenseInfoForm.add(accountIdField);

        var serialCodeField = new TextField<>("serialCode", serialCodeModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("シリアルコードの選択肢"));
            }
        };
        licenseInfoForm.add(serialCodeField);

        var licenseNumberField = new TextField<>("licenseNumber", licenseNumberModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("台数の選択肢"));
            }
        };
        licenseInfoForm.add(licenseNumberField);


    }
}

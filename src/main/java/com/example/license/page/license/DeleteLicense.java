package com.example.license.page.license;


import com.example.license.MySession;
import com.example.license.data.*;
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
import java.util.Optional;

@MountPath("DeleteLicense")
public class DeleteLicense extends SelectLicense{

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
    private FileUploadField fileUploadField;


    public DeleteLicense(License selectedLicense) {

        var softwareList = softwareService.findSoftwares(MySession.get().getAccount());
        var selectionSoftwareModel = LoadableDetachableModel.of(() -> softwareList);
        var selectedSoftwareModel = new Model<Software>();

        Optional<Software> softwareOpt = softwareList.stream()
                .filter(software -> software.getSoftwareId().equals(selectedLicense.getSoftwareId())).findFirst();
        var software = softwareOpt.orElse(null);

        Model<Date> licenseStartDateModel = Model.of(new Date());
        Model<Date> licenseEndDateModel = Model.of(new Date());

        var budgetList = budgetService.findBudgets(MySession.get().getAccount());
        var selectionBudgetModel = LoadableDetachableModel.of(() -> budgetList);
        var selectedBudgetModel = new Model<Budget>();
        Optional<Budget> budgetOpt = budgetList.stream()
                .filter(budget -> budget.getBudgetId().equals(selectedLicense.getBudgetId())).findFirst();
        var budget = budgetOpt.orElse(null);

        var terminalList = terminalService.findTerminals(MySession.get().getAccount());
        var selectionTerminalModel = LoadableDetachableModel.of(() -> terminalList);
        var selectedTerminalModel = new Model<Terminal>();
        Optional<Terminal> terminalOpt = terminalList.stream()
                .filter(terminal -> terminal.getTerminalId().equals(selectedLicense.getTerminalId())).findFirst();
        var terminal = terminalOpt.orElse(null);

        var accountList = accountService.findAccounts(MySession.get().getAccount());
        var selectionAccountModel = LoadableDetachableModel.of(() -> accountList);
        var selectedAccountModel = new Model<Account>();
        Optional<Account> accountOpt = accountList.stream()
                .filter(account -> account.getAccountId().equals(selectedLicense.getAccountId())).findFirst();
        var account = accountOpt.orElse(null);

        Model<String> serialCodeModel = Model.of();
        Model<String> licenseNumberModel = Model.of();
        //Model<File> licenseRemarksModel = Model.of();

        var rendererBudget = new ChoiceRenderer<>("budgetName");
        var rendererSoftware = new ChoiceRenderer<>("softwareName");
        var rendererTerminal = new ChoiceRenderer<>("terminalName");
        var rendererAccount = new  ChoiceRenderer<>("accountName");

        var licenseInfoForm = new Form<>("licenseInfo") {
            @Override
            protected void onSubmit() {
                var softwareId = selectedSoftwareModel.getObject().getSoftwareId();
                Date licenseStartDate = licenseStartDateModel.getObject();
                Date licenseEndDate = licenseEndDateModel.getObject();
                var budgetId = selectedBudgetModel.getObject().getBudgetId();
                var terminalId = selectedTerminalModel.getObject().getTerminalId();
                var accountId = selectedAccountModel.getObject().getAccountId();
                var serialCode = serialCodeModel.getObject();
                var licenseNumber = licenseNumberModel.getObject();
                FileUpload upload = fileUploadField.getFileUpload();


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

                licenseService.deleteLicense(selectedLicense.getLicenseId());
                setResponsePage(new SelectLicense());
            }
        };
        add(licenseInfoForm);

        var softwareSelection = new DropDownChoice<>("softwareId", selectedSoftwareModel, selectionSoftwareModel, rendererSoftware) {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                setModelObject(software);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                //setModelObject(selectedLicense.getSoftwareId());
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("ソフトウェアIDの選択肢"));
            }
        };
        licenseInfoForm.add(softwareSelection);

        var licenseStartDateField = new DateTextField("licenseStartDate", licenseStartDateModel, "yyyy-MM-dd"){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedLicense.getLicenseStartDate());
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
                setModelObject(selectedLicense.getLicenseEndDate());
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
                setModelObject(budget);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("予算IDの選択肢"));
            }
        };
        licenseInfoForm.add(budgetSelection);

        var terminalSelection = new DropDownChoice<>("terminalId", selectedTerminalModel, selectionTerminalModel, rendererTerminal) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(terminal);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("端末IDの選択肢"));
            }
        };
        licenseInfoForm.add(terminalSelection);

        var accountSelection = new DropDownChoice<>("accountId", selectedAccountModel, selectionAccountModel, rendererAccount) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(account);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("アカウントIDの選択肢"));
            }
        };
        licenseInfoForm.add(accountSelection);

        var serialCodeField = new TextField<>("serialCode", serialCodeModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedLicense.getSerialCode());
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
                setModelObject(selectedLicense.getLicenseNumber().toString());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("台数の選択肢"));
            }
        };
        licenseInfoForm.add(licenseNumberField);

        fileUploadField = new FileUploadField("fileUpload");
        licenseInfoForm.add(fileUploadField);
    }
}

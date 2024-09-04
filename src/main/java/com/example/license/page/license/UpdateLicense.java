package com.example.license.page.license;

import com.example.license.MySession;
import com.example.license.data.*;
import com.example.license.service.*;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Date;

@MountPath("UpdateLicense")
public class UpdateLicense extends SelectLicense {

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

    public UpdateLicense(License selectedLicense) {

        var selectionSoftwareModel = LoadableDetachableModel.of(() -> softwareService.findSoftwares(MySession.get().getAccount()));
        var selectedSoftwareModel = new Model<Software>();
        Model<Date> licenseStartDateModel = Model.of(new Date());
        Model<Date> licenseEndDateModel = Model.of(new Date());
        var selectionBudgetModel = LoadableDetachableModel.of(() -> budgetService.findBudgets(MySession.get().getAccount()));
        var selectedBudgetModel = new Model<Budget>();
        var selectionTerminalModel = LoadableDetachableModel.of(() -> terminalService.findTerminals(MySession.get().getAccount()));
        var selectedTerminalModel = new Model<Terminal>();
        var selectionAccountModel = LoadableDetachableModel.of(() -> accountService.findAccounts(MySession.get().getAccount()));
        var selectedAccountModel = new Model<Account>();
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

                licenseService.renewal(selectedLicense.getLicenseId(),
                        softwareId,
                        licenseStartDate,
                        licenseEndDate,
                        budgetId,
                        terminalId,
                        accountId,
                        serialCode,
                        licenseNumber);
                setResponsePage(new SelectLicense());
            }
        };
        add(licenseInfoForm);

        var softwareSelection = new DropDownChoice<>("softwareId", selectedSoftwareModel, selectionSoftwareModel, rendererSoftware) {
            @Override
            protected void onInitialize() {

                super.onInitialize();
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

        var terminalISelection = new DropDownChoice<>("terminalId", selectedTerminalModel, selectionTerminalModel, rendererTerminal) {
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
        licenseInfoForm.add(terminalISelection);

        var accountSelection = new DropDownChoice<>("accountId", selectedAccountModel, selectionAccountModel, rendererAccount) {
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
        licenseInfoForm.add(accountSelection);

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

package com.example.license.page.license;


import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.File;
import java.util.Date;

@MountPath("MakeLicense")
public class MakeLicense extends SelectLicense{
    public MakeLicense() {
        var softwareIdModel = Model.of();
        Model<Date> licenseStartDateModel = Model.of(new Date());
        Model<Date> licenseEndDateModel = Model.of(new Date());
        var budgetIdModel = Model.of();
        var terminalIdModel = Model.of();
        var accountIdModel = Model.of();
        var serialCodeModel = Model.of();
        var licenseNumberModel = Model.of();
        Model<File> licenseRemarksModel = Model.of();

        var licenseInfoForm = new Form<>("licenseInfo") {
            @Override
            protected void onSubmit() {
                var softwareId = softwareIdModel.getObject();
                Date licenseStartDate = licenseStartDateModel.getObject();
                Date licenseEndDate = licenseEndDateModel.getObject();
                var budgetId = budgetIdModel.getObject();
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

                setResponsePage(new MakeLicense());
            }
        };
        add(licenseInfoForm);

        var softwareIdField = new TextField<>("softwareId", softwareIdModel) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("ソフトウェアIDの選択肢"));
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

        var budgetIdField = new TextField<>("budgetId", budgetIdModel) {
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
        licenseInfoForm.add(budgetIdField);

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

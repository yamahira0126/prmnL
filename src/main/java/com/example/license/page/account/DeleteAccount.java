package com.example.license.page.account;

import com.example.license.data.Account;
import com.example.license.data.Section;
import com.example.license.service.IAccountService;
import com.example.license.service.ISectionService;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("DeleteAccount")
public class DeleteAccount extends SelectAccount{

    @SpringBean
    private IAccountService accountService;
    @SpringBean
    private ISectionService sectionService;

    public DeleteAccount(Account selectedAccount) {

        //入力のためのモデル
        var accountNameModel = Model.of("");
        var accountPasswordModel = Model.of("");
        var accountMailAddressModel = Model.of("");
        var selectionModel = LoadableDetachableModel.of(() -> sectionService.findSections());
        var selectedModel = new Model<Section>();
        var renderer = new ChoiceRenderer<>("sectionName");
//        //プルダウンじゃないバージョン
//        var accountSectionModel = Model.of("");

        var accountInfoForm = new Form<>("accountInfo") {
            @Override
            protected void onSubmit() {
                var accountName = accountNameModel.getObject();
                var accountPassword = accountPasswordModel.getObject();
                var accountMailAddress = accountMailAddressModel.getObject();
                var section = selectedModel.getObject();
//                //プルダウンじゃないバージョン
//                var accountSection = accountSectionModel.getObject();
                var msg = "送信データ"
                        + accountName
                        +","
                        + accountPassword
                        +","
                        + accountMailAddress
                        +","
                        + section;
//                        //プルダウンじゃないバージョン
//                        + accountSection;
                System.out.println(msg);

                accountService.deleteAccount(selectedAccount.getAccountId());
                setResponsePage(new SelectAccount());
            }
        };
        add(accountInfoForm);

        var accountNameField = new TextField<>("accountName", accountNameModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedAccount.getAccountName());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("アカウント名の選択肢"));
            }
        };
        accountInfoForm.add(accountNameField);

        var accountPasswordField = new TextField<>("accountPassword", accountPasswordModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedAccount.getAccountPass());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("アカウントパスワードの選択肢"));
            }
        };
        accountInfoForm.add(accountPasswordField);

        var accountMailAddressField = new TextField<>("accountMailAddress", accountMailAddressModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedAccount.getAccountMailAddress());
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("アカウントメールアドレスの選択肢"));
            }
        };
        accountInfoForm.add(accountMailAddressField);

        //プルダウン
        var sectionSelection = new DropDownChoice<>("sectionName", selectedModel, selectionModel, renderer) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 必ず空欄の選択肢を用意するように設定
                setNullValid(true);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("課の選択肢"));
            }
        };
        accountInfoForm.add(sectionSelection);

//        //プルダウンじゃないバージョン
//        var accountSectionField = new TextField<>("sectionName", accountSectionModel){
//            @Override
//            protected void onInitialize() {
//                super.onInitialize();
//                setModelObject(accountSectionModel.getObject());
//                setRequired(true);
//                setLabel(Model.of("アカウントパスワードの選択肢"));
//            }
//        };
//        accountInfoForm.add(accountSectionField);

        var feedback = new FeedbackPanel("feedback");
        accountInfoForm.add(feedback);
    }
}

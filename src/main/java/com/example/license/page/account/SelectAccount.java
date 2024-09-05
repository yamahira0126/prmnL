package com.example.license.page.account;

import com.example.license.MySession;
import com.example.license.data.Account;
import com.example.license.data.Section;
import com.example.license.page.common.MainMenu;
import com.example.license.service.IAccountService;
import com.example.license.service.ISectionService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SelectAccount")
public class SelectAccount extends MainMenu {

    @SpringBean
    private IAccountService accountService;
    @SpringBean
    private ISectionService sectionService;

    public  SelectAccount (){

        var accountModel = Model.ofList(accountService.findAccounts());
        var sectionModel = Model.ofList(sectionService.findAccountSections());

        //ListView
        var accountsLV = new ListView<>("accounts", accountModel) {
            //wicket:id="budget"の中身の設定
            @Override
            protected void populateItem(ListItem<Account> listItem) {
                var itemModel = listItem.getModel();
                var account = itemModel.getObject();

                var accountIdModel = Model.of(account.getAccountId());
                var accountIdLabel = new Label("accountId", accountIdModel);
                listItem.add(accountIdLabel);

                var accountNameModel = Model.of(account.getAccountName());
                var accountNameLabel = new Label("accountName", accountNameModel);
                listItem.add(accountNameLabel);

                var accountPasswordModel = Model.of(account.getAccountPass());
                var accountPasswordLabel = new Label("accountPassword", accountPasswordModel);
                listItem.add(accountPasswordLabel);

                var accountMailAddressModel = Model.of(account.getAccountMailAddress());
                var accountMailAddressLabel = new Label("accountMailAddress", accountMailAddressModel);
                listItem.add(accountMailAddressLabel);
            }
        };
        add(accountsLV);

        //課を表示
        var sectionsLV = new ListView<>("sections", sectionModel) {
            @Override
            protected void populateItem(ListItem<Section> listItem) {
                var itemModel = listItem.getModel();
                var section = itemModel.getObject();

                var sectionNameModel = Model.of(section.getSectionName());
                var sectionNameLabel = new Label("accountSectionName", sectionNameModel);
                listItem.add(sectionNameLabel);
            }
        };
        add(sectionsLV);

        var editsLV = new ListView<>("edit", accountModel) {
            @Override
            protected void populateItem(ListItem<Account> listItem) {
                var itemModel = listItem.getModel();
                var account = itemModel.getObject();

                var toUpdateAccount = new Link<>("toUpdateAccount") {
                    @Override
                    public void onClick() {
                        setResponsePage(new UpdateAccount(account));
                    }
                };
                listItem.add(toUpdateAccount);

                var toDeleteAccount = new Link<>("toDeleteAccount") {
                    @Override
                    public void onClick() {
                        setResponsePage(new DeleteAccount(account));
                    }
                };
                listItem.add(toDeleteAccount);
            }
        };
        add(editsLV);

        //MakeAccountページに遷移する
        var toMakeAccount = new BookmarkablePageLink<>("toMakeAccount", MakeAccount.class);
        add(toMakeAccount);
    }
}

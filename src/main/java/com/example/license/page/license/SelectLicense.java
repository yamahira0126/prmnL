package com.example.license.page.license;


import com.example.license.MySession;
import com.example.license.data.*;
import com.example.license.page.budget.SelectBudget;
import com.example.license.page.common.MainMenu;
import com.example.license.service.*;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.text.SimpleDateFormat;
import java.util.Optional;

@MountPath("SelectLicense")
public class SelectLicense extends MainMenu {

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

     public SelectLicense() {

         var licensesModel = Model.ofList(licenseService.findLicenses(MySession.get().getAccount()));
         var softwareList = softwareService.findSoftwares(MySession.get().getAccount());
         var budgetList = budgetService.findBudgets(MySession.get().getAccount());
         var terminalList = terminalService.findTerminals(MySession.get().getAccount());
         var accountList = accountService.findAccounts(MySession.get().getAccount());

         var licensesLV = new ListView<>("licenses", licensesModel) {
             @Override
             protected void populateItem(ListItem<License> listItem) {
                 var itemModel = listItem.getModel();
                 var license = itemModel.getObject();

                 Optional<Software> softwareOpt = softwareList.stream()
                         .filter(software -> software.getSoftwareId().equals(license.getSoftwareId())).findFirst();
                 var softwareName = softwareOpt.orElse(null).getSoftwareName();

                 Optional<Budget> budgetOpt = budgetList.stream()
                         .filter(budget -> budget.getBudgetId().equals(license.getBudgetId())).findFirst();
                 var budgetName = budgetOpt.orElse(null).getBudgetName();

                 Optional<Terminal> terminalOpt = terminalList.stream()
                         .filter(terminal -> terminal.getTerminalId().equals(license.getTerminalId())).findFirst();
                 var terminalName = terminalOpt.orElse(null).getTerminalName();

                 Optional<Account> accountOpt = accountList.stream()
                         .filter(account -> account.getAccountId().equals(license.getAccountId())).findFirst();
                 var accountName = accountOpt.orElse(null).getAccountName();

                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                 var licenseIdModel = Model.of(license.getLicenseId());
                 var licenseIdLabel = new Label("licenseId", licenseIdModel);
                 listItem.add(licenseIdLabel);

                 var softwareNameModel = Model.of(softwareName);
                 var softwareNameLabel = new Label("softwareName", softwareNameModel);
                 listItem.add(softwareNameLabel);

                 var licenseStartDateModel = Model.of(formatter.format(license.getLicenseStartDate()));
                 var licenseStartDateLabel = new Label("licenseStartDate", licenseStartDateModel);
                 listItem.add(licenseStartDateLabel);

                 var licenseEndDateModel = Model.of(formatter.format(license.getLicenseEndDate()));
                 var licenseEndDateLabel = new Label("licenseEndDate", licenseEndDateModel);
                 listItem.add(licenseEndDateLabel);

                 var budgetNameModel = Model.of(budgetName);
                 var budgetNameLabel = new Label("budgetName", budgetNameModel);
                 listItem.add(budgetNameLabel);

                 var terminalNameModel = Model.of(terminalName);
                 var terminalNameLabel = new Label("terminalName", terminalNameModel);
                 listItem.add(terminalNameLabel);

                 var accountNameModel = Model.of(accountName);
                 var accountNameLabel = new Label("accountName", accountNameModel);
                 listItem.add(accountNameLabel);

                 var serialCodeModel = Model.of(license.getSerialCode());
                 var serialCodeLabel = new Label("serialCode", serialCodeModel);
                 listItem.add(serialCodeLabel);

                 var licenseNumberModel = Model.of(license.getLicenseNumber());
                 var licenseNumberLabel = new Label("licenseNumber", licenseNumberModel);
                 listItem.add(licenseNumberLabel);

                 ResourceLink<Void> link = new ResourceLink<>("licenseRemarksData", new PdfDownloadResource(license.getLicenseRemarksData()));
                 link.add(new Label("licenseRemarksName", license.getLicenseRemarksName()));
                 listItem.add(link);

                 var toUpdateLicense = new Link<>("toUpdateLicense") {
                     @Override
                     public void onClick() {
                         setResponsePage(new UpdateLicense(license));
                     }
                 };
                 listItem.add(toUpdateLicense);

                 var toDeleteLicense = new Link<>("toDeleteLicense") {
                     @Override
                     public void onClick() {
                         setResponsePage(new DeleteLicense(license));
                     }
                 };
                 listItem.add(toDeleteLicense);
             }
         };
         add(licensesLV);

         //MakeLicenseページに遷移する
         var toMakeBudget = new BookmarkablePageLink<>("toMakeLicense", MakeLicense.class);
         add(toMakeBudget);
     }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(SelectBudget.class, "../js/sort.js")));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(SelectBudget.class, "../js/paging.js")));
    }
}

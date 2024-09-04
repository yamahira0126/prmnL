package com.example.license.page.license;


import com.example.license.MySession;
import com.example.license.data.License;
import com.example.license.page.common.MainMenu;
import com.example.license.service.ILicenseService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.text.SimpleDateFormat;

@MountPath("SelectLicense")
public class SelectLicense extends MainMenu {

    @SpringBean
    private ILicenseService licenseService;

     public SelectLicense() {

         var licensesModel = Model.ofList(licenseService.findLicenses(MySession.get().getAccount()));

         var licensesLV = new ListView<>("licenses", licensesModel) {
             @Override
             protected void populateItem(ListItem<License> listItem) {
                 var itemModel = listItem.getModel();
                 var license = itemModel.getObject();

                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                 var licenseIdModel = Model.of(license.getLicenseId());
                 var licenseIdLabel = new Label("licenseId", licenseIdModel);
                 listItem.add(licenseIdLabel);

                 var softwareIdModel = Model.of(license.getSoftwareId());
                 var softwareIdLabel = new Label("softwareId", softwareIdModel);
                 listItem.add(softwareIdLabel);

                 var licenseStartDateModel = Model.of(formatter.format(license.getLicenseStartDate()));
                 var licenseStartDateLabel = new Label("licenseStartDate", licenseStartDateModel);
                 listItem.add(licenseStartDateLabel);

                 var licenseEndDateModel = Model.of(formatter.format(license.getLicenseEndDate()));
                 var licenseEndDateLabel = new Label("licenseEndDate", licenseEndDateModel);
                 listItem.add(licenseEndDateLabel);

                 var budgetIdModel = Model.of(license.getBudgetId());
                 var budgetIdLabel = new Label("budgetId", budgetIdModel);
                 listItem.add(budgetIdLabel);

                 var terminalIdModel = Model.of(license.getTerminalId());
                 var terminalIdLabel = new Label("terminalId", terminalIdModel);
                 listItem.add(terminalIdLabel);

                 var accountIdModel = Model.of(license.getAccountId());
                 var accountIdLabel = new Label("accountId", accountIdModel);
                 listItem.add(accountIdLabel);

                 var serialCodeModel = Model.of(license.getSerialCode());
                 var serialCodeLabel = new Label("serialCode", serialCodeModel);
                 listItem.add(serialCodeLabel);

                 var licenseNumberModel = Model.of(license.getLicenseNumber());
                 var licenseNumberLabel = new Label("licenseNumber", licenseNumberModel);
                 listItem.add(licenseNumberLabel);

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
}

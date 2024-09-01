package com.example.license.page.software;


import com.example.license.MySession;
import com.example.license.data.Software;
import com.example.license.page.common.MainMenu;
import com.example.license.service.ISoftwareService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SelectSoftware")
public class SelectSoftware extends MainMenu {

    @SpringBean
    private ISoftwareService softwareService;
    public SelectSoftware(){
        //softwareのデータが入ったリストのモデルを用意
        var softwaresModel = Model.ofList(softwareService.findSoftwares(MySession.get().getAccount()));

        //ListView
        var softwaresLV = new ListView<>("softwares",softwaresModel){
            //wicket:id="software"の中身の設定
            @Override
            protected void populateItem(ListItem<Software> listItem){
                var itemModel = listItem.getModel();
                var software = itemModel.getObject();

                var softwareIdModel = Model.of(software.getSoftwareId());
                var softwareIdLabel = new Label("softwareId", softwareIdModel);
                listItem.add(softwareIdLabel);

                var softwareNameModel = Model.of(software.getSoftwareName());
                var softwareNameLabel = new Label("softwareName", softwareNameModel);
                listItem.add(softwareNameLabel);

                var softwareTypeModel = Model.of(software.getSoftwareType());
                var softwareTypeLabel = new Label("softwareType", softwareTypeModel);
                listItem.add(softwareTypeLabel);

                var totalNumberModel = Model.of(software.getTotalNumber());
                var totalNumberLabel = new Label("totalNumber", totalNumberModel);
                listItem.add(totalNumberLabel);

                var softwareRemarksModel = Model.of(software.getSoftwareRemarks());
                var softwareremarksLabel = new Label("softwareRemarks", softwareRemarksModel);
                listItem.add(softwareremarksLabel);

                var toUpdateSoftware = new Link<>("toUpdateSoftware"){
                    @Override
                    public void onClick() {
                        setResponsePage(new UpdateSoftware(software));
                    }
                };
                listItem.add(toUpdateSoftware);

                var toDeleteSoftware = new Link<>("toDeleteSoftware"){
                    @Override
                    public void onClick() {
                        setResponsePage(new DeleteSoftware(software));
                    }
                };
                listItem.add(toDeleteSoftware);
            }

        };
        add(softwaresLV);

        //MakeSoftwareページに遷移する
        var toMakeSoftware = new BookmarkablePageLink<>("toMakeSoftware", MakeSoftware.class);
        add(toMakeSoftware);
    }
}

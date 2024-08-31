package com.example.license.page.software;

import com.example.license.MySession;
import com.example.license.data.Software;
import com.example.license.page.budget.MakeBudget;
import com.example.license.repository.ISoftwareSectionRepository;
import com.example.license.service.ISectionService;
import com.example.license.service.ISoftwareService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;


@MountPath("MakeSoftware")
public class MakeSoftware extends SelectSoftware {

    @SpringBean
    private ISoftwareService softwareService;
    @SpringBean
    private ISectionService sectionService;
    public MakeSoftware(){
        //入力のためのモデル
        var softwareNameModel = Model.of("");
        var softwareTypeModel = Model.of("");
        var totalNumberModel = Model.of("");
        var softwareRemarksModel = Model.of("");

        var renderer = new ChoiceRenderer<>("softwareName");

        var softwareInfoForm = new Form<>("softwareInfo") {
            @Override
            protected void onSubmit() {
                var softwareName = softwareNameModel.getObject();
                var softwareType = softwareTypeModel.getObject();
                var totalNumber = totalNumberModel.getObject();
                var softwareRemarks = softwareRemarksModel.getObject();

                var msg = "送信データ"
                        + softwareName
                        +","
                        + softwareType
                        +","
                        + totalNumber
                        +","
                        + softwareRemarks;
                System.out.println(msg);


                //変更点
                //softwareService.registerSoftware(softwareName, softwareType, totalNumber, softwareRemarks);
                softwareService.registerSoftware(softwareName, softwareType, totalNumber, softwareRemarks, MySession.get().getAccount());
                setResponsePage(new MakeSoftware());
            }
        };
        add(softwareInfoForm);

        var softwareNameField = new TextField<>("softwareName", softwareNameModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("ソフトウェア名称の選択肢"));
            }
        };
        softwareInfoForm.add(softwareNameField);

        var softwareTypeField = new TextField<>("softwareType", softwareTypeModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("タイプの選択肢"));
            }
        };
        softwareInfoForm.add(softwareTypeField);

        var totalNumberField = new TextField<>("totalNumber", totalNumberModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("総ライセンス数の選択肢"));
            }
        };
        softwareInfoForm.add(totalNumberField);

        var softwareRemarksField = new TextField<>("softwareRemarks", softwareRemarksModel);
        softwareInfoForm.add(softwareRemarksField);

        /*プルダウン
        var softwareSelection = new DropDownChoice<>("softwareSelection", selectedModel, selectionModel, renderer){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                //setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("課の選択肢"));
            }
        };
        softwareInfoForm.add(softwareSelection);*/

        var feedback = new FeedbackPanel("feedback");
        softwareInfoForm.add(feedback);
    }
}

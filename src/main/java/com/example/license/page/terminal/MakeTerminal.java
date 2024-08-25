//http://localhost:8080/MakeTerminal

package com.example.license.page.terminal;


import com.example.license.data.Section;
import com.example.license.service.ISectionService;
import com.example.license.service.ITerminalService;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("MakeTerminal")
public class MakeTerminal extends SelectTerminal {
    @SpringBean
    private ITerminalService terminalService;
    @SpringBean
    private ISectionService sectionService;

    public MakeTerminal() {
        //入力のためのモデル
        var terminalNameModel = Model.of("");
        var terminalNumberModel = Model.of("");
        var terminalRemarksModel = Model.of("");


        var selectionModel = LoadableDetachableModel.of(() -> sectionService.findSections());
        var selectedModel = new Model<Section>();

        var renderer = new ChoiceRenderer<>("sectionName");


        var terminalInfoForm = new Form<>("terminalInfo") {
            @Override
            protected void onSubmit() {
                var terminalName = terminalNameModel.getObject();
                var terminalNumber = terminalNumberModel.getObject();
                var terminalRemarks = terminalRemarksModel.getObject();
                var section = selectedModel.getObject();
                var msg = "送信データ"
                        + terminalName
                        +","
                        +terminalNumber
                        +","
                        +terminalRemarks
                        +","
                        +section;
                System.out.println(msg);

                terminalService.registerTerminal(terminalName, terminalNumber, terminalRemarks, section);
                setResponsePage(MakeTerminal.class);
            }
        };
        add(terminalInfoForm);

        var terminalNameField = new TextField<>("terminalName", terminalNameModel){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("端末名称の選択肢"));
            }
        };
        terminalInfoForm.add(terminalNameField);

        var terminalNumberField = new TextField<>("terminalNumber", terminalNumberModel){
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
        terminalInfoForm.add(terminalNumberField);

        var terminalRemarksField = new TextField<>("terminalRemarks", terminalRemarksModel);
        terminalInfoForm.add(terminalRemarksField);


        var sectionSelection = new DropDownChoice<>("sectionName", selectedModel, selectionModel, renderer) {
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 必ず空欄の選択肢を用意するように設定
                setNullValid(true);
                // 空欄の選択肢の送信を許可しないバリデーション
                //setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("課の選択肢"));
            }
        };
        terminalInfoForm.add(sectionSelection);



        var feedback = new FeedbackPanel("feedback");
        terminalInfoForm.add(feedback);

    }
}

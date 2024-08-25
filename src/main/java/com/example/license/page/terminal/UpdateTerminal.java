//http://localhost:8080/UpdateTerminal
package com.example.license.page.terminal;

import com.example.license.data.Section;
import com.example.license.data.Terminal;
import com.example.license.service.ISectionService;
import com.example.license.service.ITerminalService;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UpdateTerminal")
public class UpdateTerminal extends SelectTerminal {
    @SpringBean
    private ITerminalService terminalService;
    @SpringBean
    private ISectionService sectionService;

    public UpdateTerminal(Terminal selectedTerminal) {
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
                var sectionName = selectedModel.getObject().getSectionName();
                var msg = "送信データ"
                        + terminalName
                        +","
                        +terminalNumber
                        +","
                        +terminalRemarks
                        +","
                        +sectionName;
                System.out.println(msg);


                terminalService.renewal(selectedTerminal.getTerminalName(),terminalName, terminalNumber, terminalRemarks);
                setResponsePage(MakeTerminal.class);
            }
        };
        add(terminalInfoForm);

        var terminalNameField = new TextField<>("terminalName", terminalNameModel){
            @Override
            protected void onInitialize() {
                setModelObject(selectedTerminal.getTerminalName());
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
                setModelObject(selectedTerminal.getTerminalNumber());
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("台数の選択肢"));
            }
        };
        terminalInfoForm.add(terminalNumberField);

        var terminalRemarksField = new TextField<>("terminalRemarks", terminalRemarksModel){
            @Override
            protected void onInitialize(){
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                setModelObject(selectedTerminal.getTerminalRemarks());
            }

        };
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

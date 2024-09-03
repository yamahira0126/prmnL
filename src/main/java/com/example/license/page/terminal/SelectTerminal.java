//http://localhost:8080/SelectTerminal

package com.example.license.page.terminal;

import com.example.license.MySession;
import com.example.license.data.Terminal;
import com.example.license.page.common.MainMenu;
import com.example.license.service.ITerminalService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SelectTerminal")
public class SelectTerminal extends MainMenu {

    @SpringBean
    private ITerminalService terminalService;
    public  SelectTerminal() {

        var terminalsModel = Model.ofList(terminalService.findTerminals(MySession.get().getAccount()));

        var terminalsLV = new ListView<>("terminals", terminalsModel) {
            @Override
            protected void populateItem(ListItem<Terminal> listItem) {
                var itemModel = listItem.getModel();
                var terminal = itemModel.getObject();

                var terminalIdModel = Model.of(terminal.getTerminalId());
                var terminalIdLabel = new Label("terminalId", terminalIdModel);
                listItem.add(terminalIdLabel);

                var terminalNameModel = Model.of(terminal.getTerminalName());
                var terminalNameLabel = new Label("terminalName", terminalNameModel);
                listItem.add(terminalNameLabel);

                var terminalNumberModel = Model.of(terminal.getTerminalNumber());
                var terminalNumberLabel = new Label("terminalNumber", terminalNumberModel);
                listItem.add(terminalNumberLabel);

                var terminalRemarksModel = Model.of(terminal.getTerminalRemarks());
                var terminalRemarksLabel = new Label("terminalRemarks", terminalRemarksModel);
                listItem.add(terminalRemarksLabel);

                var toUpdateTerminal = new Link<>("toUpdateTerminal") {
                    @Override
                    public void onClick() {
                        setResponsePage(new UpdateTerminal(terminal));
                    }
                };
                listItem.add(toUpdateTerminal);

                var toDeleteTerminal = new Link<>("toDeleteTerminal") {
                    @Override
                    public void onClick() {
                        setResponsePage(new DeleteTerminal(terminal));
                    }
                };
                listItem.add(toDeleteTerminal);
            }
        };
        add(terminalsLV);

        //MakeTerminalページに遷移する
        var toMakeTerminal = new BookmarkablePageLink<>("toMakeTerminal", MakeTerminal.class);
        add(toMakeTerminal);
    }
}

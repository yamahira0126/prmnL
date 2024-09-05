package com.example.license.page.common;

import com.example.license.MySession;
import com.example.license.page.account.SelectAccount;
import com.example.license.page.budget.SelectBudget;
import com.example.license.page.license.SelectLicense;
import com.example.license.page.software.SelectSoftware;
import com.example.license.page.terminal.SelectTerminal;
import com.example.license.page.saisho.Saisho;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("MainMenu")
public class MainMenu extends WebPage {

    public MainMenu() {
        var toBudget = new BookmarkablePageLink<>("toBudget", SelectBudget.class);
        add(toBudget);
        var toSoftware = new BookmarkablePageLink<>("toSoftware", SelectSoftware.class);
        add(toSoftware);
        var toTerminal = new BookmarkablePageLink<>("toTerminal", SelectTerminal.class);
        add(toTerminal);
        var toHome = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHome);
        var toLicense = new BookmarkablePageLink<>("toLicense", SelectLicense.class);
        add(toLicense);
        var toAccount = new BookmarkablePageLink<>("toAccount", SelectAccount.class);
        add(toAccount);
        var toSaisho = new BookmarkablePageLink<>("toSaisho", Saisho.class);
        add(toSaisho);
        var logout = new Link<>("logout"){
            @Override
            public void onClick() {
                MySession.get().invalidate();
            }
        };
        add(logout);
    }

    @Override
    public void renderHead(IHeaderResponse response){
        super.renderHead(response);
        response.render(CssHeaderItem.forUrl("https://unpkg.com/ress/dist/ress.min.css"));
        response.render(CssHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css"));
        response.render(CssHeaderItem.forReference(new CssResourceReference(MainMenu.class, "../css/kawamuraStyle.css")));
        response.render(JavaScriptHeaderItem.forUrl("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"));
        response.render(JavaScriptHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(MainMenu.class, "../js/scroll.js")));
    }
}

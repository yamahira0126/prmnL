package com.example.license.page.account;

import com.example.license.page.common.MainMenu;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SelectAccount")
public class SelectAccount extends MainMenu {
    public  SelectAccount (){
        //Makeページに遷移する
        var toMake = new BookmarkablePageLink<>("toMake", MakeAccount.class);
        add(toMake);
    }
}

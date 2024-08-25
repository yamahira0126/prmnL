package com.example.license.page.login;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@MountPath("ForgetId")
public class ForgetId extends WebPage{

    public ForgetId(){

        var toLoginLink = new BookmarkablePageLink<>("toLogin", Login.class); //ID,Pass忘れのページ遷移
        add(toLoginLink);

    }

}

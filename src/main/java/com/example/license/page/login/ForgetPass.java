package com.example.license.page.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ForgetPass")
public class ForgetPass extends WebPage{

    public ForgetPass(){

        var toLoginLink = new BookmarkablePageLink<>("toLogin", Login.class);
        add(toLoginLink);

    }

}

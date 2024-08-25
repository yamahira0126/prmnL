package com.example.license.page.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ForgetIdPass")
public class ForgetIdPass extends WebPage{

    public ForgetIdPass(){

        var toLoginLink = new BookmarkablePageLink<>("toLogin", Login.class);
        add(toLoginLink);

    }

}

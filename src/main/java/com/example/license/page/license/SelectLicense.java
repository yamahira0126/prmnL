package com.example.license.page.license;


import com.example.license.page.budget.MakeBudget;
import com.example.license.page.common.MainMenu;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SelectLicense")
public class SelectLicense extends MainMenu {
     public SelectLicense() {

         //MakeLicenseページに遷移する
         var toMakeBudget = new BookmarkablePageLink<>("toMakeLicense", MakeLicense.class);
         add(toMakeBudget);
     }
}

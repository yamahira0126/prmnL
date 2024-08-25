package com.example.license.page.login;

import com.example.license.page.common.HomePage;
import com.example.license.service.IAccountService;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath; //3～5web表示
import org.apache.wicket.markup.html.link.BookmarkablePageLink; //ページ移動
import com.example.license.MySession; //7～9ページ認証
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.markup.html.form.Form; //10～14Spring
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;


@WicketSignInPage
@MountPath("Login")
public class Login extends WebPage{

    // ServiceをIoC/DIする
    @SpringBean
    private IAccountService service;

    public Login(){

        var loginNameModel = Model.of("");
        var loginPassModel = Model.of("");

        Form<Void> loginInfoForm = new Form<>("loginInfo"){
            @Override
            protected void onSubmit(){
                var loginName = loginNameModel.getObject();
                var loginPass = loginPassModel.getObject();
                if(service.existsAccount(loginName, loginPass)){
                    MySession.get().sign(loginName, service.termsFindAccount(loginName, loginPass));
                }
                setResponsePage(new HomePage());
            }
        };
        add(loginInfoForm);

        var loginIdField = new TextField<>("loginName", loginNameModel);
        loginInfoForm.add(loginIdField);

        var loginPassField = new PasswordTextField("loginPass", loginPassModel);
        loginInfoForm.add(loginPassField);

        var toForgetIdLink = new BookmarkablePageLink<>("toForgetId", ForgetId.class); //ID,Pass忘れのページ遷移
        add(toForgetIdLink);

        var toForgetPassLink = new BookmarkablePageLink<>("toForgetPass", ForgetPass.class);
        add(toForgetPassLink);

        var toForgetIdPassLink = new BookmarkablePageLink<>("toForgetIdPass", ForgetIdPass.class);
        add(toForgetIdPassLink);
    }

    @Override
    public void renderHead(IHeaderResponse response){
        super.renderHead(response);
//        response.render(CssHeaderItem.forUrl("https://unpkg.com/ress/dist/ress.min.css"));
//        response.render(CssHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css"));
        response.render(CssHeaderItem.forUrl("https://use.fontawesome.com/releases/v5.6.1/css/all.css"));
        response.render(CssHeaderItem.forReference(new CssResourceReference(Login.class, "Login.css")));
//        response.render(JavaScriptHeaderItem.forUrl("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"));
//        response.render(JavaScriptHeaderItem.forUrl("https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(Login.class, "Login.js")));
    }
}

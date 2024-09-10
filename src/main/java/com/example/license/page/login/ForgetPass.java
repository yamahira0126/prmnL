package com.example.license.page.login;

import com.example.license.service.AccountService;
import com.example.license.service.EmailService;
import com.example.license.service.PasswordService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ForgetPass")
public class ForgetPass extends WebPage{

    @SpringBean
    private EmailService emailService;
    @SpringBean
    private AccountService accountService;
    @SpringBean
    private PasswordService passwordService;

    public ForgetPass(){

        var mailAddressModel = Model.of("");

        var mailAddressInfoForm = new Form<>("mailAddressInfo"){
            @Override
            protected void onSubmit(){
                String mailAddress = mailAddressModel.getObject();
                var msg = "送信データ"
                        + mailAddress;
                System.out.println(msg);

                if(accountService.existsAccount(mailAddress)){
                    String newPassword = passwordService.makePassword();
                    emailService.sendSimpleEmail(mailAddress, "new Password", newPassword);
                    accountService.renewalPassword(mailAddress, newPassword);
                }else{
                    System.out.println("not mailAddress");
                }
                setResponsePage(new ForgetPass());
            }
        };
        add(mailAddressInfoForm);

        var mailAddressField = new TextField<>("mailAddress", mailAddressModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                setRequired(true);
                setLabel(Model.of("メールアドレス"));
            }
        };
        mailAddressInfoForm.add(mailAddressField);

        var feedback = new FeedbackPanel("feedback");
        mailAddressInfoForm.add(feedback);

        var toLoginLink = new BookmarkablePageLink<>("toLogin", Login.class);
        add(toLoginLink);

    }

}

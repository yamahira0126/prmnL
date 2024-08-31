package com.example.license.service;

import com.example.license.data.Account;
import com.example.license.data.Software;
import com.example.license.repository.IAccountSectionRepository;
import com.example.license.repository.IBudgetSectionRepository;
import com.example.license.repository.ISoftwareRepository;
import com.example.license.repository.ISoftwareSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService implements ISoftwareService{

    private ISoftwareRepository softwareRepos;
    private ISoftwareSectionRepository softwareSectionRepos;
    private IAccountSectionRepository accountSectionRepos;

    @Autowired
    private SoftwareService(ISoftwareRepository softwareRepos,ISoftwareSectionRepository softwareSectionRepos, IAccountSectionRepository accountSectionRepos){
        this.softwareRepos = softwareRepos;
        this.softwareSectionRepos = softwareSectionRepos;
        this.accountSectionRepos = accountSectionRepos;
    }
    @Override
    public void registerSoftware(String softwareName, String softwareType, String totalNumber, String softwareRemarks, Account account) {
        //softwareの追加と追加したsoftwareのIDをreturn
        int softwareId = softwareRepos.insert(softwareName,softwareType,totalNumber,softwareRemarks);
        System.out.println("ソフトウェアID：" + softwareId);
        Integer sectionId = accountSectionRepos.findSectionId(account.getAccountId());
        System.out.println("sectionID:" + sectionId);
        //sectionIdとbudgetIdを紐づけ
        int n = softwareSectionRepos.insert(softwareId, sectionId);

    }

    @Override
    public void renewal(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber, String softwareRemarks) {
        int n = softwareRepos.change(selectedSoftwareName,softwareName,softwareType,totalNumber,softwareRemarks);
        System.out.println("記録行数" + n);
    }

    //software_tableの情報をすべてListに保持させる
    @Override
    public List<Software> findSoftwares() {
        var softwares = softwareRepos.find();
        System.out.println("データ件数 findSoftwares()：" + softwares.size());
        return softwares;
    }

    //software_tableのaccountに関係した情報をListに保持させる
    @Override
    public List<Software> findSoftwares(Account account) {
        var softwares = softwareRepos.find(account);
        System.out.println("データ件数 findSoftwares(account)：" + softwares.size());
        return softwares;
    }
}

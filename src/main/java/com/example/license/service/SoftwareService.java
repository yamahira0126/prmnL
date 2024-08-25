package com.example.license.service;

import com.example.license.data.Software;
import com.example.license.repository.ISoftwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareService implements ISoftwareService{

    private ISoftwareRepository softwareRepos;

    private SoftwareService(ISoftwareRepository softwareRepos){
        this.softwareRepos = softwareRepos;
    }
    @Override
    public void registerSoftware(String softwareName, String softwareType, String totalNumber, String softwareRemarks) {
        int n = softwareRepos.insert(softwareName,softwareType,totalNumber,softwareRemarks);
        System.out.println("記録行数" + n );
    }

    @Override
    public void renewal(String selectedSoftwareName, String softwareName, String softwareType, String totalNumber, String softwareRemarks) {
        int n = softwareRepos.change(selectedSoftwareName,softwareName,softwareType,totalNumber,softwareRemarks);
        System.out.println("記録行数" + n);
    }

    @Override
    public List<Software> findSoftwares() {
        var softwares = softwareRepos.find();
        System.out.println("データ件数" + softwares);
        return softwares;
    }
}

package com.example.license.service;

import com.example.license.data.Section;
import com.example.license.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService{

    private ISectionRepository sectionRepos;

    @Autowired
    public SectionService(ISectionRepository sectionRepos){
        this.sectionRepos = sectionRepos;
    }

    @Override
    public List<Section> findSections() {
        var sections = sectionRepos.find();
        System.out.println("データ件数 findSections：" + sections.size());
        return sections;
    }
}

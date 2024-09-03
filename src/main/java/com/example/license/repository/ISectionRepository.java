package com.example.license.repository;

import com.example.license.data.Section;
import java.util.List;

public interface ISectionRepository {
    public List<Section> find();
    public List<Section> findSectionName();

}

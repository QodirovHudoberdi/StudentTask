package com.company.interfaces;

import com.company.models.FieldStudiesDTO;

import java.util.List;

public interface StudyField {
     FieldStudiesDTO create(FieldStudiesDTO fieldstudiesDTO);
     List<FieldStudiesDTO> getList1();
}
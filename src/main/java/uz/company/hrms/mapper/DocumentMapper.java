package uz.company.hrms.mapper;


import uz.company.hrms.dto.DocumentResponse;
import uz.company.hrms.entity.Document;

import java.util.List;

public class DocumentMapper {



    public static DocumentResponse toDTO(Document document){
        return new DocumentResponse(
                document.getId(),
                document.getType(),
                document.getFileName(),
                document.getFileSize(),
                document.getContentType(),
                document.getFilePath()
        );
    }



    public static List<DocumentResponse> toDTOList(List<Document> documents){

        return documents.stream()
                .map(DocumentMapper::toDTO)
                .toList();
    }



}

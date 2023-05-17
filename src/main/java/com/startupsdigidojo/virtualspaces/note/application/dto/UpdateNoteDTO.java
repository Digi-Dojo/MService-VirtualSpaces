package com.startupsdigidojo.virtualspaces.note.application.dto;

import lombok.Getter;
import lombok.Setter;

public class UpdateNoteDTO extends CreateNoteDTO {
    @Setter @Getter
    private Long id;
    public UpdateNoteDTO() {super();}

    public UpdateNoteDTO(Long id, String text, boolean status, Long placeId,String date){

        super(text,status,placeId,date);
        this.id=id;
    }

}

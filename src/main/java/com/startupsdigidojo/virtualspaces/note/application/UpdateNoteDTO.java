package com.startupsdigidojo.virtualspaces.note.application;

public class UpdateNoteDTO extends CreateNoteDTO {

    private Long id;
    public UpdateNoteDTO() {super();}

    public UpdateNoteDTO(Long id, String text, boolean status, Long placeId,String date){

        super(text,status,placeId,date);
        this.id=id;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}

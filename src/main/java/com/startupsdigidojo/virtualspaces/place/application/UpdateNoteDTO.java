package com.startupsdigidojo.virtualspaces.place.application;

public class UpdateNoteDTO extends CreateNoteDTO{

    private Long id;


    public UpdateNoteDTO() {super();}

    public UpdateNoteDTO(Long id, String text,Long placeId, boolean status){
        super(text,placeId,status);
        this.id=id;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id=id;}
}

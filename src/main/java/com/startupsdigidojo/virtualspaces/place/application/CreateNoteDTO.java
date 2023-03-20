package com.startupsdigidojo.virtualspaces.place.application;

public class CreateNoteDTO {

    private String text;
    private boolean status;

    private Long placeId;
    private String date;




    public CreateNoteDTO(){}

    public CreateNoteDTO(String text, boolean status, Long placeId,String date){
        this.text=text;
        this.status=status;
        this.placeId=placeId;
        this.date=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}

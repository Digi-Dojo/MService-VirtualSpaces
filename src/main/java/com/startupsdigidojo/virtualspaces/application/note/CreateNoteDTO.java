package com.startupsdigidojo.virtualspaces.application.note;

public class CreateNoteDTO {

    private String text;
    private boolean statusAdded;
    private Long placeId;
    private String date;

    public CreateNoteDTO(){}

    public CreateNoteDTO(String text, boolean status, Long placeId,String date){
        this.text=text;
        this.statusAdded =status;
        this.placeId=placeId;
        this.date=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getStatusAdded() {
        return statusAdded;
    }

    public void setStatusAdded(boolean statusAdded) {
        this.statusAdded = statusAdded;
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

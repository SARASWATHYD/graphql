package com.example.demo.services;

import java.util.Set;

public class EventDto {

    private String id;

    private String merchant;

    private String calendar;

    private String brand;

    private Long startTime;

    private Long endTime;

    private int maxSeats;

    private Set<String> service;

    private Set<String> consumer;

    private Set<String> provider;

    private Set<String> resource;

    private double cost;

    private String status;

    private String label;

    private String bookingId;

    private String source;

    private String recurringId;

    private String startDateTime;

    private String endDateTime;

    private String notes;

    private String createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getStartDateTime() {
        return startDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }
    public String getEndDateTimeDateTime() {
        return endDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }


    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public Set<String> getService() {
        return service;
    }

    public void setService(Set<String> service) {
        this.service = service;
    }

    public Set<String> getConsumer() {
        return consumer;
    }

    public void setConsumer(Set<String> consumer) {
        this.consumer = consumer;
    }

    public Set<String> getProvider() {
        return provider;
    }

    public void setProvider(Set<String> provider) {
        this.provider = provider;
    }

    public Set<String> getResource() {
        return resource;
    }

    public void setResource(Set<String> resource) {
        this.resource = resource;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(String recurringId) {
        this.recurringId = recurringId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


}
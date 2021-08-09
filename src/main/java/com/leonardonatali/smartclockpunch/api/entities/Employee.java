package com.leonardonatali.smartclockpunch.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.leonardonatali.smartclockpunch.api.enums.ProfileEnum;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    private static final long serialVersionUID = 3378976460374016L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private BigDecimal valuePerHour;
    private Float workedHoursPerDay;
    private Float lunchHoursQty;
    private ProfileEnum profile;
    private Date createdAt;
    private Date updatedAt;
    private Company company;
    private List<Entry> entries;

    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "cpf", nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "value_per_hour", nullable = true)
    public BigDecimal getValuePerHour() {
        return valuePerHour;
    }

    @Transient
    public Optional<BigDecimal> getValuePerHourOpt() {
        return Optional.ofNullable(valuePerHour);
    }

    public void setValuePerHour(BigDecimal valuePerHour) {
        this.valuePerHour = valuePerHour;
    }

    @Column(name = "worked_hours_per_day", nullable = true)
    public Float getWorkedHoursPerDay() {
        return workedHoursPerDay;
    }

    @Transient
    public Optional<Float> getWorkedHoursPerDayOpt() {
        return Optional.ofNullable(workedHoursPerDay);
    }

    public void setWorkedHoursPerDay(Float workedHoursPerDay) {
        this.workedHoursPerDay = workedHoursPerDay;
    }

    @Column(name = "lunch_hours_qty", nullable = true)
    public Float getLunchHoursQty() {
        return lunchHoursQty;
    }

    @Transient
    public Optional<Float> getlunchHoursQtyOpt() {
        return Optional.ofNullable(lunchHoursQty);
    }

    public void setLunchHoursQty(Float lunchHoursQty) {
        this.lunchHoursQty = lunchHoursQty;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "profile", nullable = false)
    public ProfileEnum getProfile() {
        return profile;
    }

    public void setProfile(ProfileEnum profile) {
        this.profile = profile;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "company")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(mappedBy = "employee_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @PrePersist
    public void prePersist() {
        final Date now = new Date();
        updatedAt = now;
        createdAt = now;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee: [ id: %d, name: %s, email: %s, password: %s, cpf: %s, valuePerHour: %.2f, workedHoursPerDay: %.2f, lunchHoursQty: %.2f, createdAt: %s, updatedAt: %s, company: %s]",
                id, name, email, password, cpf, valuePerHour, workedHoursPerDay, lunchHoursQty, createdAt.toString(),
                updatedAt.toString(), company.toString());
    }
}
